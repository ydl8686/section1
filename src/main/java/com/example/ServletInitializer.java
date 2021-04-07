package com.example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//这个类作用相当于DispatcherServlet
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};        //指定Web配置类
    }

    @Override
    protected String[] getServletMappings() {//将 DispatcherServlet 映射到 "/" 路径
        return new String[]{"/"};

    }
}