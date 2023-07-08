package com.autumn.controller;

import com.autumn.domain.User;
import com.autumn.service.UserService;
import com.autumn.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 侯亚雄
 * @createDate 2023/6/2 12:24
 * @description
 **/
@RestController
@RequestMapping
public class UserController {

    /**
     * 构造器注入
     */
    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @author 侯亚雄
     * @return 返回查询结果
     */
    @GetMapping("/user/getAll")
    public Result<List<User>> getAll() {
        List<User> userList = userService.list();
        if (userList != null) {
            return Result.success("查询成功", userList);
        }
        return Result.fail("查询失败", null);
    }

    /**
     * @author 曾帅
     * @param user 用户信息
     * @return 如果查询成功返回用户信息，反正返回错误信息。
     */
    @PostMapping("/user/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> data = userService.login(user);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("用户名或密码错误");
    }

    /**
     * @author 侯亚雄
     * @param token token信息
     * @return token存在则返回用户信息，反之则返回错误信息
     */
    @GetMapping("/user/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token) {
        Map<String, Object> data = userService.getUserInfo(token);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("用户登陆信息无效");
    }

    /**
     * @author 罗宇
     * @param token token信息
     * @return 返回响应代码
     */
    @PostMapping("/user/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        userService.logout(token);
        return Result.success();
    }

    /**
     * @author 侯亚雄
     * @param username 用户姓名
     * @param phone 用户电话
     * @param pageNo 分页页数
     * @param pageSize 每页多少数据
     * @return 如果查询成功，则返回一个带有数据的结果。
     */
    @GetMapping("/user/list")
    public Result<Map<String, Object>> userList(@RequestParam(value = "username", required = false) String username,
                                               @RequestParam(value = "phone", required = false) String phone,
                                               @RequestParam(value = "pageNo") Long pageNo,
                                               @RequestParam(value = "pageSize") Long pageSize) {
        // 查询条件构造器
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);
        wrapper.eq(StringUtils.hasLength(phone),User::getPhone,phone);

        Page<User> page = new Page<>(pageNo, pageSize);
        userService.page(page,wrapper);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total",page.getTotal());
        hashMap.put("rows",page.getRecords());

        return Result.success(hashMap);
    }

    /**
     * @author 侯亚雄
     * @param user 用户信息
     * @return 返回成功信息
     */
    @PostMapping("/user/addUser")
    public Result<?> addUser(@RequestBody User user){
        user.setPassword("123456");
        user.setDeleted(0);
        userService.save(user);
        return Result.success("新增用户成功");
    }

    /**
     * @author 侯亚雄
     * @param user 用户信息
     * @return 返回成功信息
     */
    @PutMapping("/user/updateUser")
    public Result<?> updateUser(@RequestBody User user){
        user.setPassword(null);
        userService.updateById(user);
        return Result.success("用户修改成功");
    }

    /**
     * @author 侯亚雄
     * @param id 用户id
     * @return 返回用户信息
     */
    @GetMapping("/user/getUserById/{id}")
    public Result<User> getById(@PathVariable("id") Integer id){
        User byId = userService.getById(id);
        return Result.success(byId);
    }

    /**
     * @author 吴雯媚
     * @param id 用户id
     * @return 返回成功信息
     */
    @DeleteMapping("/user/deleteById/{id}")
    public Result<User> deleteById(@PathVariable("id") Integer id){
        userService.removeById(id);
        return Result.success("用户删除成功");
    }
}
