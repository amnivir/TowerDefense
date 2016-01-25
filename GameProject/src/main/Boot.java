package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Designer.*;


public class Boot {

	public Boot()
	{
		beginSession();
		TileGrid grid=new TileGrid();
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
