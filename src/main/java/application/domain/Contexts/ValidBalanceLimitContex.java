package application.domain.Contexts;

import application.domain.interfaces.shares.ValidBalanceLimitInterface;

public class ValidBalanceLimitContex {

    private ValidBalanceLimitInterface validBalanceLimitInterface;

    public ValidBalanceLimitContex(ValidBalanceLimitInterface validBalanceLimitInterface){this.validBalanceLimitInterface = validBalanceLimitInterface;}

    public void executvalidBalanceLimit(Double newBalance){
        validBalanceLimitInterface.validBalanceLimit(newBalance);
    }
}
