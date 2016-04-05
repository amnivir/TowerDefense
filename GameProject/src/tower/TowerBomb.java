package tower;

import main.Controller;
import map.Tile;
import static graphics.Designer.*;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.newdawn.slick.opengl.Texture;
/**
 * This class is a sub class of Tower with more specific characteristic
 * @author Rashpal
 *
 */
@XmlRootElement(name="TowerBonb")
public class TowerBomb extends Tower 
{
	/**
	 * This constructor initializes the TowerBomb object.
	 * @param texture Shape or kind of Tower
	 * @param startTile Location of the tile in the grid
	 */
	public TowerBomb(Texture texture, Tile startTile) 
	{
		super();
		this.name="Tower Bomb";
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.damage = 20;
		this.range = 15000;
		this.texture = texture;
		this.price = 50;
		this.speedOfFire = 30; 			//speed of firing the bullets
		this.lastShootTime = 0; 		//the time for last shooted bullet
		this.shootTiles = new ArrayList<ShootTile>(); 	//list of bullets
	}
	public TowerBomb()
	{

	
	}
}