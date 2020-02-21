package com.fridy.backend.controller;
import com.fridy.backend.base.result.PageTableRequest;
import com.fridy.backend.base.result.ResponseCode;
import com.fridy.backend.base.result.Results;
import com.fridy.backend.dto.UserDto;
import com.fridy.backend.model.SysUser;
import com.fridy.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {
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

    @PostMapping("/add")
    @ResponseBody
    public Results addUser(UserDto userDto) {
        SysUser sysUser = userService.searchByPhone(userDto.getTelephone());
        if(sysUser!=null){
            return Results.fail(ResponseCode.EXITPHONE);
        }
        sysUser = userService.searchByEmail(userDto.getEmail());
        if(sysUser!=null){
            return Results.fail(ResponseCode.EXITEMAIL);
        }
        sysUser = userService.searchByUsername(userDto.getUsername());
        if(sysUser!=null){
            return Results.fail(ResponseCode.EXITUSERNAME);
        }

         return userService.addUser(userDto);
    }

    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
}
