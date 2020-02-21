package com.fridy.backend.service.impl;

import com.fridy.backend.dao.RoleDao;
import com.fridy.backend.model.SysRole;
import com.fridy.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp1 implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Override
    public List<SysRole> getRoleList() {
        return roleDao.getRoles();
    }
}
