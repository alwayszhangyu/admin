package com.autumn.mapper;

import com.autumn.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Autumn
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-06-10 17:23:18
* @Entity com.autumn.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> getUserInfo(Integer userId);
}
