package com.autumn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.autumn.domain.Role;
import com.autumn.service.RoleService;
import com.autumn.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 侯亚雄
* @description 针对表【t_role】的数据库操作Service实现
* @createDate 2023-06-02 17:19:28
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




