package application.services;

import application.domain.Person;
import application.dto.BindAccountDto;
import application.exceptions.ExceptionCustom;
import application.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public Person bindsAccount(BindAccountDto bindAccountDto){
        Person person = findById(bindAccountDto.getId());
        bindAccountDto.getAccounts().forEach(x->person.getAccounts().add(x));//Classe invejosa.
        return update(person);
    }



    public Person findById(String id){
        return personRepository.findById(id)
                .orElseThrow(() ->
                        ExceptionCustom.builder().code(10).httpStatus(HttpStatus.NOT_FOUND).message("Dado não existe no banco!").detail("Pessoa não encontrada com o id: "+id).build());
    }

    public Person update(Person person){
        return personRepository.save(person);
    }

    public Person insert(Person person) {
        return personRepository.insert(person);
    }

    public void delete(String id){
        personRepository.deleteById(id);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }


    public Page<Person> findAllBy(PageRequest pageRequest){
        return personRepository.findAllBy(pageRequest);
    }

    public void deleteAll(){
        personRepository.deleteAll();
    }
}
