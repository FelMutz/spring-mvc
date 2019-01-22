package application.facade;

import application.domain.Account;
import application.dto.AccountDto;
import application.dto.BankSharesDto;
import application.exceptions.ExceptionCustom;
import application.services.BankSharesServices;
import application.services.Roud;
import application.services.ValidPassword;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static application.mappers.AccountMap.*;
@AllArgsConstructor
@Component
public class BankSharesServicesFacade {

    BankSharesServices bankSharesServices;

    public AccountDto withdrawal(BankSharesDto bankSharesDto){

        return mapToDto(bankSharesServices.withdrawal(bankSharesDto));
    }

    public AccountDto deposit(BankSharesDto bankSharesDto){
        return mapToDto(bankSharesServices.deposit(bankSharesDto));
    }

    public AccountDto transfer(BankSharesDto bankSharesDto){
        return mapToDto(bankSharesServices.transfer(bankSharesDto));
    }
}
