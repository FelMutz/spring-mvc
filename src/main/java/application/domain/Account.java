package application.domain;

import application.domain.enums.AccountType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Account {

    @Id
    private String card;
    private String password;
    private Double balance;
    private AccountType accountType;

}
