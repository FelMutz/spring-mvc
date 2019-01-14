package application.services;

import application.domain.Account;
import application.domain.enums.AccountType;
import application.exceptions.ExceptionCustom;
import application.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    public void calcInterestMonthlyOfSavingsAccounts(){
        List<Account> accountsSavings = findByAccountType(AccountType.SAVING);
        accountsSavings.forEach(x->{
            Double balance = x.getBalance();
            balance += balance * 6 / 100;
            x.setBalance(Roud.roudBalance(balance));
            updateAccount(x);
        });
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Page<Account> findAllBy(PageRequest pageRequest){
        return accountRepository.findAllBy(pageRequest);
    }

    public Account findById(String card){
        return  accountRepository.findById(card)
            .orElseThrow(()->
                    ExceptionCustom.builder().code(10).httpStatus(HttpStatus.NOT_FOUND).message("Dado não existe no banco!").detail("Conta não encontrada com o cartão: "+card).build());
    }

    public Account insert(Account account) {
        return  accountRepository.insert(account);
    }

    public Account updateAccount(Account account){
        return accountRepository.save(account);
    }

    public void deleteAll(){
        accountRepository.deleteAll();
    }

    public void delete(String card){
        accountRepository.delete(findById(card));
    }

    public List<Account> findByAccountType(AccountType accountType){
        return accountRepository.findByAccountType(accountType);
    }
}
