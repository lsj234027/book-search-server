package com.example.booksearchserver.infra.config;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.valves.rewrite.RewriteValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Configuration
public class BeanConfig {
  private static final String REWRITE_STRING = "RewriteCond %{REQUEST_PATH} .*/login$ [OR]\n" +
          "RewriteCond %{REQUEST_PATH} .*/book$ [OR]\n" +
          "RewriteRule ^(.*)$ /index.html [L]";

  @Bean
  public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
    return new MappingJackson2HttpMessageConverter();
  }

  @Bean
  public ServletWebServerFactory servletContainerFactory() throws Exception {
    final RewriteValve rewrite = new RewriteValve();
    final TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
      @Override
      protected void postProcessContext(Context context) {
        super.postProcessContext(context);
        rewrite.setContainer(context);
        try {
          rewrite.setConfiguration(REWRITE_STRING);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };
    tomcat.addContextValves(rewrite);
    return tomcat;
  }
}
