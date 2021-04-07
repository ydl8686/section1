package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//相当于视图解析器的作用,同时配置扫描controller 的路径
@Configuration
@EnableWebMvc //启动 springMVC
@ComponentScan(value = {"com.example.web"})	//配置组件扫描
public class WebConfig extends WebMvcConfigurerAdapter {
    //配置jsp 视图解析器
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");//设置预加载路径/目录
        resolver.setSuffix(".jsp");  //设置允许后缀
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    //配置静态资源的处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();//对静态资源的请求转发到容器缺省的servlet,而不使用 DispatcherServlet
    }

}
