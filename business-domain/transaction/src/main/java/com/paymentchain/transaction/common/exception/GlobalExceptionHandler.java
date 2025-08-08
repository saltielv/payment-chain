package com.paymentchain.transaction.common.exception;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatusCode status,
      final WebRequest request) {
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

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      final ServletRequestBindingException ex,
      final HttpHeaders headers,
      final HttpStatusCode status,
      final WebRequest request) {

    final String missingParameter;
    final String missingParameterType;

    switch (ex) {
      case MissingRequestHeaderException headerException -> {
        missingParameter = headerException.getHeaderName();
        missingParameterType = "header";
      }
      case MissingServletRequestParameterException parameterException -> {
        missingParameter = parameterException.getParameterName();
        missingParameterType = "query";
      }
      case MissingPathVariableException pathVariableException -> {
        missingParameter = pathVariableException.getVariableName();
        missingParameterType = "path";
      }
      default -> {
        missingParameter = "unknown";
        missingParameterType = "unknown";
      }
    }

    String value =
        String.format(
            "Missing %s parameter with name '%s'", missingParameterType, missingParameter);

    final ProblemDetail problemDetail =
        new ProblemDetailBuilder(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())
            .errors(Collections.singletonMap(missingParameter, value))
            .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      final MissingServletRequestParameterException ex,
      final HttpHeaders headers,
      final HttpStatusCode status,
      final WebRequest request) {

    return handleServletRequestBindingException(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      final MissingPathVariableException ex,
      final HttpHeaders headers,
      final HttpStatusCode status,
      final WebRequest request) {
    return handleServletRequestBindingException(ex, headers, status, request);
  }
}
