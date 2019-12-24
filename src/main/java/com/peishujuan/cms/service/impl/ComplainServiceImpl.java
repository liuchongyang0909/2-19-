package com.peishujuan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peishujuan.cms.dao.ArticleMapper;
import com.peishujuan.cms.dao.ComplainMapper;
import com.peishujuan.cms.domain.Complain;
import com.peishujuan.cms.service.ComplainService;
import com.peishujuan.cms.util.CMSException;
import com.peishujuan.cms.vo.ComplainVO;
import com.peishujuan.common.utils.StringUtil;

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
