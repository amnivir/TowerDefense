package tower;

import main.Controller;
import map.Tile;
import static graphics.Designer.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

/**
 * This class is a sub class of Tower with more specific characteristic
 * @author Rashpal
 *
 */
public class TowerFreez extends Tower 
{

	/**
	 * This constructor initializes the TowerFreez object.
	 * @param texture Shape or kind of Tower
	 * @param startTile Location of the tile in the grid
	 */
	public TowerFreez(Texture texture, Tile startTile)
	{
		super();
		this.name="Cannon Freez";
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.damage = 30;
		this.range = 10000;
		this.texture = texture;
		this.price = 70;
		this.speedOfFire = 30; 			//speed of firing the bullets
		this.lastShootTime = 0; 		//the time for last shooted bullet
		this.shootTiles = new ArrayList<ShootTile>(); //list of bullets
	}
}
