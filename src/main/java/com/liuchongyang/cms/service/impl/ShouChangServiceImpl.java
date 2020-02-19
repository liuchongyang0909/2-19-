package com.liuchongyang.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.dao.ShouChangMapper;
import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ShouChang;
import com.liuchongyang.cms.service.ShouChangService;

@Service
public class ShouChangServiceImpl implements ShouChangService {

	@Autowired
	private ShouChangMapper shouChangMapper;
	
	@Override
	public PageInfo<ShouChang> getShouChangList(Integer pageNum) {
		PageHelper.startPage(pageNum, 5);
		
		List<ShouChang> list = shouChangMapper.getShouChangList();
		
		PageInfo<ShouChang> info = new PageInfo<ShouChang>(list);
		
		return info;
	}

	@Override
	public void delShouChang(Integer id) {
		shouChangMapper.delShouChang(id);
	}

	@Override
	public Article getArticle(Integer id) {
		Article a = shouChangMapper.getArticle(id);
		return a;
	}

	@Override
	public void saveShouChang(String title, Integer id) {
		shouChangMapper.saveShouChang(title, id);
	}

}
