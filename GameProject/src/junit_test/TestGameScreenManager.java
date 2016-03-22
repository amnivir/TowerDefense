package junit_test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.View;
import map.GameScreenManager;
import map.Tile;
import map.TileGrid;

/**
 * @author eshinig
 *
 */
public class TestGameScreenManager 
{
	GameScreenManager gamescreen = null;
	TileGrid grid = null;
	static int[][] tileArray;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		View.setNoRows(5);
		View.setNoColumns(5);
		
		/*
		 * This path list is valid path for matrix 5X5
		 */
		Integer[] pathList = {1,6,5};
		TileGrid.pathCordinate.addAll(Arrays.asList(pathList));
		
		/*
		 * Map to save
		 */
		tileArray = new int[][]
						{
						  { 1, 0, 0, 0, 0},
						  { 0, 1, 0, 0, 0},
						  { 0, 0, 1, 0, 0},
						  { 0, 0, 0, 1, 0},
						  { 0, 0, 0, 0, 1}
						};
	}

	/**
	 * Test method for {@link map.GameScreenManager#saveMap()}.
	 */
	@Test
	public void testSaveMap()
	{
		gamescreen = new GameScreenManager();
		assertTrue("Map not saved", gamescreen.saveMap(tileArray,gamescreen));
	}

}
