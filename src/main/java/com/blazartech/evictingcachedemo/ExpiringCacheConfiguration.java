/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.evictingcachedemo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author aar1069
 */
@Configuration
@EnableCaching
@EnableScheduling
public class ExpiringCacheConfiguration {
    
    @Bean
    public CacheManager expiringCacheManager() {
        return new ConcurrentMapCacheManager();
    }
    
    @Bean
    public ExpiringCacheEvictor expiringCacheEvictor() {
        return new ExpiringCacheEvictor();
    }
}
