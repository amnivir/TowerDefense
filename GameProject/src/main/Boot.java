package main;

import java.util.Scanner;

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
	public Boot()
	{	
		//Scanner keyboard = new Scanner(System.in);		
		//System.out.println("Enter Number of Rows & Columns for MAP=");
		//noRows=keyboard.nextInt();
		//noColumns=keyboard.nextInt();
		
		noRows=10;
		noColumns=10;
		int[][]map={
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
				};

		beginSession();
		TileGrid grid=new TileGrid(map,noRows, noColumns);
		grid.setTile(0, 0, TileType.Water);  // setting a particular tile
		grid.setTile(0, 0, grid.getTile(1, 1).getType());// setting a tile correspond to given one 
		
		Player player=new Player(grid);
		
		while(!Display.isCloseRequested()){

			grid.draw();
			player.setTile();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	public static void main(String args[])
	{
		new Boot();
	}
	

}
