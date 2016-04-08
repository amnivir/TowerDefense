package junit_test;

import org.junit.Test;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

import tower.TowerFreez;
import utility.Clock;
import junit.framework.TestCase;

public class ClockTest extends TestCase
{
	long currentTime ;
	public static long lastFrame;
	/**
	 * THis method tests the getting of time
	 */
	@Test
	public void testGetTime()
	{
		currentTime = Clock.getTime();
		long expectedValue = Sys.getTime() * 1000/Sys.getTimerResolution();
		assertEquals(expectedValue, currentTime);
	}
	/**
	 * THis method tests delta function
	 */
	@Test
	public void testGetDelta_NotZero()
	{
		try 
		{
			long time1 = Sys.getTime();
			Thread.sleep(100);// 100 milliseconds
			Clock.lastFrame = time1;
			float actualDelta = Clock.getDelta();
			
			assertFalse(actualDelta == 0);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}	
	/**
	 * THis method tests delta function
	 */
	@Test
	public void testGetDelta()
	{
		try 
		{
			long time1 = Sys.getTime();
			Thread.sleep(100); // 100 milliseconds
			Clock.lastFrame = time1;
			long time2 = Sys.getTime();
			float actualDelta = Clock.getDelta();
			float expectedDelta = (time2 - time1)*0.01f;
			
			assertTrue(actualDelta >= expectedDelta);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}	
}
