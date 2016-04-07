package junit_test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPath.class, TestGameScreenManager.class,  TestTower.class, ClockTest.class, 
	TestStrategy.class, TestController.class, TestGameStateManager.class,TestLog.class})
public class GameTestsSuite {

}
