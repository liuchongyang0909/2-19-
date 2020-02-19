package com.liuchongyang.cms.service;



import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ArticleWithBLOBs;

public interface ArticleService {

	/**
	 * 
	 * @param pageNum 
	 * @Title: selects 
	 * @Description: 查询文章
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	PageInfo<Article> selects(int pageNum, Article article);
	/**
	 * 
	 * @Title: insertSelective 
	 * @Description: 增加文章
	 * @param record
	 * @return
	 * @return: int
	 */
	 int insertSelective(ArticleWithBLOBs record);
	 
	/**
	 *  
	 * @Title: selectByPrimaryKey 
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: ArticleWithBLOBs
	 */
	ArticleWithBLOBs selectByPrimaryKey(Integer id);
	
	/**
	 * 
	 * @Title: updateByPrimaryKeySelective 
	 * @Description: 文章修改
	 * @param article
	 * @return
	 * @return: int
	 */
	int updateByPrimaryKeySelective(ArticleWithBLOBs article);
	
	Integer[] getChannelId();
	void add(ArticleWithBLOBs awb);
}
