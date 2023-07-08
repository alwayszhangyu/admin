package com.autumn.service;

import com.autumn.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 侯亚雄
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-06-2 17:23:18
*/
public interface UserService extends IService<User> {
    /**
     * @author 曾帅
     * @param user 用户信息
     * @return 返回用户信息
     */
    Map<String, Object> login(User user);

    /**
     * @author 侯亚雄
     * @param token token
     * @return 返回结果
     */
    Map<String, Object> getUserInfo(String token);

    /**
     * @author 罗宇
     * @param token token
     */
    void logout(String token);
}
