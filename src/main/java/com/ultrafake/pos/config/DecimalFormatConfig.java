package com.ultrafake.pos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;

@Configuration
public class DecimalFormatConfig {
    private static final String CURRENCY_PATTERN = "#,###,##0.00";

    @Bean
    public DecimalFormat decimalFormat() {
        DecimalFormat decimalFormat = new DecimalFormat(CURRENCY_PATTERN);
        return decimalFormat;
    }
}
