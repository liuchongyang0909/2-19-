package com.liuchongyang.cms.ShouChang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liuchongyang.common.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestShouChang {
	@Test
	public void SaveShouChang() {
		boolean httpUrl2 = StringUtil.isHttpUrl2("www.baidu.com");
		
		System.out.println(httpUrl2);
	}
}
