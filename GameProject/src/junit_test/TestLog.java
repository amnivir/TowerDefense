package junit_test;

import static graphics.Designer.beginSession;
import graphics.Designer;
import map.Tile;
import map.TileType;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;

import tower.Tower;
import tower.TowerFactory;
import utility.Log;
import junit.framework.TestCase;

public class TestLog extends TestCase 
{
	Tower t1,t2,t3;
	@Before
	public void setUp() throws Exception 
	{
		beginSession(5,5);
		t1=TowerFactory.getTower("freez", Designer.quickTexture("freezBase"),new Tile(32, 32, 32, 32, TileType.TowerFreez));
		t2=TowerFactory.getTower("bomb", Designer.quickTexture("bombBase"),new Tile(64, 64, 32, 32, TileType.TowerBomb));
		t3=TowerFactory.getTower("cannon", Designer.quickTexture("cannonBase"),new Tile(32, 32, 32, 32, TileType.TowerCannon));
		
        Log.addLogMessage(t3.getClass().getSimpleName().toString() + t3.getX()/32 + t3.getY()/32, "tower bought");
        Log.addLogMessage(t2.getClass().getSimpleName().toString() + t2.getX()/32 + t2.getY()/32, "tower bought");
        Log.addLogMessage(t1.getClass().getSimpleName().toString() + t1.getX()/32 + t1.getY()/32, "tower bought");
	}
	
	@Test
	public void testAllTowersLog() 
	{
		assertEquals(true, Log.printLogs(""));
		Display.destroy();
	}
	@Test
	public void testAllTowersLogFalse() 
	{
		assertEquals(false, Log.printLogs(null));
		Display.destroy();
	}
	@Test
	public void testCannonTowersLog() 
	{
		assertEquals(true, Log.printLogs(t3.getClass().getSimpleName().toString()));
		Display.destroy();
	}
	@Test
	public void testBombTowersLog() 
	{
		assertEquals(true, Log.printLogs(t2.getClass().getSimpleName().toString()));
		Display.destroy();
	}
	@Test
	public void testFreezTowersLog() 
	{
		assertEquals(true, Log.printLogs(t2.getClass().getSimpleName().toString()));
		Display.destroy();
	}
	@Test
	public void testPerticularCannonTowerLog() 
	{
		assertEquals(true, Log.printLogs(t3.getClass().getSimpleName().toString()+t3.getX()/32+t3.getY()/32));
		Display.destroy();
	}
	@Test
	public void testPerticularBombTowerLog() 
	{
		assertEquals(true, Log.printLogs(t2.getClass().getSimpleName().toString()+t2.getX()/32+t2.getY()/32));
		Display.destroy();
	}
	@Test
	public void testPerticularFreezTowerLog() 
	{
		assertEquals(true, Log.printLogs(t2.getClass().getSimpleName().toString()+t1.getX()/32+t1.getY()/32));
		Display.destroy();
	}
}
