package com.example.demo3.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo3.dao.OrderGoodListMapper;
import com.example.demo3.dao.OrdersMapper;
import com.example.demo3.entity.OrderGoodList;
import com.example.demo3.entity.Orders;
import com.example.demo3.service.IOrderGoodListService;
import com.example.demo3.service.IOrdersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Service
public class OrderGoodListServiceImpl extends ServiceImpl<OrderGoodListMapper, OrderGoodList> implements IOrderGoodListService {

}
