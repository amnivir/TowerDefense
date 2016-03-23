package junit_test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGameScreenManager.class, TestPath.class, TestTower.class, ClockTest.class, TestStrategy.class})
public class GameTestsSuite {

}
