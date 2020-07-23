package com.example.demo3.service.impl;

import com.example.demo3.entity.Users;
import com.example.demo3.dao.UsersMapper;
import com.example.demo3.service.IUsersService;
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
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
