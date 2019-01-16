package application.domain.contexts;

import application.domain.interfaces.shares.TransferInterface;

public class TransferContext {

    private TransferInterface transferInterface;

    public TransferContext(TransferInterface transferInterface){this.transferInterface = transferInterface;}

    public Double executTransfer(Double balance, Double amount){
        return transferInterface.transfer(balance,amount);
    }
}
