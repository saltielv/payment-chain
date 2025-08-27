package com.paymentchain.transaction.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;

public class IbanValidator implements ConstraintValidator<IbanValid, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    return IBANCheckDigit.IBAN_CHECK_DIGIT.isValid(value);
  }
}
