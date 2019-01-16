package application.domain.bankshares.valid;

import application.domain.interfaces.shares.ValidBalanceLimitInterface;
import application.exceptions.ExceptionCustom;
import org.springframework.http.HttpStatus;

public class ValidBalanceLimitWithThreeHundredLimit implements ValidBalanceLimitInterface {
    @Override
    public void validBalanceLimit(Double newBalance) {
        if (-300 > newBalance) {
            throw ExceptionCustom.builder().code(15).httpStatus(HttpStatus.NOT_ACCEPTABLE).message("Erro na Requisição").detail("Saldo Insuficiente").build();
        }
    }
}
