package com.paymentchain.transaction.common.exception;

import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  /**
   * Handles custom business exceptions of type {@link BusinessException}.
   *
   * @param ex the thrown {@link BusinessException}
   * @param request
   * @return {@linkResponseEntity} emitting the {@link ProblemDetail} response
   */
  @ExceptionHandler({BusinessException.class})
  public ResponseEntity<ProblemDetail> handleBusinessException(
      final BusinessException ex, final ServletWebRequest request) {

    ProblemDetail problemDetail =
        new ProblemDetailBuilder(ex.getStatus(), ex.getDetail()).title(ex.getTitle()).build();

    return ResponseEntity.status(ex.getStatus()).body(problemDetail);
  }

  /**
   * Handles exceptions of type {@link MethodArgumentNotValidException}. This exception is thrown
   * when an argument annotated with @Valid failed validation
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, final ServletWebRequest request) {
    Map<String, String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .collect(
                Collectors.toMap(
                    FieldError::getField, // Key: The field name
                    FieldError::getDefaultMessage, // Value: The default error message
                    (existing, replacement) -> existing + ", " + replacement));
    ProblemDetail problemDetail =
        new ProblemDetailBuilder(
                BusinessExceptionReason.ARGUMENT_NOT_VALID_ERROR.getStatus(),
                BusinessExceptionReason.ARGUMENT_NOT_VALID_ERROR.getDetail())
            .title(BusinessExceptionReason.ARGUMENT_NOT_VALID_ERROR.getTitle())
            .errors(errors)
            .build();

    return ResponseEntity.status(BusinessExceptionReason.ARGUMENT_NOT_VALID_ERROR.getStatus())
        .body(problemDetail);
  }
}
