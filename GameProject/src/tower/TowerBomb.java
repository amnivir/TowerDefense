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
public class TowerBomb extends Tower {

	/**
	 * This constructor initializes the TowerBomb object.
	 * @param texture Shape or kind of Tower
	 * @param startTile Location of the tile in the grid
	 */
	public TowerBomb(Texture texture, Tile startTile) {
		super();
		this.x=startTile.getX();
		this.y=startTile.getY();
		this.width=startTile.getWidth();
		this.height=startTile.getHeight();
		this.damage=20;
		this.range=20;
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

	@Override
	public boolean buy() {
		if(Player.money >=price)
		{
			Player.money=Player.money-price;
			return true;
		}
	else
		return false;	
	}
	
	/**
	 * Buys the tower 
	 */

	public void description() {

		System.out.println("-----Discription of Bomb Tower-----");
		System.out.println("Tower Pwoer " + damage);
		System.out.println("Tower Range " + range);
		System.out.println("Price of tower $" + price);
		System.out.println();
	}
	
	public void sell() {
		System.out.println("You sold the Bomb Tower");
		Player.money = Player.money + price;
		System.out.println("your current money $" + Player.money);
		System.out.println();

	}
	
	public void update() {
		if(Player.money >=10)
	{
		Player.money=Player.money-10;
		this.range=this.range+10;
		System.out.println("Bomb Tower's updated range:"+this.range);
		System.out.println("Your Current Money: &"+Player.money);
	}
	else{
			System.out.println("Sorry your money is less the price to update the tower");
			System.out.println("Your Current Money: &"+Player.money);	
		}	
	}

}