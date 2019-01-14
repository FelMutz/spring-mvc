package application.domain.BankShares.valid;

import application.domain.interfaces.shares.ValidBalanceLimitInterface;
import application.exceptions.ExceptionCustom;
import org.springframework.http.HttpStatus;

public class ValidBalanceLimitWithoutLimit implements ValidBalanceLimitInterface {
    @Override
    public void validBalanceLimit(Double newBalance) {
        if (0 > newBalance) {
            throw ExceptionCustom.builder().code(15).httpStatus(HttpStatus.NOT_ACCEPTABLE).message("Erro na Requisição").detail("Saldo Insuficiente").build();
        }
    }
}
