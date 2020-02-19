package com.liuchongyang.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ShouChang;

public interface ShouChangMapper {

	List<ShouChang> getShouChangList();

	void delShouChang(@Param("id")Integer id);

	Article getArticle(@Param("id")Integer id);

	void saveShouChang(@Param("title")String title, @Param("id")Integer id);

}
