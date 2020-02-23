package com.fridy.backend.service.impl;

import com.fridy.backend.base.result.ResponseCode;
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

import java.util.List;

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
        SysUser sysUser = searchByPhone(userDto.getTelephone());
        sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
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

    @Override
    public UserDto getUserById(Integer id) {
        SysUser sysUser = userDao.searchByUserId(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(sysUser, userDto);
        userDto.setRoleId(roleUserDao.getRoleByUserId(id).getRoleId());
        return userDto;
    }

    @Override
    public Results editUser(Integer id, UserDto userDto) {
        SysUser sysUser = userDao.searchByUserId(id);
        if (sysUser == null) {
            return Results.fail(ResponseCode.NOTEXITUSER);
        }
        SysRoleUser userRole = roleUserDao.getRoleByUserId(sysUser.getId().intValue());
        if(userDto.getRoleId() != userRole.getRoleId()){
            userRole.setRoleId(userDto.getRoleId());
            roleUserDao.updateRoleId(userRole);
        }
        BeanUtils.copyProperties(userDto,sysUser);
        userDao.updateUser(sysUser);
        return Results.success();
    }

    @Override
    public void delectUserById(Integer id) {
        userDao.delectUserById(id);
    }

    @Override
    public Results<SysUser> getUserByFuzzyUsername(Integer limit, Integer offset, String username) {
        List<SysUser> userByFuzzyUsername = userDao.getUserByFuzzyUsername(limit, offset, username);
        return Results.succcess(userByFuzzyUsername.size(),userByFuzzyUsername);
    }
}
