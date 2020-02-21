package com.fridy.backend.dao;

import com.fridy.backend.model.SysRoleUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleUserDao {

    @Insert("insert into sys_role_user(userId,roleId) values (#{userId},#{roleId})")
    void addRecord(SysRoleUser sysRoleUser);
}
