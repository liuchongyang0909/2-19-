package com.liuchongyang.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.liuchongyang.cms.domain.Article;
//继承ElasticsearchRepository这样就具备了crud的功能
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer>{

	//根据标题进行查找
	List<Article> findByTitle(String key);
}
