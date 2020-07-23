package com.example.demo3.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo3.entity.Orders;
import com.example.demo3.service.IDishsService;
import com.example.demo3.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/OrdersController")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("/orders")//显示订单列表
    public String getOrderList(Model model){
        EntityWrapper<Orders> wrapper = new EntityWrapper<>();
        List<Orders> orders = ordersService.selectList(wrapper);

        model.addAttribute("orders",orders);
        model.addAttribute("falt",2);
        return "order/list";
    }

    @GetMapping("/order")
    public  String toAddpage(Model model)
    {
        model.addAttribute("falt",0);
        return "order/add";
    }


}
