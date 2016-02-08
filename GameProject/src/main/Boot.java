package main;

import graphics.DesignerButtons;

import java.util.Scanner;

import map.GameScreenManager;
import map.TileGrid;
import map.TileType;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static graphics.Designer.*;


public class Boot {


	private static int noRows;
	private static int noColumns;
	public static Player player=null;
	public static GameScreenManager gameScreen = null;

	public Boot()
	{	
		Scanner keyboard = new Scanner(System.in);		
		System.out.println("Enter Number of Rows & Columns for the MAP(max. 20x20)=");
		noRows=keyboard.nextInt();
		noColumns=keyboard.nextInt();
		
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
		int[][] map  = new int[noRows][noColumns];
		
		beginSession(noRows,noColumns);
		TileGrid grid=new TileGrid(map,noRows, noColumns);//draws the green tiles
		gameScreen = new GameScreenManager(grid);
		//grid.setTile(0, 0, TileType.Water);  // setting a particular tile
		
		player=new Player(grid);
		
		DesignerButtons designerButtons = new DesignerButtons(WIDTH, HEIGHT);
		while(!Display.isCloseRequested()){
			
			grid.draw();
			designerButtons.draw();
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
	public static void main(String args[])
	{
		new Boot();
	}
	
	
	public static GameScreenManager getGameScreen() {
		return gameScreen;
	}

}
