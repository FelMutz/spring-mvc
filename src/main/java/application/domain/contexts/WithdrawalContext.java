package application.domain.contexts;

import application.domain.interfaces.shares.WithdrawalInterface;

public class WithdrawalContext {
    private WithdrawalInterface withdrawalInterface;

    public WithdrawalContext(WithdrawalInterface withdrawalInterface){
        this.withdrawalInterface = withdrawalInterface;
    }

    public Double executWithdrawal(Double balance, Double amount){
        return withdrawalInterface.withdrawal(balance,amount);
    }
}
