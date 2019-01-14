package application.controllers;

import application.domain.Account;
import application.dto.BankSharesDto;
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

    BankSharesServices bankSharesServices;

    @ApiOperation("withdrawal")
    @PostMapping("withdrawal")
    @Transactional
    public Account withdrawal(@Valid @RequestBody BankSharesDto bankSharesDto){
        return bankSharesServices.withdrawal(bankSharesDto);
    }

    @ApiOperation("deposit")
    @PostMapping("deposit")
    public Account deposit(@Valid @RequestBody BankSharesDto bankSharesDto){
        return bankSharesServices.deposit(bankSharesDto);
    }

    @ApiOperation("transfer")
    @PostMapping("transfer")
    @Transactional
    public Account transfer(@Valid @RequestBody BankSharesDto bankSharesDto){
        return bankSharesServices.transfer(bankSharesDto);
    }


}
