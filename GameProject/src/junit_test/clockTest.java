package junit_test;

import org.junit.Test;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

import tower.TowerFreez;
import utility.Clock;
import junit.framework.TestCase;

public class clockTest extends TestCase{
	long currentTime ;
	public static long lastFrame;
	
	
	@Test
	public void testgetTime()
	{
		currentTime = Clock.getTime()*1000/Sys.getTimerResolution();
		long expectedValue = Sys.getTime()*1000/Sys.getTimerResolution();
		assertEquals(expectedValue, currentTime);
	}
	
	@Test
	public void testgetDelta()
	{
		currentTime = Clock.getTime()*1000/Sys.getTimerResolution();
		lastFrame =  Clock.getTime()*1000/Sys.getTimerResolution();;
		int delta = (int)(currentTime-lastFrame);
		float actualValue = delta*0.01f;
		long expectedValue = Sys.getTime()*1000/Sys.getTimerResolution();
		int delta1 = (int)(expectedValue-lastFrame);
		float expectedValue1 = delta1*0.01f;
		assertEquals(expectedValue1, actualValue);
		
	}	
}
