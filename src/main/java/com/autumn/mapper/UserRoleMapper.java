package com.autumn.mapper;

import com.autumn.domain.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Autumn
* @description 针对表【t_user_role】的数据库操作Mapper
* @createDate 2023-06-08 18:30:56
* @Entity com.autumn.domain.UserRole
*/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}




