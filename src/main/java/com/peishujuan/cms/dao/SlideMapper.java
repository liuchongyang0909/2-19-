package com.peishujuan.cms.dao;

import java.util.List;

import com.peishujuan.cms.domain.Slide;

public interface SlideMapper {
	
	List<Slide> selects();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Slide record);

    int insertSelective(Slide record);

    Slide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slide record);

    int updateByPrimaryKey(Slide record);
}