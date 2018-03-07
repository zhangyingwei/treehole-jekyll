package com.zhangyingwei.treehole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.cloud.CloudAutoConfiguration;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		ThymeleafAutoConfiguration.class,
		CacheAutoConfiguration.class,
		JacksonAutoConfiguration.class,
		JmxAutoConfiguration.class,
		ActiveMQAutoConfiguration.class,
		ArtemisAutoConfiguration.class,
		BatchAutoConfiguration.class,
		CassandraAutoConfiguration.class,
		CassandraDataAutoConfiguration.class,
		CassandraRepositoriesAutoConfiguration.class,
		CloudAutoConfiguration.class,
		CouchbaseAutoConfiguration.class,
		CouchbaseDataAutoConfiguration.class,
		CouchbaseRepositoriesAutoConfiguration.class
})
//@EnableCaching
@Configuration
@MapperScan(basePackages = {"com.zhangyingwei.treehole"})
@PropertySource("classpath:treehole.properties")
public class TreeholeApplication {
	public static void main(String[] args) {
		SpringApplication.run(TreeholeApplication.class, args);
	}
}