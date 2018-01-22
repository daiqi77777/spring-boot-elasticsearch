## 开发环境

JDK1.7、Maven、Eclipse、SpringBoot1.5.9、elasticsearch2.4.6、Dubbox2.8.4、zookeeper3.4.6

## 项目结构

```
     
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─itstyle
│  │  │          └─es
│  │  │              │  Application.java
│  │  │              │  
│  │  │              ├─common
│  │  │              │  ├─constant
│  │  │              │  │      PageConstant.java
│  │  │              │  │      
│  │  │              │  └─interceptor
│  │  │              │          MyAdapter.java
│  │  │              │          
│  │  │              └─log
│  │  │                  ├─controller
│  │  │                  │      LogController.java
│  │  │                  │      
│  │  │                  ├─entity
│  │  │                  │      Pages.java
│  │  │                  │      SysLogs.java
│  │  │                  │      
│  │  │                  ├─repository
│  │  │                  │      ElasticLogRepository.java
│  │  │                  │      
│  │  │                  └─service
│  │  │                      │  LogService.java
│  │  │                      │  
│  │  │                      └─impl
│  │  │                              LogServiceImpl.java
│  │  │                              
│  │  ├─resources
│  │  │  │  application-dev.properties
│  │  │  │  application-prod.properties
│  │  │  │  application-test.properties
│  │  │  │  application.yml
│  │  │  │  spring-context-dubbo.xml
│  │  │  │  
│  │  │  ├─static
│  │  │  │  ├─iview
│  │  │  │  │  │  iview.css
│  │  │  │  │  │  iview.min.js
│  │  │  │  │  │  
│  │  │  │  │  └─fonts
│  │  │  │  │          ionicons.eot
│  │  │  │  │          ionicons.svg
│  │  │  │  │          ionicons.ttf
│  │  │  │  │          ionicons.woff
│  │  │  │  │          
│  │  │  │  ├─jquery
│  │  │  │  │      jquery-3.2.1.min.js
│  │  │  │  │      
│  │  │  │  └─vue
│  │  │  │          vue.min.js
│  │  │  │          
│  │  │  └─templates
│  │  │      └─log
│  │  │              index.html
│  │  │              
│  │  └─webapp
│  │      │  index.jsp
│  │      │  
│  │      └─WEB-INF
│  │              web.xml
│  │              
│  └─test
│      └─java
│          └─com
│              └─itstyle
│                  └─es
│                      └─test
│                              Logs.java
│                              


```
## 项目截图

![搜索查询](https://gitee.com/uploads/images/2018/0122/160851_95e54741_87650.png "ES_index.png")

#### 演示网址：http://es.52itstyle.com

## 版本介绍

spring-boot-starter-parent-1.5.9.RELEASE、spring-data-elasticsearch-2.1.9.RELEAS、elasticsearch-2.4.6(5.0+以上需要依赖JDK8)

截止2018年1月22日，ElasticSearch目前最新的已到6.1.2，但是spring-boot的更新速度远远跟不上ElasticSearch更新的速度，目前spring-boot支持的最新版本是elasticsearch-2.4.6。

## 服务说明

### 使用本地ElasticSearch服务(application-dev.properties)
```
spring.data.elasticsearch.cluster-name=elasticsearch
#默认就是本机,如果要使用远程服务器，或者局域网服务器，那就需要在这里配置ip:prot;可以配置多个，以逗号分隔，相当于集群。
#spring.data.elasticsearch.cluster-nodes=192.168.1.180:9300
```
### 使用远程ElasticSearch服务(application-dev.properties)

- 需要自行安装ElasticSearch，注意ElasticSearch版本尽量要与JAR包一致。
 
- 下载地址：https://www.elastic.co/downloads/past-releases/elasticsearch-2-4-6

- 安装说明：http://www.52itstyle.com/thread-20114-1-1.html 

- 新版本不建议使用root用户启动，需要自建ElasticSearch用户，也可以使用以下命令启动 elasticsearch -Des.insecure.allow.root=true -d 或者在elasticsearch中加入ES_JAVA_OPTS="-Des.insecure.allow.root=true"。



## 全文检索

### 简介

Elasticsearch （ES）是一个基于 Lucene 的开源搜索引擎，它不但稳定、可靠、快速，而且也具有良好的水平扩展能力，是专门为分布式环境设计的。

### 特性

- 安装方便：没有其他依赖，下载后安装非常方便；只用修改几个参数就可以搭建起来一个集群
- JSON：输入/输出格式为 JSON，意味着不需要定义 Schema，快捷方便
- RESTful：基本所有操作（索引、查询、甚至是配置）都可以通过 HTTP 接口进行
- 分布式：节点对外表现对等（每个节点都可以用来做入口）；加入节点自动均衡
- 多租户：可根据不同的用途分索引；可以同时操作多个索引

### 集群

其中一个节点就是一个 ES 进程，多个节点组成一个集群。一般每个节点都运行在不同的操作系统上，配置好集群相关参数后 ES 会自动组成集群（节点发现方式也可以配置）。集群内部通过 ES 的选主算法选出主节点，而集群外部则是可以通过任何节点进行操作，无主从节点之分（对外表现对等/去中心化，有利于客户端编程，例如故障重连）。

### 索引
![输入图片说明](https://gitee.com/uploads/images/2018/0122/113919_debba243_87650.png "ES.png")

### 分片

ES 是一个分布式系统，我们一开始就应该以集群的方式来使用它。它保存索引时会选择适合的“主分片”（Primary Shard），把索引保存到其中（我们可以把分片理解为一块物理存储区域）。分片的分法是固定的，而且是安装时候就必须要决定好的（默认是 5），后面就不能改变了。

既然有主分片，那肯定是有“从”分片的，在 ES 里称之为“副本分片”（Replica Shard）。副本分片主要有两个作用：

- 高可用：某分片节点挂了的话可走其他副本分片节点，节点恢复后上面的分片数据可通过其他节点恢复
- 负载均衡：ES 会自动根据负载情况控制搜索路由，副本分片可以将负载均摊

### RESTful

这个特性非常方便，最关键的是 ES 的 HTTP 接口不只是可以进行业务操作（索引/搜索），还可以进行配置，甚至是关闭 ES 集群。下面我们介绍几个很常用的接口：

- /_cat/nodes?v：查集群状态
- /_cat/shards?v：查看分片状态
- /${index}/${type}/_search：搜索

v 是 verbose 的意思，这样可以更可读（有表头，有对齐），_cat 是监测相关的 APIs，/_cat?help 来获取所有接口。${index} 和 ${type} 分别是具体的某一索引某一类型，是分层次的。我们也可以直接在所有索引所有类型上进行搜索：/_search。

