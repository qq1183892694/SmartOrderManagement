package com.example.demo3.controller;

import com.example.demo3.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/LoginController")
public class LoginController {
    @Autowired
    IUsersService usersService;
    @RequestMapping("/login")
    public String login(@RequestParam("Loginname") String Loginname,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) //model用于回显数据
    {
        System.out.println(Loginname);
        System.out.println(password);
        return "redirect:/OrdersController/order";
//        if(usersService.selectById(Loginname)!=null)
//        {
//            //           System.out.println(usersService.selectById(Loginname).getUserPassword());
////            System.out.println(password);
//            if (usersService.selectById(Loginname).getUserPassword().equals(password))
//            {
//                session.setAttribute("loginUser",Loginname);
//                return "redirect:/OrdersController/order";
//            }
//            else
//            {
//                model.addAttribute("msg","密码错误！");
//                return  "index";
//            }
//        }
//        else
//        {
//            model.addAttribute("msg","用户名错误！");
//            return  "index";
//        }
    }
    @RequestMapping("/logout")
    public  String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/index.html";
    }

}
