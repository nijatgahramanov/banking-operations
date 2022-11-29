package com.compay.msbanking.mapper;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account requestAccountToAccount(AccountRequest requestAccount);
}
