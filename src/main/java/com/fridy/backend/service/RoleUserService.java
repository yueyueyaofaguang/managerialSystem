package com.fridy.backend.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface RoleUserService {
    void delectRecordByUserId(Integer id);
}
