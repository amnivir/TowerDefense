package ai;

import java.util.ArrayList;

import main.Boot;
import map.TileGrid;

public class Path {

	public static ArrayList<Integer> tempPath;
	public static ArrayList<Integer> continousPath;
	
	
	public static void copyArray(ArrayList<Integer> arr)
	{	continousPath = new ArrayList<Integer>();
		tempPath = new  ArrayList<Integer>();
		for (Integer coordinate:arr)
		{
			tempPath.add(coordinate);
		}
	}

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
		for(Integer x:pathCordinate)
		{	
			if(x<noColumns)
			{
			startEndPathCordinate[noExitEntryCoordinate]=x;
			noExitEntryCoordinate++;
			continue;

			}
			for(int i=(noRows-1)*noColumns;i<(noColumns*noRows);i++)
			{
				if(x==i)
				{	
				startEndPathCordinate[noExitEntryCoordinate]=x;
				noExitEntryCoordinate++;
				continue;
				}
			}

			for(int i=noColumns;i<(noRows*noColumns)-noColumns;i=i+noColumns)
			{if(x==i)
			{
			startEndPathCordinate[noExitEntryCoordinate]=x;
			noExitEntryCoordinate++;
			continue;
			}
			}
			for(int i=2*noColumns-1;i<(noRows*noColumns)-noColumns;i=i+noColumns)
			{if(x==i)
			{startEndPathCordinate[noExitEntryCoordinate]=x;
			noExitEntryCoordinate++;
			continue;
			}
			}


		}

		if(noExitEntryCoordinate>2)
		{
			System.out.println("No of EntryExit points more than 2. Invalid Path!");
			return PathValidationCode.PATH_MANY_EXIT_ENTRY;
		}

		if(noExitEntryCoordinate<2)
		{
			System.out.println("No of EntryExit points less than 2. Invalid Path!");
			return PathValidationCode.PATH_NO_EXIT_ENTRY;
		}


		//startEndPathCordinate
		//int startingCoordinate=arrangedpathCordinate[0];
		System.out.println("No of Boundrypoints="+noExitEntryCoordinate);
		//	continousPath=arrangedpathCordinate[0];
		//copies the path cordinate to temparray
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
		return PathValidationCode.PATH_OK;

	}
}
