package application.domain.bankshares.transfer;

import application.domain.interfaces.shares.TransferInterface;

public class TransferWithSexPercent implements TransferInterface {
    @Override
    public Double transfer(Double balance, Double amount) {
        return balance-amount-(amount*6/100);
    }
}
