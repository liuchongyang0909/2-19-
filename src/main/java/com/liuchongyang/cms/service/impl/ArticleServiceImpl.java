package com.liuchongyang.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.dao.ArticleMapper;
import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ArticleWithBLOBs;
import com.liuchongyang.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleMapper articleMapper;
	@Override
	public PageInfo<Article> selects(int pageNum, Article article) {
		PageHelper.startPage(pageNum, 5);
		List<Article> list = articleMapper.selects(article);
		PageInfo<Article> info = new PageInfo<Article>(list);
		return info;
	}
	
	@Override
	public int insertSelective(ArticleWithBLOBs record) {
		return articleMapper.insertSelective(record);
	}

	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleWithBLOBs article) {

		return articleMapper.updateByPrimaryKeySelective(article);
	}

	@Override
	public Integer[] getChannelId() {
		return articleMapper.getChannelId();
	}

	@Override
	public void add(ArticleWithBLOBs awb) {
		articleMapper.add(awb);
	}

}
