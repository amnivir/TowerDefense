package graphics;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;

import java.io.IOException;
import java.io.InputStream;

import main.Boot;
import main.Player;
import map.TileGrid;
import map.TileType;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * This class access and  initializes openGL library to  creates the Display screen 
 * of size noRows*noColumns*32(pixels).
 * @author Rashpal
 *
 */
public class Designer {
	
	public static int WIDTH, HEIGHT;
	static int  blockSize = 32;
	private static int noOfBlocksOnRight=5;
	
	/**
	 * This method sets openGL Parameters for the Display screen and printing the text
	 */
	public static void beginSession(int numRows,int numCols){
		HEIGHT= numRows*blockSize;
		//Reserves some space on right
		WIDTH= (numCols+noOfBlocksOnRight)*blockSize;
		try {
			Display.setTitle("Tower Defence Game");
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	    GL11.glDisable(GL11.GL_LIGHTING);  
	    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(0);
        GL11.glViewport(0,0,WIDTH,HEIGHT);
		}
	
/**
 * This method draws quad for a specific texture. The texture refers to a tile
 * @param tex TYexture of the tile
 * @param x X Coordinate of the tile (quad)
 * @param y Y Coordinate of the tile (quad)
 * @param width location of the tile in the map along x axis
 * @param height location of the tile in the map along y axis
 */
	public static void drawQuadTex(Texture tex, float x, float y, float width, float height){

		tex.bind();
		glTranslatef(x,y,0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
		
	}
	
	/**
	 * This method loads the texture of tile
	 * @param path directory path of png file of the tile 
	 * @param fileType format of the tile
	 * @return
	 */
	public static Texture loadTexture(String path, String fileType)
	{
		Texture tex=null;
		InputStream in= ResourceLoader.getResourceAsStream(path);
		try {
			tex=TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
	
	/**
	 * This method is a helper. This method adds the package and the type of the file
	 * @param name Name of the tile
	 * @return texture of the tile 
	 */
	public static Texture quickTexture(String name)
	{
		Texture tex=null;
		tex=loadTexture("res/"+name+".png","PNG");
		
		return tex;
	}
	
}
