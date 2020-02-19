package com.liuchongyang.cms;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.dao.ArticleMapper;
import com.liuchongyang.cms.dao.ArticleRepository;
import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ImportMysqlDB2Es {

	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Test
	public void testImportMysqlDB2Es() {
		//1.从mysql中查询所有的文章信息(已审核通过的文章信息  1  )
		Article article = new Article();
		//审核通过的文章才能刚被查到，存进es
		article.setStatus(1);
		PageInfo<Article> selects = articleService.selects(1, article);
//		List<Article> selects = articleMapper.selects(article);
		System.out.println(selects);
//	
		//2.把查询出来的文章，批量保存到es索引库
		articleRepository.saveAll(selects.getList());
	}
	
	@Test
	public void testdel() {
		articleRepository.deleteById(456);
		
	}
	
}
