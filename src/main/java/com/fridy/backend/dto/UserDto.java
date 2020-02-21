package com.fridy.backend.dto;

import com.fridy.backend.model.SysUser;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String username;
    private String password;
    private String nickname;
    private String telephone;
    private String email;
    private Date birthday;
    private Integer sex;
    private Integer roleId;
}
