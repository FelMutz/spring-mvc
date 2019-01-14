package application.services;

import application.domain.Account;
import application.dto.BankSharesDto;
import application.exceptions.ExceptionCustom;
import application.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankSharesServices {

    private AccountRepository accountRepository;

    private AccountService accountService;

    public Account withdrawal(BankSharesDto bankSharesDto){
        Account account = accountService.findById(bankSharesDto.getCard());

        ValidPassword.validPassword(account.getPassword(), bankSharesDto.getPassword());

        Double newBalance = Roud.roudBalance( account.getAccountType().withdrawal(account.getBalance(), bankSharesDto.getAmount()));

        account.getAccountType().validBalanceLimit(newBalance);

        account.setBalance(newBalance);

        return accountService.updateAccount(account);
    }

    public Account deposit(BankSharesDto bankSharesDto){
        Account account = accountService.findById(bankSharesDto.getCard());

        ValidPassword.validPassword(account.getPassword(), bankSharesDto.getPassword());

        account.setBalance(Roud.roudBalance( account.getAccountType().deposit( account.getBalance(), bankSharesDto.getAmount() ) ));
        return accountService.updateAccount(account);
    }

    public Account transfer(BankSharesDto bankSharesDto){
        Account account = accountService.findById(bankSharesDto.getCard());
        Account accountTransfer;

        try {
            accountTransfer = accountService.findById(bankSharesDto.getAccountTransfer());
        }
        catch(IllegalArgumentException e){
            throw ExceptionCustom.builder().code(20).httpStatus(HttpStatus.BAD_REQUEST).message("Erro na solicitação").detail("AccountTransfer não pode ser Null").build();
        }

        ValidPassword.validPassword(account.getPassword(), bankSharesDto.getPassword());

        Double newBalance = Roud.roudBalance(account.getAccountType().transfer(account.getBalance(), bankSharesDto.getAmount() ));

        account.getAccountType().validBalanceLimit(newBalance);

        account.setBalance(newBalance);

        accountTransfer.setBalance(Roud.roudBalance( accountTransfer.getBalance() + bankSharesDto.getAmount() ));

        accountService.updateAccount(accountTransfer);

        return accountService.updateAccount(account);
    }
}
