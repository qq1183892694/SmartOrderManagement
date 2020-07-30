package com.example.demo3.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo3.entity.OrderDetails;
import com.example.demo3.entity.OrderGoodList;
import com.example.demo3.entity.Orders;
import com.example.demo3.entity.proclamation;
import com.example.demo3.service.IOrderDetailsService;
import com.example.demo3.service.IOrderGoodListService;
import com.example.demo3.service.IOrdersService;
import com.example.demo3.service.IProclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/PrepareDishController")
public class PrepareDishController {

    @Autowired
    IOrderDetailsService orderDetailsService;
    @Autowired
    IOrdersService ordersService;
    @Autowired
    IOrderGoodListService orderGoodListService;
    @Autowired
    IProclamation proclamationService;
    @GetMapping("/preparedish")
    public  String toAddpage3(Model model)
    {
//把最新的公告的信息传过去
        EntityWrapper<proclamation> wrapper1 = new EntityWrapper<>();
        List<proclamation> proclamation = proclamationService.selectList(wrapper1);
        String index = "";
        if (proclamation.size()==0) {
            index = "空";
        }
        else if (proclamation.size()==1) {
            index = proclamation.get(0).getProclamationContext();
        }
        else {
            long maxValue = Integer.parseInt(proclamation.get(0).getProclamationId().substring(proclamation.get(0).getProclamationId().length()-10));
            for (int i = 0;i<proclamation.size();i++){
                if (i==proclamation.size()-1){
                    continue;
                }
                int next = Integer.parseInt(proclamation.get(i+1).getProclamationId()
                        .substring(proclamation.get(i+1).getProclamationId().length()-10));
                if (maxValue < next){
                    maxValue = next;
                    index = proclamation.get(i+1).getProclamationContext();
                }
            }
        }
//        System.out.println(index);//查出最新的公告
        model.addAttribute("proclamationid",index);//传到前端



        EntityWrapper<OrderDetails> wrapper = new EntityWrapper<>();
        List<OrderDetails> orderDetails = orderDetailsService.selectList(wrapper);
        List<String> dishstatusList = new ArrayList<String>();//创建一个list对状态进行筛选，在结账界面不显示已烹饪完的订单
        dishstatusList.add("0");
        orderDetails = orderDetails.stream().filter((OrderDetails orderdetails) -> dishstatusList.contains(orderdetails.getDishStatus())).collect(Collectors.toList());

        model.addAttribute("preparedish",orderDetails);
        model.addAttribute("falt",6);
        return "preparedish/preparedish";
    }

    @RequestMapping("/finishDish/{id}")
    public String finishDish(@PathVariable("id")String id, Model model){
        //把最新的公告的信息传过去
        EntityWrapper<proclamation> wrapper1 = new EntityWrapper<>();
        List<proclamation> proclamation = proclamationService.selectList(wrapper1);
//        System.out.println(proclamation.size());
        String index = "";
        if (proclamation.size()==0) {
            index = "空";
        }
        else if (proclamation.size()==1) {
            index = proclamation.get(0).getProclamationContext();
        }
        else {
            long maxValue = Integer.parseInt(proclamation.get(0).getProclamationId().substring(proclamation.get(0).getProclamationId().length()-10));
            for (int i = 0;i<proclamation.size();i++){
                if (i==proclamation.size()-1){
                    continue;
                }
                int next = Integer.parseInt(proclamation.get(i+1).getProclamationId()
                        .substring(proclamation.get(i+1).getProclamationId().length()-10));
                if (maxValue < next){
                    maxValue = next;
                    index = proclamation.get(i+1).getProclamationContext();
                }
            }
        }
//        System.out.println(index);//查出最新的公告
        model.addAttribute("proclamationid",index);//传到前端

        //把该订单的菜品状态更新为已完成
        EntityWrapper<OrderDetails> wrapper = new EntityWrapper<>();
        wrapper.eq("id",id);
        OrderDetails ordersdetails = orderDetailsService.selectOne(wrapper);
//        System.out.println(ordersdetails);
        ordersdetails.setDishStatus("1");//改变为状态1，意味着烹饪完成
        orderDetailsService.deleteById(id);
        orderDetailsService.insert(ordersdetails);

        model.addAttribute("falt",6);
        return "redirect:/PrepareDishController/preparedish";
    }


}
