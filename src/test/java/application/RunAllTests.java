package application;

import application.controllers.AccountControllerTest;
import application.controllers.PersonControllerTest;
import application.services.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ValidPasswordTest.class,
        PersonServiceTest.class,
        AccountServiceTest.class,
        BankSharesServicesTest.class,
        RoudTest.class,
        AccountControllerTest.class,
        PersonControllerTest.class})
public class RunAllTests {
}
