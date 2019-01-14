package application.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class Roud {

    public static Double roudBalance(Double balance){
        return new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
