package application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class BankSharesDto {

    @NotNull(message = "O cartão é obrigatório.")
    @NotEmpty(message = "O cartão não pode ser vazio.")
    private String card;

    @NotNull(message = "A senha é obrigatória.")
    @NotEmpty(message = "A senha não pode ser vazia.")
    private String password;

    @NotNull(message = "Você deve informar o valor desejado.")
    private Double amount;

    private String accountTransfer;
}
