package com.liuchongyang.cms.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import com.liuchongyang.cms.dao.ArticleRepository;
import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ArticleWithBLOBs;
import com.liuchongyang.cms.domain.Category;
import com.liuchongyang.cms.domain.Channel;
import com.liuchongyang.cms.domain.ShouChang;
import com.liuchongyang.cms.domain.User;
import com.liuchongyang.cms.service.ArticleService;
import com.liuchongyang.cms.service.ChannelService;
import com.liuchongyang.cms.service.ShouChangService;
/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: a'su's
 * @date: 2019年12月12日 下午10:54:37
 */
@RequestMapping("my")
@Controller
public class MyController {
	@Autowired
	private ArticleRepository re;

	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ShouChangService shouChangService;

	
	//个人中心首页
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "my/index";
	}
	/**
	 * 
	 * @Title: publish 
	 * @Description: 去发布文章
	 * @return
	 * @return: String
	 */
	@GetMapping("article/publish")
	public String publish() {
		
		return "my/article/publish";
	}
	/**
	 * 
	 * @Title: selectChannels 
	 * @Description: 所有栏目
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@RequestMapping("channel/selects")
	public List<Channel> selectChannels(){
		
		return channelService.selects();
	}
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目查询分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@RequestMapping("category/selectsByChannelId")
	public List<Category> selectsByChannelId(Integer channelId){
		return channelService.selectsByChannelId(channelId);
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 发布文章
	 * @param file
	 * @param article
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("article/publish")
	public boolean publish(MultipartFile file,ArticleWithBLOBs article,HttpServletRequest request) throws Exception {
		String path = "D:/pic";
		if(!file.isEmpty()) {
			//获取原始文件名称
			String filename = file.getOriginalFilename();
			//防止文件重名
			String newFilename = UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			//把文件写入硬盘
			file.transferTo(new File(path,newFilename));
			//数据库存储文件地址
			article.setPicture(newFilename);
		}
		
		article.setCreated(new Date());//发布时间默认系统时间
		article.setStatus(0);//文章状态 0:待审核
		article.setHits(0);//点击量
		article.setDeleted(0);//是否删除// 0:未删除
		article.setUpdated(new Date());//更新时间
		//从session获取当前登录人的信息。用来指定发布人
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		article.setUserId(u.getId());//发布人
		article.setHot(0);//非热文章
		
		re.save(article);
		
		return articleService.insertSelective(article)>0;
	}
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 查询文章
	 * @param m
	 * @param article
	 * @param pageNum
	 * @return
	 * @return: String
	 */
	@GetMapping("article/articles")
	public String articles(Model m,HttpServletRequest request, Article article,@RequestParam(defaultValue = "1")int pageNum) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		article.setUserId(u.getId());
		
		PageInfo<Article> info = articleService.selects(pageNum, article);
		m.addAttribute("info", info);
		return "my/article/articles";
	}
	/**
	 * 
	 * @Title: article 
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("article/article")
	public String article(Integer id,Model m) {
		
		ArticleWithBLOBs a = articleService.selectByPrimaryKey(id);
		m.addAttribute("a", a);
		return "my/article/article";
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("article/update")
	public boolean update(ArticleWithBLOBs article) {
		return articleService.updateByPrimaryKeySelective(article)>0;
	}
	
	/**
	 * 
	 * @Title: getShouChangList
	 * @Description: 收藏文章列表
	 * @param pageNum
	 * @param m
	 * @return
	 * @return: String
	 */
	@GetMapping("article/shouChang")
	public String getShouChangList(@RequestParam(defaultValue = "1")Integer pageNum, Model m) {
		PageInfo<ShouChang> info = shouChangService.getShouChangList(pageNum);
		
		m.addAttribute("info", info);
		
		return "my/article/shouChang";
	}
	
	/**
	 * 
	 * @Title: delShouChang 
	 * @Description: 删除收藏文章
	 * @param id
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("article/delShouChang")
	public Object delShouChang(Integer id) {
		try {
			shouChangService.delShouChang(id);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: shouchang 
	 * @Description: 收藏文章
	 * @param id
	 * @param m
	 * @return
	 * @return: String
	 */
	@RequestMapping("article/shouchang")
	public String shouchang(Integer id, Model m) {
		Article art = shouChangService.getArticle(id);
		String title = art.getTitle();
		
		ArticleWithBLOBs a = articleService.selectByPrimaryKey(id);
		m.addAttribute("a", a);
		
		
		shouChangService.saveShouChang(title, id);
		
		return "/index/article";
	}
}
