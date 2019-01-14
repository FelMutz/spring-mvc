package application.dto;

import application.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class BindAccountDto {
    @NotNull(message = "O id da pessoa é obrigatorio.")
    @NotEmpty(message = "O id da pessoa não pode ser vazio.")
    private String id;

    private List<Account> accounts;
}
