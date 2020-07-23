package com.example.demo3.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo3.entity.Users;
import com.example.demo3.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Controller
@RequestMapping("/UserController")
public class UsersController {

    @Autowired
    IUsersService usersService;
    @GetMapping("/users")
    public String list(Model model)
    {
        EntityWrapper<Users> wrapper = new EntityWrapper<>();
        List<Users> usersList = usersService.selectList(wrapper);
        model.addAttribute("users",usersList);
        model.addAttribute("falt",1);
        return "user/list";
    }


}
