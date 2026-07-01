package ru.otus.accountservice.aggregate.mapper;

import org.mapstruct.*;
import ru.otus.accountservice.aggregate.DTO.AccountDto;
import ru.otus.accountservice.aggregate.entity.Account;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: account-service
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDto(Account entity);

    Account toEntity(AccountDto dto);

    List<AccountDto> toDto(List<Account> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(AccountDto dto, @MappingTarget Account entity);
}
