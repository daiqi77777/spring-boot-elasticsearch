## 简介
Elasticsearch （ES）是一个基于 Lucene 的开源搜索引擎，它不但稳定、可靠、快速，而且也具有良好的水平扩展能力，是专门为分布式环境设计的。

## 版本介绍

spring-boot-starter-parent-1.5.9.RELEASE、spring-data-elasticsearch-2.1.9.RELEAS、elasticsearch-2.4.6(5.0+以上需要依赖JDK8)

截止2018年1月22日，ElasticSearch目前最新的已到6.1.2，但是spring-boot的更新速度远远跟不上ElasticSearch更新的速度，目前spring-boot支持的最新版本是elasticsearch-2.4.6。

## 安装说明
- 使用本地ElasticSearch服务
```
spring.data.elasticsearch.cluster-name=elasticsearch
#默认就是本机,如果要使用远程服务器，或者局域网服务器，那就需要在这里配置ip:prot;可以配置多个，以逗号分隔，相当于集群。
#spring.data.elasticsearch.cluster-nodes=192.168.1.180:9300
```
- 使用远程ElasticSearch服务
需要自行安装ElasticSearch，注意ElasticSearch版本尽量要与JAR包一致。

下载地址：https://www.elastic.co/downloads/past-releases/elasticsearch-2-4-6

