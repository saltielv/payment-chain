package com.paymentchain.transaction.common.exception.util;

import com.paymentchain.transaction.common.exception.dto.ErrorDetails;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;

@UtilityClass
public class ErrorsUtils {

  public Map.Entry<String, String> extractParameterInfo(ServletRequestBindingException ex) {
    return switch (ex) {
      case MissingRequestHeaderException headerEx -> Map.entry(headerEx.getHeaderName(), "header");
      case MissingServletRequestParameterException paramEx ->
          Map.entry(paramEx.getParameterName(), "query");
      case MissingPathVariableException pathEx -> Map.entry(pathEx.getVariableName(), "path");
      default -> Map.entry("unknown", "unknown");
    };
  }

  public List<ErrorDetails> compositeValiditionError(Errors err) {
    if (!err.hasErrors()) {
      return Collections.emptyList();
    } else {
      return err.getFieldErrors().stream()
          .distinct()
          .map(
              fieldError -> new ErrorDetails(fieldError.getField(), fieldError.getDefaultMessage()))
          .collect(Collectors.toList());
    }
  }

  public List<ErrorDetails> compositeValiditionError(Set<ConstraintViolation<?>> err) {
    if (err.isEmpty()) {
      return Collections.emptyList();
    } else {
      return err.stream()
          .map(
              cv -> new ErrorDetails(extractConstraintPropertyInfo(cv).toString(), cv.getMessage()))
          .collect(Collectors.toList());
    }
  }

  public Path.Node extractConstraintPropertyInfo(ConstraintViolation<?> constraintViolation) {
    return ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode();
  }
}
