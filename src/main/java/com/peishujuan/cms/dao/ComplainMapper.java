package com.peishujuan.cms.dao;

import java.util.List;

import com.peishujuan.cms.domain.Complain;
import com.peishujuan.cms.vo.ComplainVO;

public interface ComplainMapper {

	//增加
	int insert(Complain complain);


	
	//查询举报
	List<Complain> selects(ComplainVO complainVO);
	

}
