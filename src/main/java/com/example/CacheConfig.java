package com.example;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching //启用缓存
public class CacheConfig {
    @Bean
//    这里可以返回不同的缓存机制（Spring支持的）
    public EhCacheCacheManager cacheManager(CacheManager ehCacheCacheManager) {
        return new EhCacheCacheManager(ehCacheCacheManager);
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheFactoryBean =
                new EhCacheManagerFactoryBean();
        ehCacheFactoryBean.setConfigLocation(
                new ClassPathResource("cache.xml"));
        return ehCacheFactoryBean;
    }
}
