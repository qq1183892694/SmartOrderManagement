package com.example.demo3;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo3.entity.Users;
import com.example.demo3.entity.vo.UsersAuthorityVO;
import com.example.demo3.service.IUsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class Demo3ApplicationTests {


    @Autowired
    DataSource dataSource;

    @Autowired
    IUsersService usersService;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());


        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }


}
