package application.controllers;

import application.domain.Account;
import application.dto.AccountDto;
import application.dto.BankSharesDto;
import application.facade.BankSharesServicesFacade;
import application.services.BankSharesServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("Bank Shares Controller")
@RestController
@RequestMapping("shares")
@AllArgsConstructor
public class BankSharesControlle {

    BankSharesServicesFacade bankSharesServicesFacade;

    @ApiOperation("withdrawal")
    @PostMapping("withdrawal")
    public AccountDto withdrawal(@Valid @RequestBody BankSharesDto bankSharesDto){
        return bankSharesServicesFacade.withdrawal(bankSharesDto);
    }

    @ApiOperation("deposit")
    @PostMapping("deposit")
    public AccountDto deposit(@Valid @RequestBody BankSharesDto bankSharesDto){
        return bankSharesServicesFacade.deposit(bankSharesDto);
    }

    @ApiOperation("transfer")
    @PostMapping("transfer")
    public AccountDto transfer(@Valid @RequestBody BankSharesDto bankSharesDto){
        return bankSharesServicesFacade.transfer(bankSharesDto);
    }


}
