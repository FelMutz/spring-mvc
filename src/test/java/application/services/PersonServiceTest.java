package application.services;

import application.domain.Account;
import application.domain.Person;
import application.domain.enums.PersonType;
import application.dto.BindAccountDto;
import application.exceptions.ExceptionCustom;
import application.repository.PersonRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;


public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @InjectMocks
    Person person;

    @InjectMocks
    Account account;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void bindsAccount() {

        person = Person.builder()
                .idPerson("123")
                .name("Carlos")
                .personType(PersonType.PHYSICAL)
                .age(25)
                .CPF("00000000000")
                .accounts(new ArrayList<>(){{add(account);}})
                .CNPJ("4567879123")
                .build();

        List<Account> accounts = new ArrayList<>(){{
            add(account);
            add(account);
        }};

        when(personRepository.findById(person.getIdPerson())).thenReturn(Optional.of(person));
        when(personRepository.save(any())).thenReturn(person);

        BindAccountDto bindAccountDto = BindAccountDto.builder().id(person.getIdPerson()).accounts(accounts).build();

        person = personService.bindsAccount(bindAccountDto);

        assertEquals(3, person.getAccounts().size());

    }

    @Test
    public void findByIdPersonNotFound() {

        thrown.expect(ExceptionCustom.class);
        thrown.expectMessage("Dado não existe no banco!");

        when(personRepository.findById(anyString())).thenReturn(Optional.empty());
        Person personReturn = personService.findById(person.getIdPerson());

    }

    @Test
    public void insert() {

        person = Person.builder()
                .idPerson("123")
                .name("Carlos")
                .personType(PersonType.PHYSICAL)
                .age(25)
                .CPF("00000000000")
                .accounts(new ArrayList<>())
                .CNPJ("4567879123")
                .build();

        when(personService.insert(any())).thenReturn(person);

        Person personInsert = this.personService.insert (person);

       assertFalse(personInsert.getIdPerson().isEmpty());
       assertTrue(personInsert.equals(person));

    }

    @Test
    public void delete() {
        person = Person.builder()
                .idPerson("123")
                .name("Carlos")
                .personType(PersonType.PHYSICAL)
                .age(25)
                .CPF("00000000000")
                .accounts(new ArrayList<>())
                .CNPJ("4567879123")
                .build();

        when(personService.insert(any())).thenReturn(person);


        Person personInsert = this.personService.insert (person);

        assertFalse(personInsert.getIdPerson().isEmpty());

        when(personRepository.findById(anyString())).thenReturn(Optional.of(person));

        personService.delete(person.getIdPerson());

    }

    @Test
    public void findAll() {

        List<Person> personList = new ArrayList<>(){{
            add(person);
            add(person);
            add(person);
        }};


        when(personRepository.findAll()).thenReturn(personList);

        List<Person> persons = personService.findAll();

        assertEquals(3, persons.size());
        verify(personRepository, times(1)).findAll();

    }

    @Test
    public void findAllBy() {

        List<Person> personList = new ArrayList<>(){{
            add(person);
            add(person);
            add(person);
            add(person);
            add(person);
        }};

        PageRequest pageRequest = PageRequest.of(0, 3);
        when(personRepository.findAllBy(any())).thenReturn(new PageImpl<>(personList.subList(0,3)));

        Page<Person> personPage = personService.findAllBy(pageRequest);

        assertEquals(3,personPage.getNumberOfElements());
    }

}