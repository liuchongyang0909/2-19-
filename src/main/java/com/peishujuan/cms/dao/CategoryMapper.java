package com.peishujuan.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peishujuan.cms.domain.Category;

public interface CategoryMapper {
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目Id 查询类型
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(@Param("channelId")Integer channelId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}