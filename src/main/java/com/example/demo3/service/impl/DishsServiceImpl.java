package com.example.demo3.service.impl;

import com.example.demo3.entity.Dishs;
import com.example.demo3.dao.DishsMapper;
import com.example.demo3.service.IDishsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Service
public class DishsServiceImpl extends ServiceImpl<DishsMapper, Dishs> implements IDishsService {

    @Autowired
    private DishsMapper dishsMapper;

    @Override
    public void uploadDishInfo(Dishs dishs) {
        dishsMapper.saveDishInfo(dishs);
    }

    @Override
    public void updataDishInfo(Dishs dishs) {
        dishsMapper.updataDishInfo(dishs);
    }
}
