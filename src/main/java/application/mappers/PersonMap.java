package application.mappers;

import application.domain.Person;
import application.domain.enums.PersonType;
import application.dto.PersonDto;

import java.util.ArrayList;
import java.util.Optional;

public class PersonMap {

    public static Person dtoToMap(PersonDto personDto){

        return Optional.ofNullable(personDto).map(person -> Person.builder()
                        .idPerson(personDto.getIdPerson())
                        .age(personDto.getAge())
                        .CPF(personDto.getCPF())
                        .name(personDto.getName())
                        .personType(personDto.getPersonType() != null ? personDto.getPersonType() : PersonType.PHYSICAL)
                        .CNPJ(personDto.getCNPJ())
                        .accounts(new ArrayList<>())
                        .build()).orElse(null);
    }

    public static PersonDto mapToDto(Person person){

        return Optional.ofNullable(person).map(personDto -> PersonDto.builder()
                .idPerson(person.getIdPerson())
                .age(person.getAge())
                .CPF(person.getCPF())
                .name(person.getName())
                .personType(person.getPersonType())
                .CNPJ(person.getCNPJ())
                .build()).orElse(null);
    }
}
