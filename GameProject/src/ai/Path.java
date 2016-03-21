package ai;

import java.util.ArrayList;

import main.View;
import map.TileGrid;
/**
 * This class determines if the path created by player is valid
 * @author s_niga
 *
 */

public class Path 
{
	public static ArrayList<Integer> tempPath=null;
	public static ArrayList<Integer> continousPath=null;

	public static ArrayList<Integer> copyArray(ArrayList<Integer> arr)
	{	
		ArrayList<Integer> temp = new  ArrayList<Integer>();
		for (Integer coordinate:arr)
		{
			temp.add(coordinate);
		}
		return temp;
	}
	
	/**
	 * This method performs the path validation in the map and returns the appropriate error code   
	 * @return PathValidationCode returns the enum code defined in class PathValidationCode
	 */
	public static PathValidationCode isPathValid()
	{
		ArrayList<Integer> pathCordinate;
		pathCordinate = TileGrid.pathCordinate;
		continousPath = new ArrayList<Integer>();
		int[] startEndPathCordinate=new int[2]; 
		int noExitEntryCoordinate=0;
		int noRows=View.getNoRows();
		int noColumns=View.getNoColumns();
		int currentCoordinate;
		boolean boundryTile=false;
		//Find boundry ; entry and exit points must be exactly equal to two

		for(Integer x:pathCordinate)
		{	//check if the path coordinate is on upper Y axis boundary
			if(x<noColumns)
			{
				boundryTile=true;
			}
			
			//check if the path coordinate is on lower Y axis boundary
			for(int i=(noRows-1)*noColumns;i<(noColumns*noRows);i++)
			{
				if(x==i)
				{
					boundryTile=true;
					break;
				}
			}
			
			//check if the path coordinate is on left X boundary
			for(int i=noColumns;i<(noRows*noColumns)-noColumns;i=i+noColumns)
			{
				if(x==i)
				{
					boundryTile=true;
					break;
				}
			}
			
			//check if the path coordinate is on right X boundary
			for(int i=2*noColumns-1;i<(noRows*noColumns)-noColumns;i=i+noColumns)
			{
				if(x==i)
				{
					boundryTile=true;
					break;
				}

			}

			if(boundryTile)
			{
				if(noExitEntryCoordinate+1>2)
					return PathValidationCode.PATH_MANY_EXIT_ENTRY;
				else
				{
					startEndPathCordinate[noExitEntryCoordinate]=x;
					noExitEntryCoordinate++;
					boundryTile=false;
				}
			}
		}
		
		if(noExitEntryCoordinate<2)
		{
			System.out.println("No of EntryExit points less than 2. Invalid Path!");
			return PathValidationCode.PATH_NO_EXIT_ENTRY;
		}

		System.out.println("No of Boundrypoints="+noExitEntryCoordinate);
		tempPath = copyArray(pathCordinate);
		currentCoordinate=startEndPathCordinate[0];
		continousPath.add(startEndPathCordinate[0]);
		tempPath.remove(new Integer(currentCoordinate));
		boolean adjacentCoordinate=false;
		
		for(int i=0;i<pathCordinate.size();i++)
		{
			for(Integer x:tempPath)
			{
				if((currentCoordinate+1)==x||(currentCoordinate-1)==x||(currentCoordinate+noColumns)==x||(currentCoordinate-noColumns)==x)
				{
					continousPath.add(x);
					currentCoordinate=x;
					adjacentCoordinate=true;
					break;
				}
			}
			if(adjacentCoordinate)
			{
				tempPath.remove(new Integer(currentCoordinate));
			}
		}
		
		System.out.println("Continuous Path"+continousPath);
		System.out.println("Temp Path"+tempPath);
		
		if(continousPath.size() == pathCordinate.size())
		{
			return PathValidationCode.PATH_OK;
		}

		else 
		{
			return PathValidationCode.PATH_MANY_ROUTES_FOUND;
		}
	}
}
