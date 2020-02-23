package com.fridy.backend.dto;

import com.fridy.backend.model.SysUser;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto extends SysUser{
    private Integer roleId;
}
