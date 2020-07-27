package com.example.demo3.dao;

import com.example.demo3.entity.Dishs;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Mapper
public interface DishsMapper extends BaseMapper<Dishs> {

    /**
     * 添加菜品
     * @param dishs
     */
    void saveDishInfo(Dishs dishs);

    /**
     * 更新菜品
     * @param dishs
     */
    void updataDishInfo(Dishs dishs);



}
