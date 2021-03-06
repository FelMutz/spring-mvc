package application.controllers;


import application.dto.AccountDto;
import application.facade.AccountServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("Account Controller")
@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountController {

    AccountServiceFacade accountServiceFacade;

    @ApiOperation("Get All Accounts from the bank")
    @GetMapping(produces="application/json")
    public List<AccountDto> findAll(){
        return accountServiceFacade.findAll();
    }

    @ApiOperation("Get a specific Accounts from the bank by id")
    @GetMapping("{card}")
    public AccountDto findById(@PathVariable String card){
       return accountServiceFacade.findById(card);
    }

    @ApiOperation("Get All Accounts from the bank with paging")
    @GetMapping("paging")
    public Page<AccountDto> findByPage(@RequestParam int page, @RequestParam int size){
        return accountServiceFacade.findAllBy(page,size);
    }

    @ApiOperation("Add a new Account")
    @PostMapping
    public AccountDto insert(@Valid @RequestBody AccountDto accountDto){
        return accountServiceFacade.insert(accountDto);
    }

    @ApiOperation("Update a specific Account")
    @PutMapping
    public AccountDto update( @RequestBody AccountDto accountDto){
        return accountServiceFacade.update(accountDto);
    }

    @ApiOperation("Delete a specific Account")
    @DeleteMapping("{card}")
    public void delete(@PathVariable String card){
        accountServiceFacade.delete(card);
    }

    @ApiOperation("Return Account List by account type")
    @GetMapping("filter")
    public List<AccountDto> findByType(@RequestParam String type){
        return accountServiceFacade.findByAccountType(type);
    }


}
