package com.autumn.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.autumn.domain.User;
import com.autumn.service.UserService;
import com.autumn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
* @author 侯亚雄
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-06-02 16:54:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    final private RedisTemplate redisTemplate;

    @Autowired
    public UserServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @author 曾帅
     * @param user 用户信息
     * @return 成功则生成token，反之则返回为空。
     */
    @Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        wrapper.eq(User::getPassword,user.getPassword());
        User selectOne = this.baseMapper.selectOne(wrapper);
        if (selectOne != null){
            String uuid = "user:" + UUID.randomUUID();
            selectOne.setPassword(null);
            redisTemplate.opsForValue().set(uuid,selectOne,30, TimeUnit.MINUTES);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("token",uuid);
            return hashMap;
        }
        return null;
    }

    /**
     * @author 侯亚雄
     * @param token token
     * @return 如果token存在就获取用户信息，反之则返回空
     */
    @Override
    public Map<String, Object> getUserInfo(String token) {
        Object data = redisTemplate.opsForValue().get(token);
        if (data != null){
            User user = JSON.parseObject(JSON.toJSONString(data), User.class);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name",user.getUsername());
            hashMap.put("avatar",user.getAvatar());
            List<String> userInfo = this.baseMapper.getUserInfo(user.getId());
            hashMap.put("roles",userInfo);
            return hashMap;
        }

        return null;
    }

    /**
     * @author 罗宇
     * @param token token信息
     */
    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}




