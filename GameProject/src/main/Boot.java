package main;

import static graphics.Designer.beginSession;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

import map.GameScreenManager;
import map.TileGrid;

import org.lwjgl.opengl.Display;

import utility.FileExplorer;


public class Boot {


	private static int noRows;
	private static int noColumns;
	public static Player player=null;
	public static GameScreenManager gameScreen = null;
	public Scanner keyboard = new Scanner(System.in);
	public static int[][] map  = null;
	public Boot()
	{	
		int choice;
		int mapToLoad;
		
		System.out.println("Enter '1' for New Game or '2' to load Saved game");
		choice=keyboard.nextInt();

		switch (choice) {
		case 1:
		{
			this.newGame();
			break;
		}
		case 2:
			System.out.println("Following Maps saved");
		
			FileExplorer fileExplorer = new FileExplorer();
			fileExplorer.displayXMLFiles();
			
			GameScreenManager temp = new GameScreenManager();
			System.out.println("Enter the File number of the map to Load:");
			mapToLoad=keyboard.nextInt();
						
			//System.out.println(fileExplorer.getFileName(mapToLoad));
			map=temp.loadMap(fileExplorer.getFileName(mapToLoad));
			System.out.println(map[0][1]);
			noRows= temp.getNoRows();
			noColumns=temp.getNoColumns();
			System.out.println(temp.getNoRows());
			break;
			


		}
		//noRows=10;
		//noColumns=10;
		/*int[][]map={
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,2,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0}
				};*/
		beginSession(noRows,noColumns);
		
		
		TileGrid grid=new TileGrid(map,noRows, noColumns);//draws the green tiles
		gameScreen = new GameScreenManager(grid);
		//grid.setTile(0, 0, TileType.Water);  // setting a particular tile

		player=new Player(grid);

		//DesignerButtons designerButtons = new DesignerButtons(WIDTH, HEIGHT);
		while(!Display.isCloseRequested()){

			grid.draw();
			//designerButtons.draw();
			player.setTile();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	public static int getNoRows() {
		return noRows;
	}
	public static int getNoColumns() {
		return noColumns;
	}
	
	public void newGame()
	{	
		System.out.println("Enter Number of Rows & Columns for the MAP(max. 20x20)=");
		noRows=keyboard.nextInt();
		noColumns=keyboard.nextInt();
		map  = new int[noRows][noColumns];
	}
	
	public void loadGame()
	{
		
	}
	
	public static void main(String args[])
	{
		new Boot();
	}


	public  GameScreenManager getGameScreen() {
		return gameScreen;
	}

}
