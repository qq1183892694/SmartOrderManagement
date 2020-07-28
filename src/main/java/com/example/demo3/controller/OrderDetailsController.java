package com.example.demo3.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo3.entity.OrderDetails;
import com.example.demo3.entity.Orders;
import com.example.demo3.service.IOrderDetailsService;
import com.example.demo3.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/OrderDetailsController")
public class OrderDetailsController {

    @Autowired
    private IOrderDetailsService orderDetailsService;
    @Autowired
    private IOrdersService ordersService;

    @GetMapping("/order/{id}")//点击结账之后显示账单的详情
    public String getDetailList(@PathVariable("id")String id , Model model){
        EntityWrapper<OrderDetails> wrapper = new EntityWrapper<>();
        wrapper.eq("orderId",id);
        List<OrderDetails> orderDetailList = orderDetailsService.selectList(wrapper);
//        OrderDetail orderDetail=orderDetailService.selectById(id);
        model.addAttribute("orderId",id);
        model.addAttribute("orderDetail",orderDetailList);
        model.addAttribute("falt",5);
        return "detail/list";
    }
    @RequestMapping("/finish/{id}")//在详情界面进行确认结账
    public String finish(@PathVariable("id")String id , Model model){
        EntityWrapper<Orders> wrapper = new EntityWrapper<>();
        wrapper.eq("orderId",id);
        Orders orders = ordersService.selectOne(wrapper);

        System.out.println(orders);
        orders.setOrderStatus("1");
        System.out.println(orders);
        ordersService.deleteById(id);
        ordersService.insert(orders);
//        order.forEach(item->{
//            Orders orders = new Orders();
//            item.setOrderStatus("1");
//            ordersService.deleteById(id);
//            ordersService.insert(orders);
//        });


        model.addAttribute("falt",5);
        return "redirect:/OrdersController/finishorder";
    }

}
