/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.evictingcachedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * define a component that will clear all caches in the evictingCacheManager
 * (@see CacheConfiguration) on a periodic basis.
 * 
 * @author aar1069
 */
@Slf4j
public class ExpiringCacheEvictor {

    @Autowired
    private CacheManager evictingCacheManager;

    public void evictAllCaches() {
        evictingCacheManager.getCacheNames()
                .stream()
                .peek(cacheName -> log.info("clearing cache " + cacheName))
                .map(cacheName -> evictingCacheManager.getCache(cacheName))
                .forEach(cache -> cache.clear());
    }

    @Scheduled(fixedRateString = "${cache.evict.interval}")
    public void evictAllcachesAtIntervals() {
        log.info("clearing my caches");
        evictAllCaches();
    }
}
