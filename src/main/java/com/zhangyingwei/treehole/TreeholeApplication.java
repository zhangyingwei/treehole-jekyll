package com.zhangyingwei.treehole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ThymeleafAutoConfiguration.class})
@EnableCaching
@Configuration
@MapperScan(basePackages = {"com.zhangyingwei.treehole"})
@PropertySource("classpath:treehole.properties")
public class TreeholeApplication {
	public static void main(String[] args) {
		SpringApplication.run(TreeholeApplication.class, args);
	}
}