package application.controllers;

import application.domain.Account;
import application.domain.enums.AccountType;
import application.dto.AccountDto;
import application.mappers.AccountMap;
import application.repository.AccountRepository;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AccountControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AccountRepository accountRepository;

    @InjectMocks
    Account account;


    @LocalServerPort
    private int port;

    @TestConfiguration
    static class Config{
        @Bean
        public RestTemplateBuilder restTemplateBuilder(){
            return new RestTemplateBuilder();
        }
    }


    @Test
    public void findAll() {

        List<Account> accounts = new ArrayList<>(){{
            add(account);
            add(account);
            add(account);
            add(account);
            add(account);
        }};

        when(accountRepository.findAll()).thenReturn(accounts);


        ResponseEntity<List> responseEntity =  restTemplate.getForEntity("/accounts/", List.class, AccountDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(responseEntity.getBody().size()).isEqualTo(5);

    }

    @Test
    public void findById() {

        account.setCard("123");
        account.setPassword("123");
        account.setAccountType(AccountType.NORMAL);
        account.setBalance(1000D);

        when(accountRepository.findById(account.getCard())).thenReturn(Optional.of(account));

        ResponseEntity<AccountDto> response =  restTemplate.getForEntity("/accounts/123", AccountDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCard()).isEqualTo("123");
    }

    @Test
    public void insert() {
        account.setCard("456");
        account.setAccountType(AccountType.SAVING);
        account.setBalance(1000D);
        account.setPassword("456789");


        AccountDto accountDto = AccountMap.mapToDto(account);


        when(accountRepository.insert(account)).thenReturn(account);

        ResponseEntity<AccountDto> response =  restTemplate.postForEntity("/accounts",accountDto, AccountDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCard()).isEqualTo("456");

    }

    @Test
    public void update() {

       account.setCard("456");
        account.setAccountType(AccountType.NORMAL);
        account.setBalance(900D);
        account.setPassword("789");

        AccountDto accountDto = AccountMap.mapToDto(account);

        when(accountRepository.save(account)).thenReturn(account);

 //      restTemplate.put("/accounts",accountDto,AccountDto.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody().getCard()).isEqualTo("456");

        Gson gson = new Gson();

        String jsonAccount = gson.toJson(accountDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jsonAccount, headers);
        ResponseEntity<AccountDto> response = restTemplate.exchange("/accounts", HttpMethod.PUT, entity, AccountDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCard()).isEqualTo("456");
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

        ResponseEntity<List> responseEntity =  restTemplate
                .getForEntity("/accounts/filter?type=SAVING", List.class, AccountDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);


        responseEntity =  restTemplate
                .getForEntity("/accounts/filter?type=PRIVATE", List.class, AccountDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(1);


        responseEntity =  restTemplate
                .getForEntity("/accounts/filter?type=NORMAL", List.class, AccountDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(3);

    }
}