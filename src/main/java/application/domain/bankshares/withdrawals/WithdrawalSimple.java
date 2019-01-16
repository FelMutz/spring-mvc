package application.domain.bankshares.withdrawals;

import application.domain.interfaces.shares.WithdrawalInterface;

public class WithdrawalSimple implements WithdrawalInterface {
    @Override
    public Double withdrawal(Double balance, Double amount) {
        return balance-amount;
    }
}
