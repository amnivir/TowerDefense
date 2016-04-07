
package junit_test;

import static graphics.Designer.beginSession;
import static graphics.Designer.quickTexture;
import graphics.Designer;
import junit.framework.TestCase;
import main.Controller;
import main.View;
import map.Tile;
import map.TileGrid;
import map.TileType;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;

import critter.CritterFactory;
import critter.Critter_A;
import ai.Strategy;
import tower.Context;
import tower.EffectType;
import tower.ShootStrategyEnum;
import tower.StrategyShootClosestCritter;
import tower.StrategyShootNearestEndPointCritter;
import tower.StrategyShootStrongestCritter;
import tower.StrategyShootWeakestCritter;
import tower.TowerCannon;
import tower.TowerFactory;

public class TestStrategy extends TestCase 
{
	@Before
	public void setUp() throws Exception 
	{
		beginSession(5,5);
		int noRows=5;
		int noColumns=5;
		int [][]map=new int[5][5]; 
		View.grid= new TileGrid(map, noRows, noColumns);
		 
		
	}
	
	@Test
	public void testExecute()
	{	
		Context context = new Context(new StrategyShootClosestCritter());	
		assertEquals(0,context.executeStrategy(0,0,30,0,EffectType.bomb,ShootStrategyEnum.closestCritter, null));
		Display.destroy();
	}
	@Test
	public void testExecute1()
	{	
		Context context = new Context(new StrategyShootWeakestCritter());	
		assertEquals(0,context.executeStrategy(0,0,30,0,EffectType.bomb,ShootStrategyEnum.closestCritter, null));
		Display.destroy();
	}
	@Test
	public void testExecute2()
	{	
		Context context = new Context(new StrategyShootStrongestCritter());	
		assertEquals(0,context.executeStrategy(0,0,30,0,EffectType.bomb,ShootStrategyEnum.closestCritter, null));
		Display.destroy();
	}
	@Test
	public void testExecute3()
	{	
		Context context = new Context(new StrategyShootNearestEndPointCritter());	
		assertEquals(0,context.executeStrategy(0,0,30,0,EffectType.bomb,ShootStrategyEnum.closestCritter, null));
		Display.destroy();
	}
}
