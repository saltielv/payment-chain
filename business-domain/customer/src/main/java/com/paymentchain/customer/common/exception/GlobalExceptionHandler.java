package com.paymentchain.customer.common.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  @ExceptionHandler({BusinessException.class})
  public ResponseEntity<ProblemDetail> handleBusinessException(
      final BusinessException ex, final ServletWebRequest request) {

    ProblemDetail problemDetail =
        new ProblemDetailBuilder(ex.getStatus(), ex.getDetail()).title(ex.getTitle()).build();

    return ResponseEntity.status(ex.getStatus()).body(problemDetail);
  }
}
