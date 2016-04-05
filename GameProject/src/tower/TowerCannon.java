package tower;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import main.Controller;
import map.Tile;
import utility.Clock;
import static graphics.Designer.*;

import org.newdawn.slick.opengl.Texture;

/**
 * This class is a sub class of Tower with more specific characteristic
 * @author Rashpal
 *
 */

public class TowerCannon extends Tower 
{
	/**
	 * This constructor initializes the TowerCannon object.
	 * @param texture Shape or kind of Tower
	 * @param startTile Location of the tile in the grid
	 */
	public TowerCannon(Texture texture, Tile startTile)
	{
		super();
		this.name="Tower Cannon";
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.damage = 10;
		this.range = 25000;
		this.texture = texture;
		this.price = 30;
		this.speedOfFire = 30; 			//speed of firing the bullets
		this.lastShootTime = 0;			 //the time for last shooted bullet
		this.shootTiles = new ArrayList<ShootTile>(); //list of bullets
	}
	
	public TowerCannon()
	{

	
	}
}
