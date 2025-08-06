package com.paymentchain.transaction.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessExceptionReason {
  DEFAULT_BUSINESS_ERROR(
      "Business Error", "An unexpected business error occurred", HttpStatus.PRECONDITION_REQUIRED),
  ARGUMENT_NOT_VALID_ERROR("Validation Error", "Validation failed", HttpStatus.BAD_REQUEST),
  TRANSACTION_NOT_FOUND(
      "Transaction Not Found",
      "Transaction not found based on the given external reference",
      HttpStatus.NOT_FOUND);

  private final String title;
  private final String detail;
  private final HttpStatus status;
}
