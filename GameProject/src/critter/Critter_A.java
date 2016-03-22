package critter;

import static graphics.Designer.drawQuadTex;
import map.Tile;
import graphics.*;
import main.View;
import org.newdawn.slick.opengl.Texture;
import utility.*;

/**
 * This class is the concrete class for the critter type A
 *
 */
public class Critter_A extends Critter
{
	public Critter_A(Texture tex, Tile StartTile, int width,int height,float speed, int health)
	{
		super();
		this.tex=tex;
		this.x=StartTile.getX();
		this.y=StartTile.getY();
		this.width=width;
		this.height=height;
		this.speed=speed;
		this.startTile=StartTile;
		this.health=health;
	
	}
	
	/**
	 * Draws the critter
	 */
	@Override
	public void draw() 
	{
		drawQuadTex(tex, x, y, width, height);
	}
	

}
