package com.example.demo3.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo3.entity.proclamation;
import com.example.demo3.service.IProclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ProclamationController")
public class ProclamationController {
    @Autowired
    private IProclamation proclamations;
    @RequestMapping("/proclamations")
    public String getProclamationList(Model model)
    {
        EntityWrapper<proclamation> wrapper=new EntityWrapper<>();
        List<proclamation> ProclamationList=proclamations.selectList(wrapper);
        model.addAttribute("proclamations",ProclamationList);
        model.addAttribute("falt",7);
        return "proclamation/list";
    }
    @PostMapping("/findproclamation")
    public  String list(Model model, HttpServletRequest httpServletRequest)
    {
        String attribute=httpServletRequest.getParameter("Id");
        if (attribute=="")
        {
            EntityWrapper<proclamation> wrapper = new EntityWrapper<proclamation>();
            List<proclamation> vipList = proclamations.selectList(wrapper);
            model.addAttribute("proclamations",vipList);
            model.addAttribute("falt",7);
            return "proclamation/list";
        }
        else
        {
            EntityWrapper<proclamation> wrapper = new EntityWrapper<proclamation>();
            proclamation vipList = proclamations.selectById(attribute);
            model.addAttribute("proclamations",vipList);
            model.addAttribute("falt",7);
            return "proclamation/list";
        }
    }
    @GetMapping("/proclamation")
    public  String toAddpage(Model model)
    {
        model.addAttribute("falt",7);
        return "proclamation/add";
    }
    @PostMapping("/proclamation")
    public String addVip(proclamation vip)
    {
        System.out.println(vip.toString());
        proclamations.insert(vip);
        return "redirect:/ProclamationController/proclamations";
    }

    @RequestMapping("/deleteProclamation/{id}")//移出购物车中的商品
    public String deletegoodInOrderList(@PathVariable("id") String id, Model model)
    {
        proclamations.deleteById(id);
        return "redirect:/ProclamationController/proclamations";//这里需要重定向，因为删除了东西
    }


}