package com.liuchongyang.cms.dao;

import java.util.List;

import com.liuchongyang.cms.domain.Complain;
import com.liuchongyang.cms.vo.ComplainVO;

public interface ComplainMapper {

	//增加
	int insert(Complain complain);


	
	//查询举报
	List<Complain> selects(ComplainVO complainVO);
	

}
