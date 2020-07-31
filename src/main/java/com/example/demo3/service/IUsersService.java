package com.example.demo3.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo3.entity.Users;
import com.baomidou.mybatisplus.service.IService;
import com.example.demo3.entity.vo.UsersAuthorityVO;
import org.apache.catalina.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
public interface IUsersService extends IService<Users> {

    /**
     * 按用户名查找
     */
   Users findUserByUserName(String name);

   List<UsersAuthorityVO> findUsersAuthority();

   int addUser(Users user);


   int deleteUser(Long id);


}
