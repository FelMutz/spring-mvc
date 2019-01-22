package application.services;

import application.domain.Account;
import application.domain.enums.AccountType;
import application.dto.BankSharesDto;
import application.repository.AccountRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BankSharesServicesTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountService accountService;

    @InjectMocks
    BankSharesServices bankSharesServices;

    @InjectMocks
    BankSharesDto bankSharesDto;

    @InjectMocks
    Account account;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    public ErrorCollector errorCollector = new ErrorCollector();

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void withdrawalNormalAccountt() {
        bankSharesDto.setCard("123");
        bankSharesDto.setAmount(100D);
        bankSharesDto.setPassword("123");

        Account accountBase = Account.builder()
                .accountType(AccountType.NORMAL)
                .balance(1000D)
                .password("123")
                .card("123")
                .build();


        when(accountRepository.findById(bankSharesDto.getCard())).thenReturn(Optional.of(accountBase));

        when(accountService.findById(bankSharesDto.getCard())).thenReturn(accountBase);

        when(accountRepository.save(any())).thenReturn(accountBase);

        when(accountService.update(any())).thenReturn(accountBase);

        account = bankSharesServices.withdrawal(bankSharesDto);

        assertEquals(account.getBalance(), Double.valueOf(900));

    }

    @Test
    public void withdrawalPrivateAccountt() {
        bankSharesDto.setCard("123");
        bankSharesDto.setAmount(100D);
        bankSharesDto.setPassword("123");

        Account accountBase = Account.builder()
                .accountType(AccountType.PRIVATE)
                .balance(1000D)
                .password("123")
                .card("123")
                .build();

        when(accountRepository.findById(bankSharesDto.getCard())).thenReturn(Optional.of(accountBase));

        when(accountService.findById(bankSharesDto.getCard())).thenReturn(accountBase);

        when(accountRepository.save(any())).thenReturn(accountBase);

        when(accountService.update(any())).thenReturn(accountBase);

        account = bankSharesServices.withdrawal(bankSharesDto);

        assertEquals(account.getBalance(), Double.valueOf(895));

    }

    @Test
    public void deposit() {
        bankSharesDto.setCard("123");
        bankSharesDto.setAmount(100D);
        bankSharesDto.setPassword("123");

        Account accountBase = Account.builder()
                .accountType(AccountType.NORMAL)
                .balance(1000D)
                .password("123")
                .card("123")
                .build();

        when(accountRepository.findById(bankSharesDto.getCard())).thenReturn(Optional.of(accountBase));

        when(accountService.findById(bankSharesDto.getCard())).thenReturn(accountBase);

        when(accountRepository.save(any())).thenReturn(accountBase);

        when(accountService.update(any())).thenReturn(accountBase);

        account = bankSharesServices.deposit(bankSharesDto);

        assertEquals(account.getBalance(), Double.valueOf(1100));

    }

    @Test
    public void transferAccountSubmit() {
        bankSharesDto.setCard("123");
        bankSharesDto.setAmount(100D);
        bankSharesDto.setPassword("123");
        bankSharesDto.setAccountTransfer("456");

        Account accountBaseSubmit = Account.builder()
                .accountType(AccountType.SAVING)
                .balance(1000D)
                .password("123")
                .card("123")
                .build();

        when(accountRepository.findById(bankSharesDto.getCard())).thenReturn(Optional.of(accountBaseSubmit));

        when(accountService.findById(bankSharesDto.getCard())).thenReturn(accountBaseSubmit);

        when(accountRepository.save(any())).thenReturn(accountBaseSubmit);

        when(accountService.update(any())).thenReturn(accountBaseSubmit);

        account = bankSharesServices.transferAccountSubmit(bankSharesDto.getCard(), bankSharesDto.getAmount(), bankSharesDto.getPassword());

        assertEquals(Double.valueOf(894), account.getBalance());

    }

    @Test
    public void transferAccountReceive() {
        bankSharesDto.setCard("123");
        bankSharesDto.setAmount(100D);
        bankSharesDto.setPassword("123");
        bankSharesDto.setAccountTransfer("456");

        Account accountBaseReceive = Account.builder()
                .accountType(AccountType.NORMAL)
                .balance(1000D)
                .password("123")
                .card("456")
                .build();


        when(accountRepository.findById(bankSharesDto.getAccountTransfer())).thenReturn(Optional.of(accountBaseReceive));

        when(accountService.findById(bankSharesDto.getAccountTransfer())).thenReturn(accountBaseReceive);

        when(accountRepository.save(accountBaseReceive)).thenReturn(accountBaseReceive);

        when(accountService.update(accountBaseReceive)).thenReturn(accountBaseReceive);


        account = bankSharesServices.transferAccountReceive(bankSharesDto.getAccountTransfer(), bankSharesDto.getAmount());

        assertEquals(Double.valueOf(1100), account.getBalance());
    }

}