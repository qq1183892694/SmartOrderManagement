package com.example.demo3.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo3.entity.Users;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo3.entity.vo.UsersAuthorityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface UsersMapper extends BaseMapper<Users> {




   @Select("select u.*,a.sysName,a.characterName from Users u,Authority a where u.characterId=a.characterId")
   List<UsersAuthorityVO> findUsersAuthorityMapper();
}
