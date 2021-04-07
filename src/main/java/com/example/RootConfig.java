package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//类似于application.xml,事务,数据库等bean配置,需要配置到本类, 类似于applicationContext 上下文
@Configuration
@ImportResource("classpath:applicationContext.xml")
//配置注解扫描的包路径
@ComponentScan( basePackages={"com.example"},
        excludeFilters = { @ComponentScan.Filter(type= FilterType.ANNOTATION,value= EnableWebMvc.class)}
)

public class RootConfig {
}
