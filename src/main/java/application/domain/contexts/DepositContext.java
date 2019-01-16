package application.domain.contexts;

import application.domain.interfaces.shares.DepositInterface;

public class DepositContext {
    private DepositInterface depositInterface;

    public DepositContext(DepositInterface depositInterface){
        this.depositInterface = depositInterface;
    }

    public Double executDeposit(Double balance, Double amount){
        return depositInterface.deposit(balance,amount);
    }
}
