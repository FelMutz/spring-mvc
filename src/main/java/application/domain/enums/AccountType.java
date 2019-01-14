package application.domain.enums;

import application.domain.BankShares.deposits.DepositSimple;
import application.domain.BankShares.transfer.TransferSimple;
import application.domain.BankShares.transfer.TransferWithSexPercent;
import application.domain.BankShares.valid.ValidBalanceLimitWithTenThousandLimit;
import application.domain.BankShares.valid.ValidBalanceLimitWithThreeHundredLimit;
import application.domain.BankShares.valid.ValidBalanceLimitWithoutLimit;
import application.domain.BankShares.withdrawals.WithdrawalRateOfFivePercent;
import application.domain.BankShares.withdrawals.WithdrawalSimple;
import application.domain.Contexts.DepositContext;
import application.domain.Contexts.TransferContext;
import application.domain.Contexts.ValidBalanceLimitContex;
import application.domain.Contexts.WithdrawalContext;


public enum  AccountType {
    NORMAL,

    PRIVATE{
        public Double withdrawal(Double balance, Double amount){
            WithdrawalContext withdrawalContext = new WithdrawalContext(new WithdrawalRateOfFivePercent());
            return withdrawalContext.executWithdrawal(balance,amount);
        }

        public Double transfer(Double balance, Double amount){
            TransferContext transferContext = new TransferContext(new TransferSimple());
            return transferContext.executTransfer(balance,amount);
        }

        public void validBalanceLimit(Double newBalance){
            ValidBalanceLimitContex validBalanceLimitContex = new ValidBalanceLimitContex(new ValidBalanceLimitWithTenThousandLimit());
            validBalanceLimitContex.executvalidBalanceLimit(newBalance);
        }
    },

    SAVING{
        public void validBalanceLimit(Double newBalance){
            ValidBalanceLimitContex validBalanceLimitContex = new ValidBalanceLimitContex(new ValidBalanceLimitWithThreeHundredLimit());
            validBalanceLimitContex.executvalidBalanceLimit(newBalance);
        }
    };

    public Double withdrawal(Double balance, Double amount){
        WithdrawalContext withdrawalContext = new WithdrawalContext(new WithdrawalSimple());
        return withdrawalContext.executWithdrawal(balance,amount);
    }

    public Double deposit(Double balance, Double amount) {
        DepositContext depositContext = new DepositContext(new DepositSimple());
        return depositContext.executDeposit(balance,amount);
    }

    public Double transfer(Double balance, Double amount){
        TransferContext transferContext = new TransferContext(new TransferWithSexPercent());
        return transferContext.executTransfer(balance, amount);
    }

    public void validBalanceLimit(Double newBalance){
        ValidBalanceLimitContex validBalanceLimitContex = new ValidBalanceLimitContex(new ValidBalanceLimitWithoutLimit());
        validBalanceLimitContex.executvalidBalanceLimit(newBalance);
    }

}