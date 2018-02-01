package com.itstyle.es.test;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itstyle.es.Application;
import com.itstyle.es.area.service.AreaService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class Areas {
	@Autowired
	private  AreaService areaService;
	@Autowired
	private  ElasticsearchTemplate elasticSearchTemplate;
	@Autowired
	private  ElasticsearchOperations  eslasticsearchOperations;
	
	//@Test
	public void  save(){
		try {
			System.out.println("组织数据开始");
			 
			System.out.println("组织数据结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//@Test
	public void  search(){
		areaService.searchAreaPage(0, 10, "搜索");
	}
	@Test
	public void  create(){
		XContentBuilder mapping = null;
		try {
			mapping = jsonBuilder()
			.startObject()
			.startObject("_ttl")
				.field("enabled",false)
				.endObject()
					.startObject("properties")
					.startObject("id")
							.field("type","long")
					.endObject()
					.startObject("name")
						.field("pid","int")
					.endObject()
					.startObject("shortname")
						.field("type","string")
					.endObject()
					.startObject("name")
						.field("type","string")
					.endObject()
					.startObject("mergerName")
						.field("type","string")
					.endObject()
					.startObject("level")
						.field("type","short")
					.endObject()
					.startObject("pinyin")
						.field("type","string")
					.endObject()
					.startObject("code")
						.field("type","string")
					.endObject()
					.startObject("zipCode")
						.field("type","string")
					.endObject()
					.startObject("location")
						.field("type","string")
					.endObject()
					.startObject("location")
						.field("type","geo_point")
					.endObject()
				.endObject()
			.endObject();
			PutMappingRequest request = Requests.putMappingRequest("elasticsearch").type("area").source(mapping);
			eslasticsearchOperations.getClient().admin().indices().putMapping(request).actionGet();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
