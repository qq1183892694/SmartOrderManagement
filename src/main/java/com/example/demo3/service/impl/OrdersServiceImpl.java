package com.example.demo3.service.impl;

import com.example.demo3.entity.Orders;
import com.example.demo3.dao.OrdersMapper;
import com.example.demo3.service.IOrdersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
