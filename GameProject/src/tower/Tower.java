package tower;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import main.Player;
import map.Tile;
/**
 * This is abstract Tower class 
 */
public abstract class Tower {
	protected float x,y,width,height,speed,lastShootTime,speedOfFire;
	protected int damage;
	protected int range;
	protected Tile startTile;
	protected Texture texture, cannonTexture;
	protected int price;
	protected ArrayList<ShootTile> shootTiles;
	
	/**
	 * Draws the tower on the map
	 */
	public abstract void draw();
	/**
	 * Abstract method that buys the tower
	 * @return
	 */
	public abstract boolean buy();
	/**
	 * Abstract method that shows description of the tower
	 * @return
	 */
	public abstract void description();
	
	/**
	 * Abstract method that increases the range the tower
	 * @return
	 */
	public abstract void update();
	/**
	 * Abstract method that sells the tower
	 * @return
	 */
	
	public abstract void sell();
	

}
