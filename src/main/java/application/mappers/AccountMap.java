package application.mappers;

import application.domain.Account;
import application.domain.enums.AccountType;
import application.dto.AccountDto;

import java.util.Optional;

public class AccountMap {

    public static Account dtoToMap(AccountDto accountDto){
        return Optional.ofNullable(accountDto).map(account -> Account.builder()
            .accountType(accountDto.getAccountType() != null ? accountDto.getAccountType() : AccountType.NORMAL)
            .balance(accountDto.getBalance())
            .password(accountDto.getPassword())
            .card(accountDto.getCard())
            .build()).orElse(null);
    }

    public static AccountDto mapToDto(Account account){
        return Optional.ofNullable(account).map(accountDto -> AccountDto.builder()
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .password(account.getPassword())
                .card(account.getCard())
                .build()).orElse(null);
    }
}
