package com.paymentchain.customer.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

@Component
@RequiredArgsConstructor
public class CustomLocaleResolver implements LocaleResolver {

  @Value("${supported-locales}")
  private final List<String> supportedLocales;

  private List<Locale> getSupportedLocales() {
    return supportedLocales.stream().map(Locale::forLanguageTag).toList();
  }

  @Override
  public Locale resolveLocale(HttpServletRequest request) {

    // 'lang' Query param
    String langParam = request.getParameter("lang");
    if (StringUtils.hasText(langParam)) {
      Locale paramLangLocale = Locale.forLanguageTag(langParam);
      if (getSupportedLocales().contains(paramLangLocale)) return paramLangLocale;
    }

    // 'X-Language' Custom Header
    String customLangHeader = request.getHeader("X-Language");
    if (StringUtils.hasText(customLangHeader)) {
      Locale customHeaderLocale = Locale.forLanguageTag(customLangHeader);
      if (getSupportedLocales().contains(customHeaderLocale)) return customHeaderLocale;
    }

    // 'Accept-Language' Header
    String acceptLangHeader = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
    if (StringUtils.hasText(acceptLangHeader)) {
      List<Locale.LanguageRange> languageRanges = Locale.LanguageRange.parse(acceptLangHeader);
      Locale acceptLangLocale = Locale.lookup(languageRanges, getSupportedLocales());
      if (acceptLangLocale != null) return acceptLangLocale;
    }

    return Locale.ENGLISH;
  }

  @Override
  public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    throw new UnsupportedOperationException("Not supported");
  }
}
