package com.peishujuan.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.peishujuan.cms.domain.Article;
import com.peishujuan.cms.domain.ArticleWithBLOBs;
import com.peishujuan.cms.domain.Complain;
import com.peishujuan.cms.domain.User;
import com.peishujuan.cms.service.ArticleService;
import com.peishujuan.cms.service.ComplainService;
import com.peishujuan.cms.service.UserService;
import com.peishujuan.cms.vo.ComplainVO;
@RequestMapping("admin")
@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ComplainService complainService;
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入admin后台首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"/","insex",""})
	public String index() {
		return "admin/index";
	}
	/**
	 * 
	 * @Title: article 
	 * @Description: 文章列表
	 * @param m
	 * @param pageNum
	 * @param article
	 * @return
	 * @return: String
	 */
	@RequestMapping("article/selects")
	public String articles(Model m, @RequestParam(defaultValue = "1")int pageNum,Article article) {
		//默认文章审核状态为待审
		if(article.getStatus()==null) {
			article.setStatus(0);
		}
		
		
		PageInfo<Article> info = articleService.selects(pageNum,article);
		m.addAttribute("info", info);
		m.addAttribute("article", article);
		return "admin/article/articles";
		
	}
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表
	 * @param m
	 * @param pageNum
	 * @param username
	 * @return
	 * @return: String
	 */
	@RequestMapping("user/selects")
	public String selects(Model m, @RequestParam(defaultValue = "1")int pageNum,String username) {
		PageInfo<User> info = userService.selects(pageNum,username);
		m.addAttribute("info", info);
		m.addAttribute("username", username);
		return "admin/user/users";
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改用户
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("user/update")
	public boolean update(User user) {
		
		return userService.updateByPrimaryKeySelective(user)>0;
	}
	
	/**
	 * 
	 * @Title: select 
	 * @Description: 查看文章详情
	 * @return
	 * @return: String
	 */
	@GetMapping("article/select")
	public String select(Model m,Integer id) {
		ArticleWithBLOBs a = articleService.selectByPrimaryKey(id);
		m.addAttribute("a", a);
		return "admin/article/article";
		
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 审核文章
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("article/update")
	public boolean update(ArticleWithBLOBs article) {
		return articleService.updateByPrimaryKeySelective(article)>0;
	}
	
	//查询投诉
		@GetMapping("article/complains")
		public String complain(Model model ,ComplainVO complainVO , @RequestParam(defaultValue = "1")int pageNum) {
			PageInfo<Complain> info = complainService.selects(complainVO,pageNum);
			model.addAttribute("info", info);
			model.addAttribute("complainVO", complainVO);
			return "admin/article/complains";
		}

}



	
	