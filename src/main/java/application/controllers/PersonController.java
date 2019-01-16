package application.controllers;

import application.dto.BindAccountDto;
import application.dto.PersonDto;
import application.mappers.PersonMap;
import application.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api("Person Controller")
@RestController
@AllArgsConstructor
@RequestMapping("persons")
public class PersonController {

    private PersonService personService;

    @ApiOperation("Get All persons from the bank")
    @GetMapping
    public List<PersonDto> findAll(){
        return personService.findAll()
                .stream().map(person->PersonMap.mapToDto(person)).collect(Collectors.toList());
    }

    @ApiOperation("Get a specific person from the bank")
    @GetMapping("{id}")
    public PersonDto findById(@PathVariable String id){
       return PersonMap.mapToDto(personService.findById(id));
    }

    @ApiOperation("Get All persons from the bank with paging")
    @GetMapping("paging")
    public Page<PersonDto> findByPage(@RequestParam int page, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return PersonMap.mapPageToDtoPage(personService.findAllBy(pageRequest));
    }

    @ApiOperation("Add a new person in the bank")
    @PostMapping
    public PersonDto insert(@Valid @RequestBody PersonDto personDto){
        return PersonMap.mapToDto(personService.insert(PersonMap.dtoToMap(personDto)));
    }

    @ApiOperation("Associates a list of Accounts with a specific person")
    @PostMapping("bindsAccount")
    public PersonDto bindsAccount(@RequestBody BindAccountDto bindAccountDto){
      return PersonMap.mapToDto(personService.bindsAccount(bindAccountDto));
    }

    @ApiOperation("Update a specific person")
    @PutMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public PersonDto update(@Valid @RequestBody PersonDto personDto){
        return PersonMap.mapToDto(personService.update(PersonMap.dtoToMap(personDto)));
    }

    @ApiOperation("Delete a specific person")
    @DeleteMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id){
        personService.delete(id);
    }

}
