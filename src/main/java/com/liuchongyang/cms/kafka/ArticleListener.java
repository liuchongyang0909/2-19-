package com.liuchongyang.cms.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.liuchongyang.cms.domain.ArticleWithBLOBs;
import com.liuchongyang.cms.service.ArticleService;

public class ArticleListener implements MessageListener<String, String>{

	@Autowired
	ArticleService articleService;
	
	//监听消息
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		//开始接受消息
		String jsonString = data.value();
//		System.err.println("接收到消息！~~成功");
		//1.将接收到的消息转成对象
		ArticleWithBLOBs awb = JSON.parseObject(jsonString,ArticleWithBLOBs.class);
//		//2.保存到数据库
		articleService.add(awb);
		
		System.out.println(awb.toString());
	}

}
