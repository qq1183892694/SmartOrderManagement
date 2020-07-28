package com.example.demo3.dao;

import com.example.demo3.entity.Orders;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo3.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {


    /**
     * 按条件和分页信息查询用户记录
     */
    public List<Orders> listOrders(Orders orders);

}
