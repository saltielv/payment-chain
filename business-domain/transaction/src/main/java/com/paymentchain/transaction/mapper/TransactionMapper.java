package com.paymentchain.transaction.mapper;

import com.paymentchain.transaction.dto.TransactionDTO;
import com.paymentchain.transaction.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public TransactionDTO mapToDTO(final Transaction entity, final TransactionDTO dto) {
    dto.setId(entity.getId());
    dto.setReference(entity.getReference());
    dto.setIbanAccount(entity.getIbanAccount());
    dto.setAmount(entity.getAmount());
    dto.setFee(entity.getFee());
    dto.setDescription(entity.getDescription());
    dto.setStatus(entity.getStatus());
    dto.setChannel(entity.getChannel());
    return dto;
  }

  public Transaction mapToEntity(final TransactionDTO dto, final Transaction entity) {
    entity.setReference(dto.getReference());
    entity.setIbanAccount(dto.getIbanAccount());
    entity.setAmount(dto.getAmount());
    entity.setFee(dto.getFee());
    entity.setDescription(dto.getDescription());
    entity.setStatus(dto.getStatus());
    entity.setChannel(dto.getChannel());
    return entity;
  }
}
