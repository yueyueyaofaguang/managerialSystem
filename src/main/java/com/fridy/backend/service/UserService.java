package com.fridy.backend.service;

import com.fridy.backend.base.result.Results;
import com.fridy.backend.dao.UserDao;
import com.fridy.backend.dto.UserDto;
import com.fridy.backend.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserService {
    SysUser getUser(String name);
    Results<SysUser> getUserList(Integer limit, Integer offset);
    Results addUser(UserDto userDto);
    SysUser searchByPhone(String telephone);
    SysUser searchByEmail(String email);
    SysUser searchByUsername(String username);
}
