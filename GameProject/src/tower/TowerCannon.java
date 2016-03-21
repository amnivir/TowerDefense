package tower;

import java.util.ArrayList;

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
	//	this.cannonTexture = quickTexture("cannonBase");
		this.width=startTile.getWidth();
		this.height=startTile.getHeight();
		this.damage=10;
		this.range=10;
		this.texture=texture;
		this.price=30;
		this.speedOfFire = 30; //speed of firing the bullets
		this.lastShootTime = 0; //the time for last shooted bullet
		this.shootTiles = new ArrayList<ShootTile>(); //list of bullets
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Draws the Tower on the map
	 */
	@Override
	public void draw() {
		drawQuadTex(texture, x, y, width, height);
		//drawQuadTexRot(cannonTexture, x, y, WIDTH, HEIGHT,45); //rotate the tower to shoot the enemies
		
	}
	
	/**
	 * Buys the tower 
	 */
	public boolean buy() {
		if(Controller.money >=price)
		{
			Controller.money=Controller.money-this.price;
			return true;
		}
		else
			return false;		
	}
	
	public void description()
	{ 
		System.out.println("-----Discription of Cannon Tower-----");
		System.out.println("Tower Power " + this.damage);
		System.out.println("Tower Range " + this.range);
		System.out.println("Price of tower $" + this.price);
		System.out.println();
	}

	public void sell() {
		System.out.println("You sold the Cannon Tower");
		Controller.money = Controller.money + this.price;
		System.out.println("your current money $"+Controller.money);
		System.out.println();
	}

	
	
	
	
//	public void prepareShoot()
//	{
//		lastShootTime+= Clock.delta();
//		if(lastShootTime > speedOfFire)
//			shoot();
//		
//		for(ShootTile s: shootTiles )
//			s.update();
//	}
	
	public void update() 
	{
		if(Controller.money >=10)
		{
			Controller.money=Controller.money-10;
			this.range=this.range+10;
			System.out.println("Cannon Tower's updated range: "+this.range);
			System.out.println("Your Current Money: &"+Controller.money);
		
		}
		else
		{
			System.out.println("Sorry your money is less the price to update the tower");
			System.out.println("Your Current Money: &"+Controller.money);	
		}
	}


	


	
}
