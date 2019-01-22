package application.services;

import application.domain.Account;
import application.dto.BankSharesDto;
import application.exceptions.ExceptionCustom;
import application.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BankSharesServices {

    private AccountRepository accountRepository;

    private AccountService accountService;

    @Transactional
    public Account withdrawal(BankSharesDto bankSharesDto){
        Account account = accountService.findById(bankSharesDto.getCard());

        ValidPassword.validPassword(account.getPassword(), bankSharesDto.getPassword());

        Double newBalance = Roud.roudBalance( account.getAccountType().withdrawal(account.getBalance(), bankSharesDto.getAmount()));

        account.getAccountType().validBalanceLimit(newBalance);

        account.setBalance(newBalance);

        return accountService.update(account);
    }

    public Account deposit(BankSharesDto bankSharesDto){
        Account account = accountService.findById(bankSharesDto.getCard());

        ValidPassword.validPassword(account.getPassword(), bankSharesDto.getPassword());

        account.setBalance(Roud.roudBalance( account.getAccountType().deposit( account.getBalance(), bankSharesDto.getAmount() ) ));
        return accountService.update(account);
    }


    @Transactional
    public Account transfer(BankSharesDto bankSharesDto){

        if(bankSharesDto.getAccountTransfer() == null){
            throw ExceptionCustom.builder().code(20).httpStatus(HttpStatus.BAD_REQUEST).message("Erro na solicitação").detail("AccountTransfer não pode ser Null").build();
        }


        transferAccountReceive(bankSharesDto.getAccountTransfer(),bankSharesDto.getAmount());

        return transferAccountSubmit(bankSharesDto.getCard(),bankSharesDto.getAmount(), bankSharesDto.getPassword());
    }

    public Account transferAccountSubmit(String accountId, Double amount, String password){
        Account account = accountService.findById(accountId);
        ValidPassword.validPassword(account.getPassword(), password);

        Double newBalance = Roud.roudBalance(account.getAccountType().transfer(account.getBalance(), amount ));

        account.getAccountType().validBalanceLimit(newBalance);

        account.setBalance(newBalance);

       return accountService.update(account);
    }

    public Account transferAccountReceive(String accountId, Double amount){

        Account account = accountService.findById(accountId);

        account.setBalance(Roud.roudBalance( account.getBalance() + amount ));

        return accountService.update(account);
    }
}
