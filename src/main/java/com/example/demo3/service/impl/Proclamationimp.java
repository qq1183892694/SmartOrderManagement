package com.example.demo3.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo3.dao.proclamationMapper;
import com.example.demo3.entity.proclamation;
import com.example.demo3.service.IProclamation;
import org.springframework.stereotype.Service;

@Service
public class Proclamationimp extends ServiceImpl<proclamationMapper, proclamation> implements IProclamation {
}
