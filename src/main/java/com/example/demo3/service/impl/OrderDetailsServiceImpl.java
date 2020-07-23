package com.example.demo3.service.impl;

import com.example.demo3.entity.OrderDetails;
import com.example.demo3.dao.OrderDetailsMapper;
import com.example.demo3.service.IOrderDetailsService;
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
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails> implements IOrderDetailsService {

}
