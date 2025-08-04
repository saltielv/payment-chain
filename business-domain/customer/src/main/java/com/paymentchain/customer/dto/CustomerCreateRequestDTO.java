package com.paymentchain.customer.dto;

import com.paymentchain.customer.common.validation.IbanValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
public class CustomerCreateRequestDTO {

  @NotBlank(message = "{validation.exception.not_blank}")
  private String name;

  @NotBlank(message = "{validation.exception.not_blank}")
  private String surname;

  @NotBlank(message = "{validation.exception.not_blank}")
  @Size(min = 5, message = "{validation.exception.field_length}")
  private String code;

  @NotBlank(message = "{validation.exception.not_blank}")
  @IbanValid
  private String iban;

  @NotNull(message = "{validation.exception.not_null}")
  @Pattern(regexp = "\\+?\\d{10,15}", message = "{validation.exception.phone_number}")
  private String phone;

  @NotNull(message = "{validation.exception.not_null}")
  @Size(min = 5, message = "{validation.exception.field_length}")
  private String address;

  @NotNull(message = "{validation.exception.not_null}")
  @NotEmpty(message = "{validation.exception.not_empty}")
  private List<Long> productIds;
}
