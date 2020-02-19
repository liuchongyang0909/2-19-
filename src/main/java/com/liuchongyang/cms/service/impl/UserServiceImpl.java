package com.liuchongyang.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.dao.UserMapper;
import com.liuchongyang.cms.domain.User;
import com.liuchongyang.cms.service.UserService;
import com.liuchongyang.cms.util.CMSException;
import com.liuchongyang.cms.util.Md5Util;
import com.liuchongyang.common.utils.StringUtil;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Override
	public PageInfo<User> selects(int pageNum, String username) {
		PageHelper.startPage(pageNum, 3);
		List<User> user = userMapper.selects(username);
		PageInfo<User> info = new PageInfo<User>(user);
		return info;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(User user) {
		// 验证用户输入的信息,是否符合要求

				// 用户名不能为空
				if (!StringUtil.hasText(user.getUsername())) {
					throw new CMSException("用户名不能为空");
				}
				// 用户名是否重复
				User u = userMapper.selectByName(user.getUsername());
				if (null != u) {// 用户名已存在
					throw new CMSException("用户名已存在");
				}

				// 密码不能为空
				if (!(StringUtil.hasText(user.getPassword()))) {
					throw new CMSException("密码不能为空");
				}
				// 密码长度
				if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 12)) {
					throw new CMSException("密码长度应该在6-12之间能为空");
				}

				// 两次密码是否一致
				if (!user.getPassword().equals(user.getRepassword())) {
					throw new CMSException("两次密码不一致");
				}
				
				//注册用户默认的角色为0
				user.setRole("0");//0:普通用户,1:管理员
		        user.setCreated(new Date());//默认注册时间
		        user.setLocked(0);//用户状态,未锁定
		        user.setNickname(user.getUsername());
		      //为密码加密
		        user.setPassword(Md5Util.md5Encoding(user.getPassword()));
		        return userMapper.insert(user);
	}

	@Override
	public User login(User user) {
		// 用户名不能为空
				if (!StringUtil.hasText(user.getUsername())) {
					throw new CMSException("用户名不能为空");
				}

				// 密码不能为空
				if (!(StringUtil.hasText(user.getPassword()))) {
					throw new CMSException("密码不能为空");
				}
				//校验用户和密码是否正确
				User u = userMapper.selectByName(user.getUsername());
				if(null==u) {
					throw new CMSException("无此用户");
				}else {
					//用户存在,再比较密码
					if(!u.getPassword().equals(Md5Util.md5Encoding(user.getPassword()))) {
						throw new CMSException("密码不正确");	
					}
				}
				
				return u;
	}








}
