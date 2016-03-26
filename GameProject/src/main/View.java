package main;

import static graphics.Designer.*;
import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

import junit_test.TestTower;
import map.GameScreenManager;
import map.Tile;
import map.TileGrid;
import map.TileType;


//import org.hamcrest.CoreMatchers;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import ai.Path;
import critter.Critter;
import critter.Critter_A;
import main.GameStateManager.GameState;
import utility.Clock;
import utility.CoordinateConverter;
import utility.FileExplorer;
import utility.Wave;
import tower.*;

/**
 * This is main the class which launches the game and displays the screen to the user.
 * This class is a view in MVC architecture. This class pulls the map from the model after each iteration
 * @author eshinig
 *
 */
public class View
{
	private static int noRows;
	private static int noColumns;
	public static GameScreenManager gameScreen = null;
	public Scanner keyboard = new Scanner(System.in);
	public static int[][] map  = null;
	public static TileGrid grid;
	public static Critter critter = null;
	
	/**
	 * This constructor initializes openGL library , accepts user input to either start 
	 * a new game or load the saved game
	 * 
	 */
	public View()
	{	
		int choice;
		GameStateManager gameState = new GameStateManager();
		System.out.println("Enter '1' for New Game or '2' to load Saved game");
		choice = keyboard.nextInt();
		switch (choice)
		{
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

		TrueTypeFont font;
		boolean antiAlias = false;
		Font awtFont = new Font("Times New Roman", Font.BOLD, 16);

		font = new TrueTypeFont(awtFont, antiAlias);

		/*
		 * Observer for tower to start shooting when critter arrive
		 */
		TowerNotification testTowerNotif = new TowerNotification();

		/*
		 * Create singleton for grid
		 */
		grid = new TileGrid(map,noRows, noColumns);//draws the green tiles
		gameScreen = new GameScreenManager(grid);

		//Add obervers to player
	//	Controller.getInstance().addObserver(testTowerNotif);

		//TowerCannon tower = new TowerCannon(quickTexture("CannonBase"), grid.getTile(14, 7));
		grid.draw();
		
		Wave wave = new Wave(15,"Critter_A");
		System.out.println("You have $" + Controller.getInstance().money);
		
		wave.addObserver(testTowerNotif);
		
		//demo Tower TODO @Rashpal remove it?
		TowerCannon demoTower = new TowerCannon(quickTexture("cannonBase"), new Tile(1 * 32, 1 * 32, 32, 32, TileType.TowerCannon));
		
		// Game starts in EDIT mode
		GameStateManager.setGameState("EDIT");
		
		while(!Display.isCloseRequested())
		{
			//Draws the grid with current assignment of Grid
			grid.draw();
			if(GameStateManager.getGameState() == GameState.PLAY)
			{
			    //Critter wave
				Clock.update();
				wave.update();
				
				if(TowerNotification.towerShoot == true)
				{   //bullet wave
					
				}
			}
			
			//Captures the user input and sets the tile
			if(GameStateManager.getGameState()!=GameState.END)
			{
				Controller.getInstance().setTile();
			}
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
		System.out.println("Enter Number of Rows & Columns for the MAP(max. 20x20)= ");
		noRows = keyboard.nextInt();
		noColumns = keyboard.nextInt();
		map  = new int[noRows][noColumns];

		if(noRows == noColumns)
			return 0;
		else 
			return 1;
	}
	
	/**
	 * This method loads the map from the saved XML file
	 */
	public void loadGame()
	{	
		int mapToLoad;
		System.out.println("Following Maps saved");
		FileExplorer fileExplorer = new FileExplorer();
		fileExplorer.displayXMLFiles();
		GameScreenManager temp = new GameScreenManager();
		System.out.println("Enter the File number of the Map to Load:");
		mapToLoad = keyboard.nextInt();
		map = temp.loadMap(fileExplorer.getFileName(mapToLoad));
		noRows = temp.getNoRows();
		noColumns = temp.getNoColumns();
	}

	/**
	 * This method returns number of Rows in the screen
	 * @return int number of Rows
	 */
	public static int getNoRows()
	{
		return noRows;
	}

	/**
	 * This method returns number of Columns in the screen
	 * @return int number of Cplumns
	 */
	public static int getNoColumns()
	{
		return noColumns;
	} 
	
	/**
	 * Set number of rows for JUNIt test
	 * @param noRows
	 */
	public static void setNoRows(int noRows) 
	{
		View.noRows = noRows;
	}

	/**
	 * Set number of columns for JUNIt test
	 * @param noColumns
	 */
	public static void setNoColumns(int noColumns)
	{
		View.noColumns = noColumns;
	}

	/**
	 * Main program and starts by call the constructor
	 * @param args
	 */
	public static void main(String args[])
	{
		new View();
	}

	/**
	 * This method returns the static object of Class GameScreenManager
	 * @return Returns the game object
	 */
	public  GameScreenManager getGameScreen()
	{
		return gameScreen;
	}
}
