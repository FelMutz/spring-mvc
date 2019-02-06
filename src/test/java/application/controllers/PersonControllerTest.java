package application.controllers;

import application.domain.Person;
import application.dto.PersonDto;
import application.facade.PersonServiceFacade;
import application.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

@WebMvcTest(controllers = PersonController.class)

public class PersonControllerTest {

//    @MockBean
//    PersonRepository personRepository;
//
//    @InjectMocks
//    Person person;


    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PersonServiceFacade personServiceFacade;


    @InjectMocks
    PersonDto person;

//    @InjectMocks
//    private PersonController personController = new PersonController(this.personServiceFacade);

//    @InjectMocks
//    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

    @Autowired
    private MockMvc mockMvc;



    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findAll() throws Exception {

        List<PersonDto> persons = new ArrayList<>(){{
            add(person);
            add(person);
        }};

        when(personServiceFacade.findAll()).thenReturn(persons);

        mockMvc.getDispatcherServlet();


        mockMvc.perform(get("/persons").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(get("/persons/123"))
                .andExpect(status().isOk());
    }

    @Test
    public void insertBadRequest() throws Exception {
        mockMvc.perform(post ("/persons").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void insert() throws Exception {
        mockMvc.perform(post ("/persons").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PersonDto.builder().idPerson("123").name("Teste").CPF("00000000000").age(50).build())))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(put ("/persons").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PersonDto.builder().idPerson("123").age(50).build())))
                .andExpect(status().isOk());
    }
}