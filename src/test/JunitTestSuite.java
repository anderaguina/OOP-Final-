package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestController.class,
   TestName.class,
   TestTab1.class
})

public class JunitTestSuite {   
}  	