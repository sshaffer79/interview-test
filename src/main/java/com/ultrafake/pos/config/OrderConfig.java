package com.ultrafake.pos.config;

import com.ultrafake.pos.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * The configuration to create a cache for storing orders
 */
@Configuration
public class OrderConfig {
    @Bean
    public Map<String, Order> orderCache() {
        Map<String, Order> orderCache = new HashMap<>();
        return orderCache;
    }
}
