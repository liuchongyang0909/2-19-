package com.peishujuan.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peishujuan.cms.domain.User;

public interface UserMapper {
	//列表
	List<User> selects(@Param("username")String username);
	//按用户名查询
	User selectByName(@Param("username")String username);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    //支持动态sql
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}