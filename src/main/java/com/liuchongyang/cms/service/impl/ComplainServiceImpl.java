package com.liuchongyang.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.dao.ArticleMapper;
import com.liuchongyang.cms.dao.ComplainMapper;
import com.liuchongyang.cms.domain.Complain;
import com.liuchongyang.cms.service.ComplainService;
import com.liuchongyang.cms.util.CMSException;
import com.liuchongyang.cms.vo.ComplainVO;
import com.liuchongyang.common.utils.StringUtil;

@Service
public class ComplainServiceImpl implements ComplainService{

	@Resource
	private ComplainMapper complainMapper;
	@Resource
	private ArticleMapper articleMapper;

	@Override
	public boolean insert(Complain complain) {
		try {
			//校验举报的地址是否合法
			boolean b = StringUtil.isHttpUrl(complain.getUrl());
			if(!b) {
				throw new CMSException("url不合法");
			}
			//举报
			complainMapper.insert(complain);
			//增加次数
			articleMapper.updateComplainnum(complain.getArticleId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("举报失败");
			
		}
	
	}

	@Override
	public PageInfo<Complain> selects(ComplainVO complainVO, int pageNum) {
		PageHelper.startPage(pageNum, 3);
		List<Complain> list = complainMapper.selects(complainVO);
		return new PageInfo<Complain>(list);
		
	}
}
