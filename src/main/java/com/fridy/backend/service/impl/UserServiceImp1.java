package com.fridy.backend.service.impl;

import com.fridy.backend.base.result.Results;
import com.fridy.backend.dao.RoleUserDao;
import com.fridy.backend.dao.UserDao;
import com.fridy.backend.dto.UserDto;
import com.fridy.backend.model.SysRoleUser;
import com.fridy.backend.model.SysUser;
import com.fridy.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImp1<RoleUserDto> implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public SysUser getUser(String name) {
        return userDao.getUser(name);
    }

    @Override
    public Results<SysUser> getUserList(Integer limit, Integer offset) {
        return Results.succcess(userDao.getUserNum(), userDao.getUserList(limit, offset));
    }

    @Override
    public Results addUser(UserDto userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto,sysUser);
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        sysUser.setStatus(1);
        userDao.add(sysUser);
        Integer id = sysUser.getId().intValue();
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setRoleId(userDto.getRoleId());
        sysRoleUser.setUserId(id);
        roleUserDao.addRecord(sysRoleUser);
        return Results.success();
    }

    @Override
    public SysUser searchByPhone(String telephone) {
        return userDao.searchByPhone(telephone);
    }

    @Override
    public SysUser searchByEmail(String email) {
        return userDao.searchByEmail(email);
    }

    @Override
    public SysUser searchByUsername(String username) {
        return userDao.searchByUsername(username);
    }
}
