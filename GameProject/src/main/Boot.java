package main;

import java.util.Scanner;

import map.TileGrid;

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
		Scanner keyboard = new Scanner(System.in);		
		System.out.println("Enter Number of Rows & Columns for MAP=");
		noRows=keyboard.nextInt();
		noColumns=keyboard.nextInt();

		beginSession();
		TileGrid grid=new TileGrid(noRows, noColumns);
		while(!Display.isCloseRequested()){

			grid.draw();
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
