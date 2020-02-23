package com.fridy.backend.controller;

import com.fridy.backend.base.result.PageTableRequest;
import com.fridy.backend.base.result.Results;
import com.fridy.backend.model.SysUser;
import com.fridy.backend.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sysrole")
@Slf4j
public class SysRoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/list")
    @ResponseBody

    public Results<SysUser> getList(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return userService.getUserList(pageTableRequest.getLimit(),pageTableRequest.getOffset());
    }
}
