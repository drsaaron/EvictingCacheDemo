/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.evictingcachedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class DataProviderImpl implements DataProvider {

    private static int VALUE = 0;
    
    @Override
    @Cacheable(cacheManager = "expiringCacheManager", cacheNames = "valueCache", key = "#n")
    public ReplyData getNext(int n) {
        log.info("getting next value");
        ReplyData d = new ReplyData();
        d.setValue(VALUE++);
        return d;
    }
    
}
