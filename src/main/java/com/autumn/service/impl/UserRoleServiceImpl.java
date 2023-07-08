package com.autumn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.autumn.domain.UserRole;
import com.autumn.service.UserRoleService;
import com.autumn.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 侯亚雄
* @description 针对表【t_user_role】的数据库操作Service实现
* @createDate 2023-06-02 18:30:56
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




