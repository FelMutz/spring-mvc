package application.controllers;

import application.domain.Account;
import application.domain.Person;
import application.dto.AccountDto;
import application.dto.BindAccountDto;
import application.dto.PersonDto;
import application.mappers.AccountMap;
import application.mappers.PersonMap;
import application.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Api("Person Controller")
@RestController
@AllArgsConstructor
@RequestMapping("persons")
public class PersonController {

    private PersonService personService;

    @ApiOperation("Get All persons from the bank")
    @GetMapping
    public List<PersonDto> findAll(){
        List<Person> persons = personService.findAll();
        ArrayList<PersonDto> personsLink = new ArrayList<>();
        persons.forEach(x->{
            PersonDto personDto = PersonMap.mapToDto(x);
            personDto.add(linkTo(methodOn(PersonController.class).findById(x.getIdPerson())).withSelfRel());
            personsLink.add(personDto);
        });

        return personsLink;
    }

    @ApiOperation("Get a specific person from the bank")
    @GetMapping("{id}")
    public PersonDto findById(@PathVariable String id){
        Person person = personService.findById(id);

        PersonDto personDto = PersonMap.mapToDto(person);

        personDto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("Buscar Todas as Pessoas"));

        return personDto;
    }

    @ApiOperation("Add a new person in the bank")
    @PostMapping
    public Person insert(@Valid @RequestBody PersonDto personDto){
        return personService.insert(PersonMap.dtoToMap(personDto));
    }

    @ApiOperation("Associates a list of Accounts with a specific person")
    @PostMapping("bindsAccount")
    public Person bindsAccount(@RequestBody BindAccountDto bindAccountDto){
      return personService.bindsAccount(bindAccountDto);
    }

    @ApiOperation("Update a specific person")
    @PutMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public Person update(@Valid @RequestBody PersonDto personDto){
        return personService.update(PersonMap.dtoToMap(personDto));
    }

    @ApiOperation("Delete a specific person")
    @DeleteMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id){
        personService.delete(id);
    }

}
