package application.controllers;


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
    public List<AccountDto> findAll(){
        return accountService.findAll()
                .stream().map(account->AccountMap.mapToDto(account)).collect(Collectors.toList());
    }

    @ApiOperation("Get a specific Accounts from the bank by id")
    @GetMapping("{card}")
    public AccountDto findById(@PathVariable String card){
       return AccountMap.mapToDto(accountService.findById(card));
    }

    @ApiOperation("Get All Accounts from the bank with paging")
    @GetMapping("paging")
    public Page<AccountDto> findByPage(@RequestParam int page, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return AccountMap.mapPageToDtoPage(accountService.findAllBy(pageRequest));
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
