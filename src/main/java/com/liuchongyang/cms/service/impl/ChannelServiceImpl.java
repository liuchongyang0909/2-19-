package com.liuchongyang.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuchongyang.cms.dao.CategoryMapper;
import com.liuchongyang.cms.dao.ChannelMapper;
import com.liuchongyang.cms.domain.Category;
import com.liuchongyang.cms.domain.Channel;
import com.liuchongyang.cms.service.ChannelService;
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
