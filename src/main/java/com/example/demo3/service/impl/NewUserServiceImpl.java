package com.example.demo3.service.impl;

import com.example.demo3.dao.NewUserMapper;
import com.example.demo3.entity.NewUser;
import com.example.demo3.service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2020/7/26.
 *
 * @author Zhouyong Tan
 */
@Service
public class NewUserServiceImpl implements NewUserService {


    @Autowired
    private NewUserMapper newUserMapper;

    @Override
    public void insert(NewUser newUser) {
        newUserMapper.insert(newUser);
    }
}
