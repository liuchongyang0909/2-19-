package com.peishujuan.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peishujuan.cms.dao.CategoryMapper;
import com.peishujuan.cms.dao.ChannelMapper;
import com.peishujuan.cms.domain.Category;
import com.peishujuan.cms.domain.Channel;
import com.peishujuan.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService{

	@Autowired
	private ChannelMapper channelMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Channel> selects() {
		
		return channelMapper.selects();
	}
	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		
		return categoryMapper.selectsByChannelId(channelId);
	}

}
