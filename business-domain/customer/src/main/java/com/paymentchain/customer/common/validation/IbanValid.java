package com.paymentchain.customer.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IbanValidator.class)
public @interface IbanValid {
  String message() default "{validation.exception.iban}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
