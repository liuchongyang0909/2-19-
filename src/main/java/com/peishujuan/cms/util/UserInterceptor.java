package com.peishujuan.cms.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.peishujuan.cms.dao.UserMapper;
import com.peishujuan.cms.domain.User;
/**
 * 
 * @ClassName: UserInterceptor 
 * @Description: 用户拦截器，只拦截进入个人中心的用户
 * @author: a'su's
 * @date: 2019年12月19日 下午3:25:53
 */
public class UserInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//规则：如果用户已经登陆，则不拦截
//		(false):如果request中，没有session，则返回null，如果有则返回session
//		(true):如果request中有，则返回，没有则创建session
		HttpSession session = request.getSession(false);
		if(null!=session) {
			//从session中获取普通用户的user对象
			Object object = session.getAttribute("user");
			if(null!=object) {//如果不为空，则返回true
				
				return true;//放行
			}
		}
		
		//如果用户存在cookie了，并和数据库的账户密码匹配不拦截
		if(rememberAutoLogin(request,request.getSession()))
			return true;
		
		//不符合要求，转发到登录页面
		request.setAttribute("error", "权限不符合，请重新登录");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		return false;
	}
	
	@Resource
	private UserMapper userMapper;

	
	private boolean rememberAutoLogin(HttpServletRequest request,HttpSession session) throws Exception {
		//从cookie获取账户密码
		Cookie cokUsername = CookieUtil.getCookieByName(request, "username");
		Cookie cokPassword = CookieUtil.getCookieByName(request, "password");
		// 从cookie获取用户名和密码
		if (null != cokUsername && null != cokPassword && null != cokUsername.getValue()
				&& null != cokPassword.getValue()) {
			String username = URLDecoder.decode(cokUsername.getValue(),"UTF-8");
			String password = URLDecoder.decode(cokPassword.getValue(),"UTF-8");
			// 从数据库获取用户名和密码并和cookie的比较。若一直则返回true
			User user = userMapper.selectByName(username);
			if (null != user && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				///系统里面用到了session ,需要重新存储session
				session.setAttribute("user", user);
				
				return true;
			}
		}
		return false;

	}
}
