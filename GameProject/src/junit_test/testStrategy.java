package junit_test;

import junit.framework.TestCase;

import org.junit.Test;
import org.lwjgl.opengl.Display;

import ai.Strategy;
import ai.TargetCritter;
import ai.TargetEndTile;
import ai.TargetStartTile;
import tower.TowerCannon;

public class testStrategy extends TestCase 
{
	@Test
	public void testexecute()
	{
		Strategy targetCritter = new TargetCritter();
		Strategy targetEndTile = new TargetEndTile(); 
		Strategy targetStartTile = new TargetStartTile();
		int targetCritterValue = targetCritter.execute(3);
		int targetEndTileValue = targetEndTile.execute(2);
		int targetStartTileValue = targetStartTile.execute(1);
		
		int expectedValueTC = 3;
		int expectedValueTET = 2;
		int expectedValueTST = 1;
		
		assertEquals(expectedValueTC, targetCritterValue);
		assertEquals(expectedValueTET, targetEndTileValue);
		assertEquals(expectedValueTST, targetStartTileValue);
	}
}
