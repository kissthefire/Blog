package com.blog.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * Created by Dell on 2018/8/9.
 */
@Configuration
public class CorsConfig{

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config=new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("token");
        UrlBasedCorsConfigurationSource configSource=new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config);

        return new CorsFilter(configSource);
    }
}
