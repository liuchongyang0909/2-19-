package com.liuchongyang.cms.ShouChang;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.cms.domain.ShouChang;
import com.liuchongyang.cms.service.ShouChangService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestGetList {
	
	@Autowired
	private ShouChangService shouChangService;
	
	@Test
	public void testSave() {
		shouChangService.saveShouChang("4444", 2);
	}
	
	@Test 
	public void testList() {
		PageInfo<ShouChang> list = shouChangService.getShouChangList(1);
	}
	
	@Test
	public void testDel() {
		shouChangService.delShouChang(2);
	}
}
