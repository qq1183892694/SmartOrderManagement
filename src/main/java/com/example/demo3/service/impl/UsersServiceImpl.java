package com.example.demo3.service.impl;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo3.entity.Users;
import com.example.demo3.dao.UsersMapper;
import com.example.demo3.entity.vo.UsersAuthorityVO;
import com.example.demo3.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Autowired
    private  UsersMapper usersMapper;

    @Override
    public Users findUserByUserName(String username) {


        Wrapper<Users> wrapper=new EntityWrapper<Users>();
        wrapper.eq("userLoginName",username);
        List<Users> list = usersMapper.selectList(wrapper);
        if(list!=null && list.size()!=0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<UsersAuthorityVO> findUsersAuthority() {
        return usersMapper.findUsersAuthorityMapper();
    }

//    @Override
//    public Page<UsersAuthorityVO> findUsersAuthority(int pageNo, int pageSize) {
//        Page<UsersAuthorityVO> page=new Page<>(pageNo,pageSize);
//        List<UsersAuthorityVO> users = usersMapper.findUsersAuthorityMapper(page);
//        page.setRecords(users);
//        return page;
//    }

    @Override
    public int addUser(Users user) {
        return usersMapper.insert(user);
    }


    @Override
    public int deleteUser(Long id) {
        return usersMapper.deleteById(id);
    }


}
