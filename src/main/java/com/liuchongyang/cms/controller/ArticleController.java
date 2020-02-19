package com.liuchongyang.cms.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.dao.ArticleRepository;
import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ArticleWithBLOBs;
import com.liuchongyang.cms.domain.Channel;
import com.liuchongyang.cms.service.ChannelService;
import com.liuchongyang.cms.util.HLUtils;

@RequestMapping("article")
@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	ChannelService channelService;
	@Autowired
	RedisTemplate redisTemplate;
	
	@GetMapping("selects")
	public String selects() {
		return "admin/article/srticles";
	}
	
	@RequestMapping("search")
	public String search(String key,Model m,Article article,@RequestParam(defaultValue = "1")int pageNum) {
		//左侧的显示栏目
		//0.封装查询条件
		article.setStatus(1);
		m.addAttribute("article", article);
		//1.查询出所有的栏目
		List<Channel> channels = channelService.selects();
		m.addAttribute("channels", channels);

//		-------------------------------------------------------------------------------------------
		Article last = new Article();
		last.setStatus(1);
		//这里我们需要用redis作为缓存来优化最新文章
		//1.从redis中查询最新文章
		List<Article> redisArticles = redisTemplate.opsForList().range("new_articles", 0, -1);
		m.addAttribute("redisArticles", redisArticles);
//		-------------------------------------------------------------------------------------------
		
		//从es索引库中查询(完成普通的检索无高亮)
//		List<Article> list = articleRepository.findByTitle(key);
//		PageInfo<Article> info = new PageInfo<Article>(list);
//		m.addAttribute("info", info);
		
		//高亮检索HighLight
		PageInfo<Article> info = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, 5, new String[] {"title"}, "id", key);
		m.addAttribute("key", key);
		m.addAttribute("info", info);
		
		return "index/index";
	}
	

}
