package tower;

import static graphics.Designer.quickTexture;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import main.Player;
import map.Tile;
import utility.Clock;
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
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public Tile getStartTile() {
		return startTile;
	}
	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
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
		
		
	private void shoot()
	{
		lastShootTime = 0;
		shootTiles.add(new ShootTile(quickTexture("bullet"), x+32, y, 5, this.damage));	
	}
	
	public void preaperShoot() 
	{
		lastShootTime+= Clock.delta();
		if(lastShootTime > speedOfFire)
		{
			shoot();			
		}
	
		for(ShootTile s: shootTiles )
		{
			s.update();
			s.draw();
		}
		
	}
	
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
