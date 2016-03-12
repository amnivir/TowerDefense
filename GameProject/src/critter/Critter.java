/**
 * 
 */
package critter;

import map.Tile;

import org.newdawn.slick.opengl.Texture;

import utility.Clock;

/**
 * This class is the base for critter
 * 
 *
 */
public abstract class Critter {
	protected int width, height,health;
	protected float speed,x, y;
	protected Texture tex;
	protected Tile startTile;
	protected boolean first = true;
	public abstract void draw();
	public final void update() {
		if(first)
			first =false;
		else

			x += Clock.delta() * speed; 
	}
}
