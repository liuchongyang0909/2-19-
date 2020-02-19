package com.liuchongyang.cms.service;




import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.domain.User;

public interface UserService {

	//列表
	PageInfo<User> selects(int pageNum,String username);
	
	//修改
	 int updateByPrimaryKeySelective(User record);

	 //注册用户
	int insertSelective(User user);

	User login(User user);

	

}
