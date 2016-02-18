package tower;

import org.newdawn.slick.opengl.Texture;

import map.Tile;
/**
 * This is abstract Tower class 
 */
public abstract class Tower {
	protected float x,y,width,height;
	protected int damage;
	protected Tile startTile;
	protected Texture texture;
	protected int price;
	/**
	 * Draws the tower on the map
	 */
	public abstract void draw();
	/**
	 * Abstract method that buys the tower
	 * @return
	 */
	public abstract boolean buy();

}
