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
	
	@Test
	public void testGetTime()
	{
		currentTime = Clock.getTime();
		long expectedValue = Sys.getTime() * 1000/Sys.getTimerResolution();
		assertEquals(expectedValue, currentTime);
	}
	
	@Test
	public void testGetDelta()
	{
		try 
		{
			long time1 = Sys.getTime();
			Thread.sleep(5000);
			long time2 = Sys.getTime();
			Clock.lastFrame = time1;
			float actualDelta = Clock.getDelta();
			float expectedDelta = (time2 - time1)*0.01f;
			assertEquals(expectedDelta, actualDelta);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}	
}
