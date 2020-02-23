package com.fridy.backend.controller;
import com.fridy.backend.base.result.PageTableRequest;
import com.fridy.backend.base.result.Results;
import com.fridy.backend.dto.UserDto;
import com.fridy.backend.model.SysRole;
import com.fridy.backend.model.SysUser;
import com.fridy.backend.service.RoleService;
import com.fridy.backend.service.RoleUserService;
import com.fridy.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    RoleService roleService;

    @Autowired
    RoleUserService roleUserService;

    @Autowired
    private UserService userService;
//    @GetMapping("/{username}")
//    public SysUser getUserJson(@PathVariable String username){
//        log.info("UserController.user():param(username = "+username+")");
//        return userServiceImp1.getUser(username);
//    }
    @GetMapping("/list")
    @ResponseBody
    public Results<SysUser> getList(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return userService.getUserList(pageTableRequest.getLimit(),pageTableRequest.getOffset());
    }

    @GetMapping("/add")
    public String  getPage(Model model){
        List<SysRole> roleList = roleService.getRoleList();
        model.addAttribute("roles",roleList);
        return "user/user-add";
    }


    @PostMapping("/add")
    @ResponseBody
    public Results addUser(UserDto userDto) {
         return userService.addUser(userDto);
    }

    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id,Model model){
        List<SysRole> roleList = roleService.getRoleList();
        model.addAttribute("roles",roleList);
        model.addAttribute("sysUser",userService.getUserById(id));
        return "user/user-edit";
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public Results userEdit(@PathVariable("id") Integer id,UserDto userDto){
       return userService.editUser(id,userDto);
    }

    @GetMapping("/del")
    @ResponseBody
    public Results delUser(Integer id){
        roleUserService.delectRecordByUserId(id);
        userService.delectUserById(id);
        return Results.success();
    }

    @GetMapping("/select")
    @ResponseBody
    public Results<SysUser> getUserByFuzzyUsername(String username,Integer page,Integer limit){
        PageTableRequest pageTableRequest = new PageTableRequest();
        pageTableRequest.setLimit(limit);
        pageTableRequest.setPage(page);
        pageTableRequest.countOffset();
        return userService.getUserByFuzzyUsername(pageTableRequest.getLimit(),pageTableRequest.getOffset(),username);
    }
}
