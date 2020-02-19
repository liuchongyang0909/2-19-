package com.liuchongyang.cms;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.liuchongyang.cms.dao.ArticleRepository;
import com.liuchongyang.cms.domain.Article;
import com.liuchongyang.cms.domain.ArticleWithBLOBs;
import com.liuchongyang.cms.service.ArticleService;
import com.liuchongyang.common.utils.StreamUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class SendTest {
	@Autowired
	private ArticleService service;
	
	@Autowired
	private ArticleRepository re;
	
	@Autowired
	private KafkaTemplate kafkaTemplate;
	
	@Test
	public void send() throws IOException {
		//读取文章
				File file = new File("D:/Spider");
				File[] listFiles = file.listFiles();
				for (File file2 : listFiles) {
//					System.out.println(file2.getName());
					String title = file2.getName().replace(".txt", "");
					String content = StreamUtil.readFile(file2, "utf8");
//					System.out.println(content);
					ArticleWithBLOBs abs = new ArticleWithBLOBs();
					abs.setTitle(title);//标题
					
					abs.setContent(content);//内容
					
					abs.setSummary(content.substring(20));//摘要
					
					abs.setHits(((int)(Math.random()*10))%2);//点击量
					
					int i = (int) (Math.round(Math.random()*2)-1);//状态
					
					abs.setStatus(i);//状态
					
					abs.setHot(((int)(Math.random()*10))%2);//热门
					
					Integer[]  cid = service.getChannelId();
					abs.setChannelId((int)(Math.random() * cid.length));
					
					String jsonString = JSON.toJSONString(abs);
					kafkaTemplate.send("articles",jsonString);
					
				}
	}
}
