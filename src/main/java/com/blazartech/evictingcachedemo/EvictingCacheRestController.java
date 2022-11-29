/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.evictingcachedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aar1069
 */
@RestController
@Slf4j
public class EvictingCacheRestController {
    
    @Autowired 
    private DataProvider provider;
    
    @GetMapping("/value/{id}")
    public ReplyData getValue(@PathVariable int id) {
        log.info("getting value");
        return provider.getNext(id);
    }
}
