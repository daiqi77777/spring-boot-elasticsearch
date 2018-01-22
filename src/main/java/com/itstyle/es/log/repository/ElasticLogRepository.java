package com.itstyle.es.log.repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.itstyle.es.log.entity.SysLogs;
public interface  ElasticLogRepository extends ElasticsearchRepository<SysLogs,Long> {

}
