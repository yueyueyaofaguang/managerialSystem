package com.fridy.backend.service.impl;

import com.fridy.backend.dao.RoleUserDao;
import com.fridy.backend.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImp1 implements RoleUserService {
    @Autowired
    RoleUserDao roleUserDao;
    @Override
    public void delectRecordByUserId(Integer id) {
        roleUserDao.deleteRecordByUserId(id);
    }
}
