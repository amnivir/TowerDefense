package main;

import static graphics.Designer.*;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

import map.GameScreenManager;
import map.Tile;
import map.TileGrid;
import map.TileType;

import org.lwjgl.opengl.Display;

import tower.TowerCannon;
import utility.FileExplorer;

/**
 * This is main the class which handles all operations and takes user input
 * @author eshinig
 *
 */
public class Boot {


	private static int noRows;
	private static int noColumns;
	public static Player player=null;
	public static GameScreenManager gameScreen = null;
	public Scanner keyboard = new Scanner(System.in);
	public static int[][] map  = null;
	/**
	 * 
	 */
	public Boot()
	{	
		int choice;

		System.out.println("Enter '1' for New Game or '2' to load Saved game");
		choice=keyboard.nextInt();

		switch (choice) {
		case 1:
		{
			this.newGame();
			break;
		}
		case 2:
		{
			this.loadGame();
			break;
		}
		}

		beginSession(noRows,noColumns);

		TileGrid grid=new TileGrid(map,noRows, noColumns);//draws the green tiles
		gameScreen = new GameScreenManager(grid);
		player=new Player(grid);
		

		while(!Display.isCloseRequested()){
			grid.draw();
			player.setTile();
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	/**
	 * This method accepts no of Rows and Columns from the user.
	 * At the moment Rows and Columns must be equal. 
	 * @return 0 if rows and columns are same otherwise 1.
	 */
	public int newGame()
	{	
		System.out.println("Enter Number of Rows & Columns for the MAP(max. 20x20)=");
		noRows=keyboard.nextInt();
		noColumns=keyboard.nextInt();
		map  = new int[noRows][noColumns];

		if(noRows==noColumns)
			return 0;

		else 
			return 1;
	}
	/**
	 * 
	 */
	public void loadGame()
	{	
		int mapToLoad;
		System.out.println("Following Maps saved");
		FileExplorer fileExplorer = new FileExplorer();
		fileExplorer.displayXMLFiles();
		GameScreenManager temp = new GameScreenManager();
		System.out.println("Enter the File number of the Map to Load:");
		mapToLoad=keyboard.nextInt();
		map=temp.loadMap(fileExplorer.getFileName(mapToLoad));
		noRows= temp.getNoRows();
		noColumns=temp.getNoColumns();
	}

	/**
	 * 
	 */
	public static int getNoRows() {
		return noRows;
	}

	/**
	 * 
	 * @return
	 */
	public static int getNoColumns() {
		return noColumns;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		new Boot();
	}

	/**
	 * 
	 * @return
	 */
	public  GameScreenManager getGameScreen() {
		return gameScreen;
	}

}
