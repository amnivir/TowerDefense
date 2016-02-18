package tower;

import main.Player;
import map.Tile;
import static graphics.Designer.*;

import org.newdawn.slick.opengl.Texture;

/**
 * This class is a sub class of Tower with more specific characteristic
 * @author Rashpal
 *
 */
public class TowerCannon extends Tower {

	/**
	 * This constructor initializes the TowerCannon object.
	 * @param texture Shape or kind of Tower
	 * @param startTile Location of the tile in the grid
	 */
	public TowerCannon(Texture texture, Tile startTile) {
		super();
		this.x=startTile.getX();
		this.y=startTile.getY();
		this.width=startTile.getWidth();
		this.height=startTile.getHeight();
		this.damage=10;
		this.texture=texture;
		this.price=50;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Draws the Tower on the map
	 */
	@Override
	public void draw() {
		drawQuadTex(texture, x, y, width, height);
		
	}
	
	/**
	 * Buys the tower 
	 */
	public boolean buy() {
		if(Player.money >=price)
		{
			Player.money=Player.money-price;
			return true;
		}
		else
			return false;		
	}

	
}
