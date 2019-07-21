package com.example.booksearchserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class BeanRegister {

  @Bean
  public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
    return new MappingJackson2HttpMessageConverter();
  }
}
