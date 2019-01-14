package application.services;

import application.exceptions.ExceptionCustom;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidPassword {

    public static void validPassword(String accountPassword, String sendPassword){
        if(!accountPassword.equals(sendPassword)){
            throw ExceptionCustom.builder().code(5).httpStatus(HttpStatus.NOT_ACCEPTABLE).message("Dado não existe no banco!").detail("A senha não é valida").build();
        }
    }
}
