package application.dto;

import application.domain.enums.AccountType;
import application.dto.validator.CheckPassword;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AccountDto extends ResourceSupport {

    private String card;

    @NotNull(message = "A senha é obrigatória.")
    @NotEmpty(message = "A senha não pode ser vazia.")
    @CheckPassword(message = "Senha deve ter mais que 6 caracteres")
    private String password;
    private Double balance;
    private AccountType accountType;

}
