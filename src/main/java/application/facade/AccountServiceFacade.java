package application.facade;

import application.domain.enums.AccountType;
import application.dto.AccountDto;
import application.mappers.AccountMap;
import application.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static application.mappers.AccountMap.*;

@Component
@AllArgsConstructor
public class AccountServiceFacade {

    AccountService accountService;

    public List<AccountDto> findAll(){
        return accountService.findAll().stream().map(AccountMap::mapToDto).collect(Collectors.toList());
    }

    public Page<AccountDto> findAllBy(Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return mapPageToDtoPage(accountService.findAllBy(pageRequest));
    }

    public AccountDto findById(String card){
        return mapToDto(accountService.findById(card));
    }

    public AccountDto insert(AccountDto accountDto) {
        return mapToDto(accountService.insert(dtoToMap(accountDto)));
    }

    public AccountDto update(AccountDto accountDto){
        return mapToDto(accountService.update(dtoToMap(accountDto)));
    }

    public void deleteAll(){
        accountService.deleteAll();
    }

    public void delete(String card){
        accountService.delete(card);
    }

    public List<AccountDto> findByAccountType(String accountType){
        return accountService.findByAccountType( AccountType.valueOf(accountType))
                .stream().map(AccountMap::mapToDto).collect(Collectors.toList());
    }


}
