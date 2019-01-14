package application.domain.BankShares.transfer;

import application.domain.interfaces.shares.TransferInterface;

public class TransferSimple implements TransferInterface {
    @Override
    public Double transfer(Double balance, Double amount) {
        return balance-amount;
    }
}
