package com.liuchongyang.cms.service;

import java.util.List;

import com.liuchongyang.cms.domain.Category;
import com.liuchongyang.cms.domain.Channel;

public interface ChannelService {

	/**
	 * 
	 * @Title: selects 
	 * @Description: 所有栏目
	 * @param channel
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目Id 查询类型
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);
}
