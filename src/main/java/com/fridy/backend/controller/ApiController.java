package com.fridy.backend.controller;

import com.fridy.backend.model.SysRole;
import com.fridy.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("${api-url}")
public class ApiController {
    @Autowired
    RoleService roleService;
    @RequestMapping("/getPage")
    public ModelAndView getPage(ModelAndView modelAndView,String pageName){
        modelAndView.setViewName(pageName);
        if(pageName.equals("user/user-add")){
            List<SysRole> roleList = roleService.getRoleList();
            modelAndView.addObject("roles",roleList);
        }
        return modelAndView;
    }
}
