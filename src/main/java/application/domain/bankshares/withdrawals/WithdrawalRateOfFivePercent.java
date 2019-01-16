package application.domain.bankshares.withdrawals;

import application.domain.interfaces.shares.WithdrawalInterface;

public class WithdrawalRateOfFivePercent implements WithdrawalInterface {
    @Override
    public Double withdrawal(Double balance, Double amount) {
        return balance - amount - amount * 5 / 100;
    }
}
