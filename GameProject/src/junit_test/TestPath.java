package junit_test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ai.Path;
import ai.PathValidationCode;
import junit.framework.TestCase;
import main.View;
import map.TileGrid;

public class TestPath extends TestCase
{

	@Before
	public void setUp() throws Exception
	{
		View.setNoRows(5);
		View.setNoColumns(5);
	}

	@After
	public void tearDown() throws Exception 
	{
		TileGrid.pathCordinate.clear();
	}

	@Test
	public void testCopyArray() 
	{
		ArrayList<Integer> arr1 = new  ArrayList<Integer>();
		ArrayList<Integer> arr2 = new  ArrayList<Integer>();
		arr1.add(2);
		arr1.add(3);
		arr2 = Path.copyArray(arr1);
		assertEquals(arr2, arr1);
	}

	@Test
	public void testIsPathValid_PathOK()
	{
		Integer[] pathList = {1,6,5};
		TileGrid.pathCordinate.clear();
		TileGrid.pathCordinate.addAll(Arrays.asList(pathList));
		assertEquals("Path is not OK",PathValidationCode.PATH_OK, Path.isPathValid());
	}
	
	@Test
	public void testIsPathValid_PathNoExitEntry() 
	{
		assertEquals(PathValidationCode.PATH_NO_EXIT_ENTRY, Path.isPathValid());
	}
	
	@Test
	public void testIsPathValid_PathManyExitEntry() 
	{
		Integer[] pathList = {1,6,5,15};
		TileGrid.pathCordinate.addAll(Arrays.asList(pathList));
		assertEquals(PathValidationCode.PATH_MANY_EXIT_ENTRY, Path.isPathValid());
	}
//	@Test
//	public void testIsPathValid_PathManyRoutesFound()
//	{
//		TileGrid.pathCordinate.add(1);
//		TileGrid.pathCordinate.add(6);
//		TileGrid.pathCordinate.add(5);
//		TileGrid.pathCordinate.add(12);
//		assertEquals(PathValidationCode.PATH_MANY_ROUTES_FOUND, Path.isPathValid());
//	}
}
