package com.autumn.mapper;

import com.autumn.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Autumn
* @description 针对表【t_role】的数据库操作Mapper
* @createDate 2023-06-08 17:19:28
* @Entity com.autumn.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




