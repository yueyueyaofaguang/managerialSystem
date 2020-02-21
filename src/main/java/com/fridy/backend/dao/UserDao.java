package com.fridy.backend.dao;

import com.fridy.backend.model.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface  UserDao{
    @Select("select * from sys_user t where t.username = #{username}")
    SysUser getUser(String username);

    @Select("select * from sys_user limit #{limit} offset #{offset}")
    List<SysUser> getUserList(Integer limit,Integer offset);

    @Select("select count(*) from sys_user")
    Integer getUserNum();

    @Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    Integer add(SysUser sysUser);

    @Select("select * from sys_user t where t.telephone = #{telephone}")
    SysUser searchByPhone(String telephone);
    @Select("select * from sys_user t where t.email = #{email}")
    SysUser searchByEmail(String email);
    @Select("select * from sys_user t where t.username = #{username}")
    SysUser searchByUsername(String username);
}
