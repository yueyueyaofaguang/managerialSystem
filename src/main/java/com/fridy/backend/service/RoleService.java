package com.fridy.backend.service;

import com.fridy.backend.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  RoleService {
    public List<SysRole> getRoleList();
}
