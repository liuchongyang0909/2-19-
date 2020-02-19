package com.liuchongyang.cms.service;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ShouChang;

public interface ShouChangService {

	PageInfo<ShouChang> getShouChangList(Integer pageNum);

	void delShouChang(Integer id);

	Article getArticle(Integer id);

	void saveShouChang(String title, Integer id);

}
