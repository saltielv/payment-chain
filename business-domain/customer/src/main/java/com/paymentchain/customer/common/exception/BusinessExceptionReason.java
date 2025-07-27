package com.paymentchain.customer.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessExceptionReason {
  DEFAULT_BUSINESS_ERROR(
      "Business Error", "An unexpected business error occurred", HttpStatus.PRECONDITION_REQUIRED),
  CUSTOMER_NOT_FOUND(
      "Customer Not Found",
      "Customer not found based on the given external reference",
      HttpStatus.NOT_FOUND);

  private final String title;
  private final String detail;
  private final HttpStatus status;
}
