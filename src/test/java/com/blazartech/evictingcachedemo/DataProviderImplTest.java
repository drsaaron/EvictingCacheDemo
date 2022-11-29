/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.blazartech.evictingcachedemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author aar1069
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    DataProviderImplTest.DataProviderImplTestConfiguration.class,
    ExpiringCacheConfiguration.class
})
@Slf4j
public class DataProviderImplTest {
    
    @Configuration
    @PropertySource("classpath:test.properties")
    public static class DataProviderImplTestConfiguration {
    
        @Bean
        public DataProviderImpl instance() {
            return new DataProviderImpl();
        }
    }
    
    @Autowired
    private DataProvider instance;
    
    public DataProviderImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Value("${cache.evict.interval}")
    private int evictInterval;
    
    /**
     * Test of getNext method, of class DataProviderImpl.
     */
    @Test
    public void testGetNext() {
        log.info("getNext");

        ReplyData result = instance.getNext(0);
        assertNotNull(result);
        assertEquals(0, result.getValue());
        
        // as it should be caching, calling again will get the same number
        ReplyData result2 = instance.getNext(0);
        assertEquals(result.getValue(), result2.getValue());
        
        // the cache should expire some seconds, so sleep a bit longer and try again.
        try {
            Thread.sleep((int) (evictInterval * 1.25));
        } catch (InterruptedException e) {}
        ReplyData result3 = instance.getNext(0);
        assertEquals(result.getValue() + 1, result3.getValue());
    }
    
}
