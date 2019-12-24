package com.peishujuan.cms.service;

import com.github.pagehelper.PageInfo;
import com.peishujuan.cms.domain.Complain;
import com.peishujuan.cms.vo.ComplainVO;

public interface ComplainService {
	//举报
	boolean insert(Complain complain);
		
	//查询举报
	PageInfo<Complain> selects(ComplainVO complainVO,int pageNum);
}
