/**
 * 
 */
package junit_test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * @author eshinig
 *
 */
public class TestRunner
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	      Result result = JUnitCore.runClasses(GameTestsSuite.class);
	      for (Failure failure : result.getFailures())
	      {
	         System.out.println(failure.toString());
	      }
	      
	      System.out.println("No. od TestRun="+result.getRunCount());
	      System.out.println("No. of TestFailed="+result.getFailureCount());
	      System.out.println("All Test Passed="+result.wasSuccessful());
	   }

}




