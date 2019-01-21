package application;

import application.services.PersonServiceTest;
import application.services.ValidPasswordTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ValidPasswordTest.class, PersonServiceTest.class})
public class RunAllTests {
}
