package application.domain.enums;

import application.domain.bankshares.deposits.DepositSimple;
import application.domain.bankshares.transfer.TransferSimple;
import application.domain.bankshares.transfer.TransferWithSexPercent;
import application.domain.bankshares.valid.ValidBalanceLimitWithTenThousandLimit;
import application.domain.bankshares.valid.ValidBalanceLimitWithThreeHundredLimit;
import application.domain.bankshares.valid.ValidBalanceLimitWithoutLimit;
import application.domain.bankshares.withdrawals.WithdrawalRateOfFivePercent;
import application.domain.bankshares.withdrawals.WithdrawalSimple;
import application.domain.contexts.DepositContext;
import application.domain.contexts.TransferContext;
import application.domain.contexts.ValidBalanceLimitContex;
import application.domain.contexts.WithdrawalContext;


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