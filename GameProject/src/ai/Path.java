package ai;

import java.util.ArrayList;

import main.Boot;
import map.TileGrid;
/**
 * This class determines if the path created by player is valid
 * @author s_niga
 *
 */
public class Path {

	public static ArrayList<Integer> tempPath;
	public static ArrayList<Integer> continousPath;


	public static void copyArray(ArrayList<Integer> arr)
	{	
		continousPath = new ArrayList<Integer>();
		tempPath = new  ArrayList<Integer>();
		for (Integer coordinate:arr)
		{
			tempPath.add(coordinate);
		}
	}
/**
 * This method performs the path validation in the map returns the appropriate error code   
 * @return PathValidationCode returns the enum code defined in class PathValidationCode
 */
	public static PathValidationCode isPathValid()
	{
		ArrayList<Integer> pathCordinate;
		pathCordinate = TileGrid.pathCordinate;
		int[] startEndPathCordinate=new int[2]; 
		int noExitEntryCoordinate=0;
		int noRows=Boot.getNoRows();
		int noColumns=Boot.getNoColumns();
		int currentCoordinate;
		//Find boundry ; entry and exit points must be exactly equal to two

		if(noExitEntryCoordinate >=2 )
		{
			System.out.println("No of EntryExit points more than 2. Invalid Path!");
			return PathValidationCode.PATH_MANY_EXIT_ENTRY;
		}
		for(Integer x:pathCordinate)
		{	//check if the path coordinate is on upper Y axis boundary
			if(x<noColumns && noExitEntryCoordinate<2)
			{
				startEndPathCordinate[noExitEntryCoordinate]=x;
				noExitEntryCoordinate++;
				continue;

			}
			//check if the path coordinate is on lower Y axis boundary
			for(int i=(noRows-1)*noColumns;i<(noColumns*noRows);i++)
			{
				if(x==i && noExitEntryCoordinate<2)
				{	
					startEndPathCordinate[noExitEntryCoordinate]=x;
					noExitEntryCoordinate++;
					continue;
				}
			}
			//check if the path coordinate is on left X boundary
			for(int i=noColumns;i<(noRows*noColumns)-noColumns;i=i+noColumns)
			{if(x==i && noExitEntryCoordinate<2 )
			{
				startEndPathCordinate[noExitEntryCoordinate]=x;
				noExitEntryCoordinate++;
				continue;
			}
			}
			//check if the path coordinate is on right X boundary
			for(int i=2*noColumns-1;i<(noRows*noColumns)-noColumns;i=i+noColumns)
			{if(x==i && noExitEntryCoordinate<2)
			{startEndPathCordinate[noExitEntryCoordinate]=x;
			noExitEntryCoordinate++;
			continue;
			}
			}


		}

		if(noExitEntryCoordinate<2)
		{
			System.out.println("No of EntryExit points less than 2. Invalid Path!");
			return PathValidationCode.PATH_NO_EXIT_ENTRY;
		}

		System.out.println("No of Boundrypoints="+noExitEntryCoordinate);

		copyArray(pathCordinate);

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
			return PathValidationCode.PATH_OK;

		else 
			return PathValidationCode.PATH_MANY_ROUTES_FOUND;

	}
}
