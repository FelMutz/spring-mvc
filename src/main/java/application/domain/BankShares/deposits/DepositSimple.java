package application.domain.BankShares.deposits;

import application.domain.interfaces.shares.DepositInterface;

public class DepositSimple implements DepositInterface {
    @Override
    public Double deposit(Double balance, Double amount) {
        return balance + amount;
    }
}
