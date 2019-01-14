package application.controllers;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import application.domain.Account;
import application.dto.AccountDto;
import application.mappers.AccountMap;
import application.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api("Account Controller")
@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountController {

    AccountService accountService;

    @ApiOperation("Get All Accounts from the bank")
    @GetMapping(produces="application/json")
    public ArrayList<AccountDto> findAll(){

        ArrayList<AccountDto> accountsLink = new ArrayList<>();

        accountService.findAll().stream().map(x->accountsLink.add(AccountMap.mapToDto(x)));

        accountsLink.forEach(accountDto->{
            accountDto.add(linkTo(methodOn(AccountController.class).findById(accountDto.getCard())).withSelfRel());
        });

        return accountsLink;
    }

    @ApiOperation("Get a specific Accounts from the bank by id")
    @GetMapping("{card}")
    public AccountDto findById(@PathVariable String card){
        Account account = accountService.findById(card);

        AccountDto accountDto = AccountMap.mapToDto(account);

        accountDto.add(linkTo(methodOn(AccountController.class).findAll()).withRel("Buscar Todas as Contas"));

        return accountDto;
    }

    @ApiOperation("Get All Accounts from the bank with paging")
    @GetMapping("paging")
    public Page<Account> findByPage(@RequestParam int page, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return  accountService.findAllBy(pageRequest);
    }

    @ApiOperation("Add a new Account")
    @PostMapping
    public Account insert(@Valid @RequestBody AccountDto accountDto){
        return accountService.insert(AccountMap.dtoToMap(accountDto));
    }

    @ApiOperation("Update a specific Account")
    @PutMapping
   // @PreAuthorize("hasRole('ADMIN')")
    public Account update(@Valid @RequestBody AccountDto accountDto){
        return accountService.updateAccount(AccountMap.dtoToMap(accountDto));
    }

    @ApiOperation("Delete a specific Account")
    @DeleteMapping("{card}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String card){
         accountService.delete(card);
    }


}
