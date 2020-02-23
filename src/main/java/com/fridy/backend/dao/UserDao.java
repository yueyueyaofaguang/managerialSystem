package com.fridy.backend.dao;
import com.fridy.backend.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from sys_user t where t.username = #{username}")
    SysUser getUser(String username);

    @Select("select * from sys_user limit #{limit} offset #{offset}")
    List<SysUser> getUserList(Integer limit, Integer offset);

    @Select("select count(*) from sys_user")
    Integer getUserNum();

    @Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer add(SysUser sysUser);

    @Select("select * from sys_user t where t.telephone = #{telephone}")
    SysUser searchByPhone(String telephone);

    @Select("select * from sys_user t where t.email = #{email}")
    SysUser searchByEmail(String email);

    @Select("select * from sys_user t where t.username = #{username}")
    SysUser searchByUsername(String username);

    @Select("select * from sys_user t where t.id = #{id}")
    SysUser searchByUserId(Integer id);

    @Update("update sys_user set username = #{username},phone = #{phone}, telephone = #{telephone}, email = #{email}, birthday = #{birthday}, sex = #{sex},updateTime = now() where id = #{id} ")
    void updateUser(SysUser sysUser);

    @Delete("delete from sys_user where id = #{id}")
    void delectUserById(Integer id);

    @Select("select * from sys_user where username like concat('%',#{username},'%') limit #{limit} offset #{offset}")
    List<SysUser> getUserByFuzzyUsername(Integer limit, Integer offset, String username);
}
