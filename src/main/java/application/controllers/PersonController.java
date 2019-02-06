package application.controllers;

import application.dto.BindAccountDto;
import application.dto.PersonDto;
import application.facade.PersonServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("Person Controller")
@RestController
@AllArgsConstructor
@RequestMapping("persons")
public class PersonController {

    private PersonServiceFacade personServiceFacade;

    @ApiOperation("Get All persons from the bank")
    @GetMapping
    public List<PersonDto> findAll(){
        return personServiceFacade.findAll();
    }

    @ApiOperation("Get a specific person from the bank")
    @GetMapping("{id}")
    public PersonDto findById(@PathVariable String id){
       return personServiceFacade.findById(id);
    }

    @ApiOperation("Get All persons from the bank with paging")
    @GetMapping("paging")
    public Page<PersonDto> findByPage(@RequestParam int page, @RequestParam int size){

        return personServiceFacade.findAllBy(page,size);
    }

    @ApiOperation("Add a new person in the bank")
    @PostMapping
    public PersonDto insert(@Valid @RequestBody PersonDto personDto){
        return personServiceFacade.insert(personDto);
    }

    @ApiOperation("Associates a list of Accounts with a specific person")
    @PostMapping("bindsAccount")
    public PersonDto bindsAccount(@RequestBody BindAccountDto bindAccountDto){
      return personServiceFacade.bindsAccount(bindAccountDto);
    }

    @ApiOperation("Update a specific person")
    @PutMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public PersonDto update(@RequestBody PersonDto personDto){
        return personServiceFacade.update(personDto);
    }

    @ApiOperation("Delete a specific person")
    @DeleteMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id){
        personServiceFacade.delete(id);
    }

}
