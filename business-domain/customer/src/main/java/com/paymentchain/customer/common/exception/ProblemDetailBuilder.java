package com.paymentchain.customer.common.exception;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProblemDetailBuilder {

  private final HttpStatus status;
  private final String detail;
  private String title;
  private Map<String, String> errors;

  private static final String MOZILLA_DEVELOPER_BASE_URL =
      "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/";

  public ProblemDetailBuilder(HttpStatus status, String detail) {
    this.status = status;
    this.detail = detail;
  }

  public ProblemDetailBuilder title(String title) {
    this.title = title;
    return this;
  }

  public ProblemDetail build() {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);

    if (title != null) {
      problemDetail.setTitle(title);
    } else {
      problemDetail.setTitle(status.getReasonPhrase());
    }

    problemDetail.setType(URI.create(MOZILLA_DEVELOPER_BASE_URL + status.value()));

    String timestamp =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now());
    problemDetail.setProperty("timestamp", timestamp);

    if (errors != null && !errors.isEmpty()) {
      problemDetail.setProperty("errors", errors);
    }

    return problemDetail;
  }
}
