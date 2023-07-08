package com.autumn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author 侯亚雄
 * @createData 2023/6/9 22:36
 * @description 前端跨域处理
 **/
@Configuration
public class crossDomainConfig {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://120.77.242.202:608/");
        corsConfiguration.addAllowedOrigin("http://houxiongxiong.icu:608/");
        corsConfiguration.addAllowedOrigin("http://127.0.0.1:608/");
        corsConfiguration.addAllowedOrigin("http://localhost:608/");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
