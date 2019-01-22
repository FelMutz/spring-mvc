package application.facade;

import application.dto.AccountDto;
import application.dto.BankSharesDto;
import application.services.BankSharesServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static application.mappers.AccountMap.mapToDto;
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
