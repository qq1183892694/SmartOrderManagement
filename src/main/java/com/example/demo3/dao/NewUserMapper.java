package com.example.demo3.dao;

import com.example.demo3.entity.NewUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2020/7/26.
 *
 * @author Zhouyong Tan
 */
@Mapper
public interface NewUserMapper {

    void insert(NewUser record);

}
