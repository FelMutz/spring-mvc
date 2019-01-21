package application.domain;

import application.domain.enums.PersonType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class Person {

    @Id
    private String idPerson;
    private String name;
    private Integer age;
//    @Indexed(unique = true)
    private String CPF;
    private PersonType personType;
    private String  CNPJ;

    @DBRef(lazy = true)
    private List<Account> accounts;
}
