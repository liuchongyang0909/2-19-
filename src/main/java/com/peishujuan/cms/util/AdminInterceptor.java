package com.peishujuan.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: UserInterceptor 
 * @Description: 管理员拦截器，只拦截进入管理员后台的用户
 * @author: a'su's
 * @date: 2019年12月19日 下午3:25:53
 */
public class AdminInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//规则：如果用户已经登陆，则不拦截
//		(false):如果request中，没有session，则返回null，如果有则返回session
//		(true):如果request中有，则返回，没有则创建session
		HttpSession session = request.getSession(false);
		if(null!=session) {
			//从session中获取管理员的admin对象
			Object object = session.getAttribute("admin");
			if(null!=object) {//如果不为空，则返回true
				
				return true;//放行
			}
		}
		//不符合要求，转发到登录页面
		request.setAttribute("error", "权限不符合，请重新登录");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		return false;
	}
}
