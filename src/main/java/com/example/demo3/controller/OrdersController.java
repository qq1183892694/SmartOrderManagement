package com.example.demo3.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo3.entity.Dishs;
import com.example.demo3.entity.OrderDetails;
import com.example.demo3.entity.OrderGoodList;
import com.example.demo3.entity.Orders;
import com.example.demo3.service.IDishsService;
import com.example.demo3.service.IOrderDetailsService;
import com.example.demo3.service.IOrderGoodListService;
import com.example.demo3.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

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
    @Autowired
    private IDishsService dishsService;
    @Autowired
    private IOrderGoodListService orderGoodListService;
    @Autowired
    private IOrderDetailsService orderDetailsService;


    @RequestMapping("/orders")//显示订单列表
    public String getOrderList(Model model){
        //首先得把购物单给清空
        EntityWrapper<OrderGoodList> wrapper1 = new EntityWrapper<>();
        List<OrderGoodList> ordergoodsList = orderGoodListService.selectList(wrapper1);
        ordergoodsList.forEach(item->{
            orderGoodListService.deleteById(item.getId());
        });

        EntityWrapper<Orders> wrapper = new EntityWrapper<>();
        List<Orders> orders = ordersService.selectList(wrapper);

        model.addAttribute("orders",orders);
        model.addAttribute("falt",2);
        return "order/list";
    }
    @GetMapping("/order")//跳转到服务员点餐界面
    public  String toAddpage(Model model)
    {
        //加载购物车中的商品
        EntityWrapper<OrderGoodList> wrapper = new EntityWrapper<>();
        List<OrderGoodList> ordergoodsList = orderGoodListService.selectList(wrapper);
//        orderGoodListService.通过mybatis获取表中数据条数以及金额总和并且返回至前端页面
        EntityWrapper<Dishs> wrapper1 = new EntityWrapper<>();
        List<Dishs> goodsList = dishsService.selectList(wrapper1);
        model.addAttribute("ordergoodlist",ordergoodsList);
        model.addAttribute("count",ordergoodsList.size());
        double sum= ordergoodsList.stream().mapToDouble(OrderGoodList::getDishPrice).sum();
        model.addAttribute("sum",sum);
        model.addAttribute("falt",4);
        return "order/add";
    }
    @RequestMapping("/searchGood")//在购物单页面查询商品
    public String searchGood(Model model, HttpServletRequest httpServletRequest)
    {
        String attribute = httpServletRequest.getParameter("Name");
        if (attribute=="")
        {
            EntityWrapper<Dishs> wrapper = new EntityWrapper<>();
            List<Dishs> dishList = dishsService.selectList(wrapper);
            model.addAttribute("dishs",dishList);

            //显示购物单信息
            EntityWrapper<OrderGoodList> wrapper2 = new EntityWrapper<>();
            List<OrderGoodList> ordergoodsList = orderGoodListService.selectList(wrapper2);
            model.addAttribute("ordergoodlist",ordergoodsList);
            model.addAttribute("count",ordergoodsList.size());

            double sum= ordergoodsList.stream().mapToDouble(OrderGoodList::getDishPrice).sum();
            model.addAttribute("sum",sum);

            model.addAttribute("falt",4);
            return "order/add";
        }
        else
        {
            //显示购物单信息

            Dishs goodsList = dishsService.selectById(attribute);
            model.addAttribute("dishs",goodsList);

            EntityWrapper<OrderGoodList> wrapper2 = new EntityWrapper<>();
            List<OrderGoodList> ordergoodsList = orderGoodListService.selectList(wrapper2);
            model.addAttribute("ordergoodlist",ordergoodsList);
            model.addAttribute("count",ordergoodsList.size());

            double sum= ordergoodsList.stream().mapToDouble(OrderGoodList::getDishPrice).sum();
            model.addAttribute("sum",sum);

            model.addAttribute("falt",4);
            return "order/add";
        }
    }


    @RequestMapping("/addgoodIntoOrderList/{id}")//将商品添加到购物车
    public String addgoodIntoOrderList(@PathVariable("id")String id, OrderGoodList orderGoodList)
    {
//        System.out.println(id);
        Dishs dishs=dishsService.selectById(id);
//        Date date=new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        orderGoodList.setId(dateFormat.format(date));//获取当前的时间当做id
        long MilliSeconds = System.currentTimeMillis();
        orderGoodList.setId(String.valueOf(MilliSeconds));
        orderGoodList.setDishId(String.valueOf(dishs.getDishId()));
        orderGoodList.setDishName(dishs.getDishName());
        orderGoodList.setDishPrice(dishs.getDishPrice());

        orderGoodListService.insert(orderGoodList);
        return "redirect:/OrdersController/ordergoodlist";
    }

    @RequestMapping("/ordergoodlist")
    public String getGoodsList(Model model){
        //加载购物车中的商品
        EntityWrapper<OrderGoodList> wrapper = new EntityWrapper<>();
        List<OrderGoodList> ordergoodsList = orderGoodListService.selectList(wrapper);

//        orderGoodListService.通过mybatis获取表中数据条数以及金额总和并且返回至前端页面
        EntityWrapper<Dishs> wrapper1 = new EntityWrapper<>();
        List<Dishs> goodsList = dishsService.selectList(wrapper1);
        model.addAttribute("dishs",goodsList);
        model.addAttribute("ordergoodlist",ordergoodsList);
        model.addAttribute("count",ordergoodsList.size());
        double sum= ordergoodsList.stream().mapToDouble(OrderGoodList::getDishPrice).sum();
        model.addAttribute("sum",sum);
        model.addAttribute("falt",4);
        return "order/add";
    }


    @RequestMapping("/deletegoodInOrderList/{id}")//移出购物车中的商品
    public String deletegoodInOrderList(@PathVariable("id") String id,Model model)
    {
        orderGoodListService.deleteById(id);
        return "redirect:/OrdersController/ordergoodlist";//这里需要重定向到加载购物车里面的东西，因为删除了东西
    }




    @RequestMapping("/submitOrder")//提交订单
    public String submitOrder(Orders orders, HttpServletRequest httpServletRequest, Model model) {
        String attribute=httpServletRequest.getParameter("TableId");
        EntityWrapper<OrderGoodList> wrapper = new EntityWrapper<>();
        List<OrderGoodList> ordergoodsList = orderGoodListService.selectList(wrapper);
        if (ordergoodsList.size() != 0) {
            Date date = new Date();
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orders.setOrderDate(dateFormat1.format(date));//获取当前的日期
            long MilliSeconds = System.currentTimeMillis();//获取毫秒数作为订单id
            orders.setOrderId(String.valueOf(MilliSeconds));

            double sum = ordergoodsList.stream().mapToDouble(OrderGoodList::getDishPrice).sum();
            orders.setOrderPrice(sum);
            //获取总金额

            HttpSession session = httpServletRequest.getSession();
            String userid = (String) session.getAttribute("loginUser");
            userid = "1";//因为员工系统还没做好所以设置一个初始值
            orders.setOrderUserId(userid);
            //获取当前的员工编号

            orders.setOrderTable(attribute);
            //设置当前订单的桌号

            orders.setOrderStatus("0");
            //设置当前订单为未支付状态

            System.out.println(orders);
            ordersService.insert(orders);//添加上面的信息进入总订单

            ordergoodsList.forEach((item) -> {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setId(item.getId());
                orderDetail.setDishName(item.getDishName());
                orderDetail.setDishId(item.getDishId());
                orderDetail.setDishPrice(item.getDishPrice());
                orderDetail.setDishNumber("1");
                orderDetail.setOrderId(String.valueOf(MilliSeconds));
                orderDetail.setDishStatus("0");
                orderDetail.setOrderTable(attribute);
                orderDetailsService.insert(orderDetail);
                orderGoodListService.deleteById(item.getId());
            });//把所有商品添加到详情表中去

            model.addAttribute("count", "0");
            model.addAttribute("sum", 0.0);
            model.addAttribute("falt", 4);
            return "/order/add";
        } else {
            model.addAttribute("count", "0");
            model.addAttribute("sum", 0.0);
            model.addAttribute("falt", 4);
            model.addAttribute("warning", "订单为空，提交订单失败！");
            return "/order/add";
        }
    }

    @GetMapping("/finishorder")//餐桌结账部分
    public  String toAddpage2(Model model)
    {
        //首先得把购物单给清空
        EntityWrapper<OrderGoodList> wrapper1 = new EntityWrapper<>();
        List<OrderGoodList> ordergoodsList = orderGoodListService.selectList(wrapper1);
        ordergoodsList.forEach(item->{
            orderGoodListService.deleteById(item.getId());
        });


        EntityWrapper<Orders> wrapper = new EntityWrapper<>();
        List<Orders> orders = ordersService.selectList(wrapper);

        List<String> statusList = new ArrayList<String>();//创建一个list对状态进行筛选，在结账界面不显示已结账的订单
        statusList.add("0");
        orders = orders.stream().filter((Orders order) -> statusList.contains(order.getOrderStatus())).collect(Collectors.toList());
//        wrapper.eq("0",new Orders().getOrderStatus());
        model.addAttribute("orders",orders);
        model.addAttribute("falt",5);
        return "order/finishorder";
    }



}
