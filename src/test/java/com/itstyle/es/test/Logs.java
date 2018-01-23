package com.itstyle.es.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itstyle.es.Application;
import com.itstyle.es.log.entity.SysLogs;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class Logs {
	@Autowired
	private  ElasticsearchTemplate elasticSearchTemplate;
	
	@Test
	public void  send(){
		System.out.println("组织数据开始");
		List<SysLogs> logList = new ArrayList<SysLogs>();
		for(long i=40000;i<100000;i++){
			SysLogs log = new SysLogs();
			log.setUsername("科帮网论坛");
			log.setOperation("我爱你锤子");
			log.setMethod("com.itstyle.es.log.controller.index()");
			log.setIp("192.168.1.68");
			log.setGmtCreate(new Timestamp(new Date().getTime()));
			log.setExceptionDetail("我的能力不足以支撑我的生活。据说人最痛苦的事情之一就是实力不足以支撑其野心。");
			log.setParams("{'name':'罗永浩','type':'锤子'}");
			log.setDeviceType((short)1);
			log.setPlatFrom((short)1);
			log.setLogType((short)1);
			log.setDeviceType((short)1);
			log.setId(i);
			log.setUserId(i);
			log.setTime(i);
			logList.add(log);
		}
		for(long i=100000;i<=200000;i++){
			SysLogs log = new SysLogs();
			log.setUsername("小柒博客");
			log.setOperation("生产锤子手机");
			log.setMethod("com.itstyle.es.log.controller.save()");
			log.setIp("192.168.1.69");
			log.setGmtCreate(new Timestamp(new Date().getTime()));
			log.setExceptionDetail("不存在所谓的坚持，做任何事总是有动机并且需要有正向结果驱动的。");
			log.setParams("{'name':'罗永浩','type':'雷神'}");
			log.setLogType((short)2);
			log.setDeviceType((short)2);
			log.setPlatFrom((short)2);
			log.setDeviceType((short)2);
			log.setId(i);
			log.setUserId(i);
			log.setTime(i);
			logList.add(log);
		}
		System.out.println("组织数据结束");
		bulkIndex(logList);
	}
	//批量同步或者插入数据
	public void bulkIndex(List<SysLogs> logList) {  
		long start = System.currentTimeMillis();
        int counter = 0;  
        try {  
            List<IndexQuery> queries = new ArrayList<>();  
            for (SysLogs log : logList) {  
                IndexQuery indexQuery = new IndexQuery();  
                indexQuery.setId(log.getId()+ "");  
                indexQuery.setObject(log);  
                indexQuery.setIndexName("elasticsearch");  
                indexQuery.setType("sysLog");  
                //也可以使用IndexQueryBuilder来构建  
                //IndexQuery index = new IndexQueryBuilder().withId(person.getId() + "").withObject(person).build();  
                queries.add(indexQuery);  
                if (counter % 1000 == 0) {  
                	elasticSearchTemplate.bulkIndex(queries);  
                    queries.clear();  
                    System.out.println("bulkIndex counter : " + counter);  
                }  
                counter++;  
            }  
            if (queries.size() > 0) {  
            	elasticSearchTemplate.bulkIndex(queries);  
            }
            long end = System.currentTimeMillis();
            System.out.println("bulkIndex completed use time:"+ (end-start));  
            
        } catch (Exception e) {  
            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());  
            throw e;  
        }  
    }  
}
