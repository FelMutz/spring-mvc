package application.comandrunner;

import application.domain.Account;
import application.domain.Person;
import application.domain.enums.AccountType;
import application.domain.enums.PersonType;
import application.services.AccountService;
import application.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Init implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        dbSeeder();
    }

    @Autowired
    private PersonService personService;

    @Autowired
    private AccountService accountService;

    public void dbSeeder(){
        accountService.deleteAll();
        personService.deleteAll();

        List<Account> listAccount = new ArrayList<>();
        Account account = Account.builder().password("123").balance(100.00).accountType(AccountType.NORMAL).build();
        listAccount.add(accountService.insert(account));

        account = Account.builder().password("456").balance(3000.00).accountType(AccountType.SAVING).build();
        listAccount.add(accountService.insert(account));

        account = Account.builder().password("789").balance(10000.00).accountType(AccountType.PRIVATE).build();
        listAccount.add(accountService.insert(account));

        Person person = Person.builder().name("Felipe Nigeliskii").CPF("023.088.900-75").age(28).accounts(listAccount).personType(PersonType.PHYSICAL).build();
        personService.insert(person );

        listAccount.clear();
        account = Account.builder().password("123").balance(100.00).accountType(AccountType.PRIVATE).build();
        listAccount.add(accountService.insert(account));

        account = Account.builder().password("456").balance(3000.00).accountType(AccountType.NORMAL).build();
        listAccount.add(accountService.insert(account));

        account = Account.builder().password("789").balance(10000.00).accountType(AccountType.SAVING).build();
        listAccount.add(accountService.insert(account));

        person = person.builder().name("Carlos Alberto").CPF("023.088.900-76").age(28).accounts(listAccount).CNPJ("1135277000195").personType(PersonType.LEGAL).build();
        personService.insert(person);
    }
}
