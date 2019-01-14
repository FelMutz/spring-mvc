package application.services.backgroud;


import application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class SavingsInterest{
    @Autowired
    AccountService accountService;

    //@Scheduled(cron = "0 0 0 1 * *")
    @Scheduled(cron = "0 */1 * * * *")
    public void savingsInterest() {

        accountService.calcInterestMonthlyOfSavingsAccounts();
    }
}
