package application.mappers;

import application.adapter.PageConverter;
import application.controllers.PersonController;
import application.domain.Person;
import application.domain.enums.PersonType;
import application.dto.PersonDto;
import com.google.common.base.Function;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PersonMap {

    public static Person dtoToMap(PersonDto personDto){

        return Optional.ofNullable(personDto).map(person -> Person.builder()
                .idPerson(personDto.getIdPerson())
                .age(personDto.getAge())
                .CPF(personDto.getCPF())
                .name(personDto.getName())
                .personType(personDto.getPersonType() != null ? personDto.getPersonType() : PersonType.PHYSICAL)
                .CNPJ(personDto.getCNPJ())
                .accounts(
                        personDto.getAccountsDto() !=null ?
                                personDto.getAccountsDto()
                                        .stream()
                                        .map(accountDto -> AccountMap.dtoToMap(accountDto))
                                        .collect(Collectors.toList())
                                : new ArrayList<>()
                )
                .build()).orElse(null);
    }

    public static PersonDto mapToDto(Person person){

        return Optional.ofNullable(person).map(personDto ->{
           PersonDto personDtoLink = PersonDto.builder()
                .idPerson(person.getIdPerson())
                .age(person.getAge())
                .CPF(person.getCPF())
                .name(person.getName())
                .personType(person.getPersonType())
                .CNPJ(person.getCNPJ())
                .accountsDto(
                        person.getAccounts().stream()
                        .map(account -> AccountMap.mapToDto(account))
                        .collect(Collectors.toList()))
                .build();
            personDtoLink.add(linkTo(methodOn(PersonController.class).findById(personDtoLink.getIdPerson())).withSelfRel());
            personDtoLink.add(linkTo(methodOn(PersonController.class).findAll()).withRel("BuscaTodasPessoas"));
            return personDtoLink;
        }).orElse(null);
    }

    public static Page<PersonDto> mapPageToDtoPage(Page<Person> accountPage){
        return PageConverter.convert(accountPage).using(new Function<Person, PersonDto>() {
            @Override
            public PersonDto apply(Person input) {
                return mapToDto(input);
            }
        });
    }
}
