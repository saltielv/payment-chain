package com.paymentchain.customer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
@RequiredArgsConstructor
public class LocaleConfig {

  private final CustomLocaleResolver customLocaleResolver;

  @Bean
  LocaleResolver localeResolver() {
    return customLocaleResolver;
  }
}
