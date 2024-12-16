package com.spring.implementation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Match your API paths
            .allowedOrigins("https://frontend-dot-your-project-id.ue.r.appspot.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*") // Allow all headers
            .allowCredentials(true); // Allow cookies if needed
      }
    };
  }
}
