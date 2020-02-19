package com.liuchongyang.cms.dao;

import java.util.List;

import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ArticleWithBLOBs;

public interface ArticleMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询文章
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selects(Article article);
	
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Integer articleId);
    
    int updateComplainnum(Integer articleId);//更新举报数量

	Integer[] getChannelId();

	void add(ArticleWithBLOBs awb);

	
}