package com.paymentchain.transaction.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String title;
  private final String detail;
  private final HttpStatus status;

  public BusinessException(final BusinessExceptionReason reason) {
    this.title = reason.getTitle();
    this.detail = reason.getDetail();
    this.status = reason.getStatus();
  }
}
