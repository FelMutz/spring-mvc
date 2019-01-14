package application.dto;

import application.domain.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto extends ResourceSupport {


    private String idPerson;

    @NotBlank(message = "O nome é obrigatório.")
    @NotEmpty(message = "O nome não pode ser vazio.")
    @Pattern(regexp = "^[^\\d]+$", message = "Nome não pode ter números.")
    private String name;

    private Integer age;

    @NotNull(message = "O CPF é obrigatório.")
    @NotEmpty(message = "O CPF não pode ser vazio.")
    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})", message = "O CPF não é valido.")
    private String CPF;
    private PersonType personType;
    private String  CNPJ;


}
