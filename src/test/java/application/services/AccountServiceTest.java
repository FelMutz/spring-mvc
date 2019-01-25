package application.services;

import application.domain.Account;
import application.domain.enums.AccountType;
import application.repository.AccountRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @InjectMocks
    Account account;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        List<Account> accountFlux = new ArrayList<>(){{
            add(account);
            add(account);
            add(account);
            add(account);
            add(account);
        }};

        when(accountRepository.findAll()).thenReturn(accountFlux);

        List<Account> accounts = accountService.findAll();

        assertEquals(5, accounts.size());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void findById() {


        account.setCard("456");
        account.setAccountType(AccountType.SAVING);
        account.setBalance(1000D);
        account.setPassword("123");

        Account teste = Account.builder()
                .card("456")
                .accountType(AccountType.SAVING)
                .balance(1000D)
                .password("123").build();



        when(accountRepository.findById(account.getCard())).thenReturn(Optional.of(account));

        account = accountService.findById(account.getCard());

        assertEquals(account, teste);
    }

    @Test
    public void insert() {
        account.setCard("456");
        account.setAccountType(AccountType.SAVING);
        account.setBalance(1000D);
        account.setPassword("123");

        Account teste = Account.builder()
                .card("456")
                .accountType(AccountType.SAVING)
                .balance(1000D)
                .password("123").build();



        when(accountRepository.insert(account)).thenReturn(account);

        account = accountService.insert(account);

        assertEquals(account, teste);

    }

    @Test
    public void update() {

        account.setCard("456");
        account.setAccountType(AccountType.NORMAL);
        account.setBalance(900D);
        account.setPassword("789");


        when(accountRepository.save(account)).thenReturn(account);

        account = accountService.update(account);

        assertEquals(Double.valueOf(900), account.getBalance());
        assertEquals("789", account.getPassword());
        assertEquals(AccountType.NORMAL, account.getAccountType());
    }

    @Test
    public void findByAccountType() {

        List<Account> accounts = new ArrayList<>(){{
            add(Account.builder().accountType(AccountType.SAVING).build());
            add(Account.builder().accountType(AccountType.NORMAL).build());
            add(Account.builder().accountType(AccountType.PRIVATE).build());
            add(Account.builder().accountType(AccountType.SAVING).build());
            add(Account.builder().accountType(AccountType.NORMAL).build());
            add(Account.builder().accountType(AccountType.NORMAL).build());
        }};

        when(accountRepository.findByAccountType(AccountType.SAVING)).thenReturn(accounts.stream().filter(account1 -> account1.getAccountType()==AccountType.SAVING).collect(Collectors.toList()));
        when(accountRepository.findByAccountType(AccountType.PRIVATE)).thenReturn(accounts.stream().filter(account1 -> account1.getAccountType()==AccountType.PRIVATE).collect(Collectors.toList()));
        when(accountRepository.findByAccountType(AccountType.NORMAL)).thenReturn(accounts.stream().filter(account1 -> account1.getAccountType()==AccountType.NORMAL).collect(Collectors.toList()));
        List<Account> accountsResult = new ArrayList<>();

        accountService.findByAccountType(AccountType.SAVING).forEach(accountsResult::add);
        assertEquals(2, accountsResult.size());
        accountsResult.clear();

        accountService.findByAccountType(AccountType.PRIVATE).forEach(accountsResult::add);
        assertEquals(1, accountsResult.size());
        accountsResult.clear();

        accountService.findByAccountType(AccountType.NORMAL).forEach(accountsResult::add);
        assertEquals(3, accountsResult.size());

        verify(accountRepository, times(1)).findByAccountType(AccountType.SAVING);

    }
}