package com.itstyle.es.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itstyle.es.Application;
import com.itstyle.es.log.entity.SysLogs;
import com.itstyle.es.log.service.LogService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class Logs {
	@Autowired
	private LogService logService;
	
	@Test
	public void  save(){
		System.out.println("开始插入");
		SysLogs log = new SysLogs();
		log.setUsername("科帮网");
		log.setOperation("你个锤子");
		log.setMethod("com.itstyle.es.log.controller.index()");
		log.setIp("192.168.1.66");
		log.setExceptionDetail("真正坚强并且心态健康的人，被朋友出卖、被亲人误解、被爱人抛弃，也不会对人性失去信心。");
		log.setParams("{'name':'罗永浩'}");
		log.setDeviceType((short)1);
		log.setPlatFrom((short)1);
		log.setLogType((short)1);
		for(long i=1;i<5000;i++){
			log.setId(i);
			log.setUserId(i);
			log.setTime(i);
			//logService.saveLog(log);
		}
		log.setLogType((short)2);
		log.setDeviceType((short)2);
		log.setPlatFrom((short)2);
		for(long i=5000;i<=10000;i++){
			log.setId(i);
			log.setUserId(i);
			log.setTime(i);
			//logService.saveLog(log);
		}
		System.out.println("结束插入");
	}
}
