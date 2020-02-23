package com.fridy.backend.dao;

import com.fridy.backend.model.SysRoleUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoleUserDao {

    @Insert("insert into sys_role_user(userId,roleId) values (#{userId},#{roleId})")
    void addRecord(SysRoleUser sysRoleUser);

    @Select("select * from  sys_role_user t where t.userId = #{id}")
    SysRoleUser getRoleByUserId(Integer id);

    @Update("update sys_role_user set roleId = #{roleId} where userId = #{userId}")
    void updateRoleId(SysRoleUser userRole);

    @Delete("delete from sys_role_user where userId = #{id}")
    void deleteRecordByUserId(Integer id);
}
