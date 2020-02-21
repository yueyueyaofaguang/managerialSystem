package com.fridy.backend.dao;

import com.fridy.backend.model.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {
    @Select("select * from sys_role")
    List<SysRole> getRoles();
}
