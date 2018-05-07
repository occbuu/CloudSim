package com.hieu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class CorsFilter extends WebMvcConfigurerAdapter {
	@Override
	   public void addCorsMappings(CorsRegistry registry) {
	       registry.addMapping("/**")
	               .allowedOrigins("*")
	               .allowedMethods("*")
	               .allowedHeaders("*");
	   }
}
