package com.liuchongyang.cms.service;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.domain.Complain;
import com.liuchongyang.cms.vo.ComplainVO;

public interface ComplainService {
	//举报
	boolean insert(Complain complain);
		
	//查询举报
	PageInfo<Complain> selects(ComplainVO complainVO,int pageNum);
}
