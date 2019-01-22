package application.facade;

import application.domain.Person;
import application.dto.BindAccountDto;
import application.dto.PersonDto;
import application.exceptions.ExceptionCustom;
import application.mappers.AccountMap;
import application.mappers.PersonMap;
import application.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


import static application.mappers.PersonMap.*;


import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PersonServiceFacade {

    PersonService personService;

    public PersonDto bindsAccount(BindAccountDto bindAccountDto){
        return mapToDto(personService.bindsAccount(bindAccountDto));
    }

    public PersonDto findById(String id){
        return mapToDto(personService.findById(id));
    }

    public PersonDto update(PersonDto person){
        return mapToDto(personService.update(dtoToMap(person)));
    }

    public PersonDto insert(PersonDto person) {
        return mapToDto(personService.insert(dtoToMap(person)));
    }

    public void delete(String id){
        personService.delete(id);
    }

    public List<PersonDto> findAll(){
        return personService.findAll().stream().map(PersonMap::mapToDto).collect(Collectors.toList());
    }


    public Page<PersonDto> findAllBy(Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return mapPageToDtoPage(personService.findAllBy(pageRequest));
    }

    public void deleteAll(){
        personService.deleteAll();
    }
}
