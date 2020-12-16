package com.felipe.app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class Webconfig {
	
	@Bean
	public FilterRegistrationBean<CorsFilter> filterRegistrationBean(){
		List<String> all = Arrays.asList("*");
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(all);
		configuration.setAllowedMethods(all);
		configuration.setAllowedOrigins(all);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		CorsFilter corsFilter = new CorsFilter(source);
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filter;
		
	}

}
