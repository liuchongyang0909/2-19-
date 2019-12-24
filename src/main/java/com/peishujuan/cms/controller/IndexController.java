package com.peishujuan.cms.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.peishujuan.cms.domain.Article;
import com.peishujuan.cms.domain.ArticleWithBLOBs;
import com.peishujuan.cms.domain.Category;
import com.peishujuan.cms.domain.Channel;
import com.peishujuan.cms.domain.Complain;
import com.peishujuan.cms.domain.Slide;
import com.peishujuan.cms.domain.User;
import com.peishujuan.cms.service.ArticleService;
import com.peishujuan.cms.service.ChannelService;
import com.peishujuan.cms.service.ComplainService;
import com.peishujuan.cms.service.SlideService;
import com.peishujuan.cms.util.CMSException;

@Controller
public class IndexController {

	@Autowired
	private ChannelService channelService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private ComplainService complainService;
	
	@RequestMapping(value = {"","/","index"})
	public String index(Model m,Article article,@RequestParam(defaultValue = "1")int pageNum) {
		//0.封装查询条件
		article.setStatus(1);
		m.addAttribute("article", article);
		
		//1.查询出所有的栏目
		List<Channel> channels = channelService.selects();
		m.addAttribute("channels", channels);
		
		//如果栏目为空，则默认显示推荐的文章
		if(null==article.getChannelId()) {
			//1.查询广告
			List<Slide> slides = slideService.selects();
			m.addAttribute("slides", slides);
			
			Article article2 = new Article();
			article2.setHot(1);//1.推荐文章标志
			article2.setStatus(1);//2.审核过的文章
			//2.查询推荐下所有文章
			PageInfo<Article> info = articleService.selects(pageNum, article2);
			m.addAttribute("info", info);
			
		}
		
		//如果栏目不为空，则查询栏目下所有分类
		if(null!=article.getChannelId()) {
			List<Category> categorys = channelService.selectsByChannelId(article.getChannelId());
			//查询栏目下所有文章
			PageInfo<Article> info = articleService.selects(pageNum, article);
			m.addAttribute("info", info);
			m.addAttribute("categorys", categorys);
			//如果分类不为空，则查询分类下文章
			if(null!=article.getCategoryId()) {
				PageInfo<Article> info2 = articleService.selects(pageNum, article);
				m.addAttribute("info2", info2);
			}
		}
		//页面右侧显示最近发布的 10篇文章
		Article last = new Article();
		last.setStatus(1);
		PageInfo<Article> lastInfo = articleService.selects(1, last);
		
		m.addAttribute("lastInfo", lastInfo);
		return "index/index";
	}
		
	//查询单个文章
	@GetMapping("article")	
	public String article(Integer id,Model m) {
		ArticleWithBLOBs a = articleService.selectByPrimaryKey(id);
		m.addAttribute("a", a);
		return "/index/article";
	}
	
	//去举报
	@GetMapping("complain")
	public String complain(Model model ,Article article,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null!=user) {//如果有户登录
			article.setUser(user);//封装举报人和举报的文章
			model.addAttribute("article", article);
			return "index/complain";//转发到举报页面
		}
		return "redirect:/passport/login";//没有登录，先去登录
	}
	
	//执行举报
	@ResponseBody
	@PostMapping("complain")
	public boolean complain(Model m,MultipartFile  file, Complain complain) {
		if(null!=file &&!file.isEmpty()) {
			String path="f:/pic/";
			String filename = file.getOriginalFilename();
		   String newFileName =UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			File f = new File(path,newFileName);
			
				try {
					file.transferTo(f);
					complain.setPicurl(newFileName);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
		try {
			//执行举报
			complainService.insert(complain);
			return true;
		} catch (CMSException e) {
			e.printStackTrace();
			m.addAttribute("error", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("error", "系统错误，请联系管理员");
		}
		return false;
		
	}
}
