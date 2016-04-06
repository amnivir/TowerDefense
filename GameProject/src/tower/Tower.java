package tower;

import static graphics.Designer.HEIGHT;
import static graphics.Designer.drawQuadTex;
import static graphics.Designer.quickTexture;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import ai.Path;
import ai.Strategy;
import critter.Critter;
import main.View;
import main.Controller;
import map.Tile;
import map.TileGrid;
import map.TileType;
import utility.Clock;
import utility.CoordinateConverter;
import utility.Wave;
/**
 * This is abstract Tower class 
 */
@XmlTransient
@XmlSeeAlso({TowerCannon.class,TowerBomb.class,TowerFreez.class})
public abstract class Tower 
{	
	@XmlElement
	protected float x;
    @XmlElement
	protected float y;
    @XmlTransient
    protected float width;
    @XmlTransient
    protected float height;
    @XmlTransient
    protected float speed;
    @XmlTransient
    protected float lastShootTime;
    @XmlTransient
    protected float speedOfFire;
    @XmlTransient
	protected int damage;
    @XmlTransient
	protected int range;
    @XmlTransient
	protected Tile startTile;
    @XmlTransient
	protected Texture texture, cannonTexture;
    @XmlTransient
    protected int price;
    @XmlTransient
	protected static ArrayList<ShootTile> shootTiles;
    @XmlTransient
    protected float angle;
    @XmlTransient
	public ShootStrategyEnum strategyTile = ShootStrategyEnum.closestCritter;
    @XmlTransient
	String name;
	
	/**
	 * Draws the tower on the map
	 */

	
	public void draw()
		{
			drawQuadTex(this.texture, this.x, this.y, this.width, this.height);
		}
	
	
	
	/**
	 * Method that sells the tower
	 * @return
	 */

	public void sell() 
	{
		System.out.println("You sold the "+this.name);
		Controller.money = Controller.money + this.price;
		System.out.println("your current money $" + Controller.money);
		System.out.println();
	}
	
	/**
	 * Method that shows description of the tower
	 * @return
	 */
	public void description() 
	{
		System.out.println("-----Discription of "+this.name+"-----");
		System.out.println("Tower's damage power " + this.damage);
		System.out.println("Tower range " + this.range);
		System.out.println("Price of tower $" + this.price);
		System.out.println();
	}
	
	/**
	 * Method that buys the tower
	 * @return
	 */
	
	public boolean buy() 
	{
		if(Controller.money >= price)
		{
			Controller.money = Controller.money - this.price;
			return true;
		}
		else
		{
			return false;	
		}
	}
	
	/**
	 * Method that increases the range the tower
	 * @return
	 */
	public void upgrade()
	{
		if(Controller.money >= 10)
		{
			Controller.money = Controller.money - 10;
			this.damage += 10;
			System.out.println(this.name+"'s updated damage power:" + this.damage);
			System.out.println("Your Current Money: &" + Controller.money);
		}
		else
		{
			System.out.println("Sorry your money is less the price to update the tower");
			System.out.println("Your Current Money: &" + Controller.money);	
		}	
	}

	

	/**
	 * Method that starts shooting of there are critters in map
	 * @return
	 */
	private void shoot()
	{
		lastShootTime = 0;
		if(Wave.getCritterList().size() != 0)
		{			
			EffectType effectType = getTowerEffectType();
			
			// shoot closest critter 
			if(this.strategyTile == ShootStrategyEnum.closestCritter) 
			{
				Critter closestCritter = getClosestCritter();
				if(closestCritter != null)
				{
//					System.out.println("Calling closest....");
					Context context = new Context(new StrategyShootClosestCritter());
					context.executeStrategy(x,y,30,this.damage,effectType,strategyTile,closestCritter);
				}
			}
			
			// shoot weakest critter
			else if(this.strategyTile == ShootStrategyEnum.weakestCritter)
			{
				Critter weakestCritter = getWeakestCritter();
				if(weakestCritter != null)
				{
//					System.out.println("Calling weakest....");
					Context context = new Context(new StrategyShootWeakestCritter());
					context.executeStrategy(x,y,30,this.damage,effectType,strategyTile,weakestCritter);
				}
			}
			
			// shoot strongest critter
			else if(this.strategyTile == ShootStrategyEnum.strongestCritter)
			{
				Critter strongestCritter = getStrongestCritter();
				if(strongestCritter != null) 
				{
//					System.out.println("Calling strongest....");
					Context context = new Context(new StrategyShootStrongestCritter());
					context.executeStrategy(x,y,30,this.damage,effectType,strategyTile,strongestCritter);
				}
			}	
			
			// shoot critter near to the end
			else if(this.strategyTile == ShootStrategyEnum.nearToEndCritter)
			{
				
				Critter nearToEndCritter = getNearToEndCritter();
				if(nearToEndCritter != null)
				{
//					System.out.println("Calling nearest....");
					Context context = new Context(new StrategyShootNearestEndPointCritter());
					context.executeStrategy(x,y,30,this.damage,effectType,strategyTile,nearToEndCritter);
				}
			}
		}
		else
		{
			TowerNotification.towerShoot = false;
		}
		//TODO FIX why create Shoot tile if critters not left
	}
	
	/**
	 * This method finds and returns the critter near to end of the path.
	 * @return Critter: the critter near to the end.
	*/
	private Critter getNearToEndCritter()
	{
		Critter nearToEndCritter = null;
		if(Wave.getCritterList().size() > 0)
		{
			nearToEndCritter = Wave.getCritterList().get(0);
			if(!isCritterInRange(nearToEndCritter))
				return null;
		}		
		return nearToEndCritter;
	}
	
	/**
	 * This method finds and returns the closest critter to the tower in the critter wave.
	 * @return Critter: the closest critter to the tower.
	*/
	private Critter getClosestCritter()
	{
		float closestDistance = 400000; //some large distance
		Critter closestCritter = null;
		for(Critter critter: Wave.getCritterList())
		{
			float distance = (float)(Math.sqrt((this.getX() - critter.getX())*(this.getX() - critter.getX()) + (this.getY() - critter.getY())*(this.getY() - critter.getY())))/32;
			if(distance < closestDistance)
			{
				closestDistance = distance;
				closestCritter = critter;
			}
		}
		// return critter only if in tower range.
		if (closestDistance > this.range)
			return null;
		
		return closestCritter;
	}
	
	/**
	 * This method finds and returns the first weakest critter in the tower range if exists.
	 * @return Critter: the first weakest critter in the tower range, else returns null.
	*/
	private Critter getWeakestCritter()
	{
		int minHealth = 1000;
		Critter minHealthCritter = null;
		
		for(Critter critter : Wave.getCritterList())
		{
			if(isCritterInRange(critter))
			{
				int critterHealth = critter.getHealth();
				if(critterHealth < minHealth)
				{
					minHealth = critterHealth;
					minHealthCritter = critter;
				}
			}
		}
		return minHealthCritter;	
	}
	
	/**
	 * This method finds and returns the first strongest critter in the tower range if exists.
	 * @return Critter: the first strongest critter in the tower range, else returns null.
	*/
	private Critter getStrongestCritter()
	{
		int maxHealth = 0;
		Critter maxHealthCritter = null;
		for(Critter critter : Wave.getCritterList())
		{
			if(isCritterInRange(critter))
			{
				int critterHealth = critter.getHealth();
				if(critterHealth > maxHealth)
				{
					maxHealth = critterHealth;
					maxHealthCritter = critter;
				}
			}
		}
		return maxHealthCritter;	
	}
	
	/**
	 * checks if the critter is in tower range
	 * @param critter
	 * @return true if critter is in range else returns false
	 */
	private boolean isCritterInRange(Critter critter)
	{
		float distance = (float)(Math.sqrt((this.getX() - critter.getX())*(this.getX() - critter.getX()) + (this.getY() - critter.getY())*(this.getY() - critter.getY())))/32;
		if (distance < this.range)
			return true;
		else
			return false;
	}
	
	/**
	 * Method that calculate the time before shoot
	 * @return
	 */

	public void preaperShoot() 
	{	
		if(Clock.delta() < 1 && Clock.delta() > 0)
		{
			lastShootTime += Clock.delta();
		}
		if(lastShootTime > speedOfFire)
		{ 
			shoot();			
		}

		for(ShootTile s : shootTiles )
		{
			s.update();
		}
	}
	
	public EffectType getTowerEffectType()
	{
		EffectType effect = null;
		
		if(this instanceof TowerCannon)
		{
			effect = EffectType.cannon;
		}
		else if(this instanceof TowerBomb)
		{
			effect = EffectType.bomb;
		}
		else if(this instanceof TowerFreez)
		{
			effect = EffectType.freeze;
		}
		return effect;
	}
	/**
	 * below are setters and getters for members of tower
	 * @return
	 */
	
	@XmlTransient
	public float getX()
	{
		return x;
	}
	
	public void setX(float x) 
	{
		this.x = x;
	}
	
	@XmlTransient
	public float getY() 
	{
		return y;
	}
	
	public void setY(float y) 
	{
		this.y = y;
	}
	@XmlTransient
	public float getWidth()
	{
		return width;
	}
	
	public void setWidth(float width)
	{
		this.width = width;
	}
	
	@XmlTransient
	public float getHeight()
	{
		return height;
	}
	
	public void setHeight(float height) 
	{
		this.height = height;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setDamage(int damage) 
	{
		this.damage = damage;
	}
	
	public int getRange()
	{
		return range;
	}
	
	public void setRange(int range)
	{
		this.range = range;
	}
	
	@XmlTransient
	public Tile getStartTile() 
	{
		return startTile;
	}
	
	public void setStartTile(Tile startTile) 
	{
		this.startTile = startTile;
	}
	
	@XmlTransient
	public int getPrice() 
	{
		return price;
	}
	
	public void setPrice(int price) 
	{
		this.price = price;
	}
	
	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
	
	@XmlTransient
	public Texture getTexture() 
	{
		return null;
	}



	public static void setStrategy(String tower, int x, int y,ShootStrategyEnum strategy) 
	{
		int blockSize=32;
		if(tower.equals("cannon tower"))
        {
            for ( TowerCannon temp : TileGrid.cannonList) 
            {	
                if(x == (temp.getX() / blockSize) && y == (temp.getY() / blockSize))
                {
                	temp.strategyTile=strategy;
                	System.out.println("Shooting strategy changed: "+strategy+" of "+temp.name+" at "+temp.getX()/32+" "+temp.getY()/32);
                    break;
                }
            }
        }

        else if(tower.equals("bomb tower"))
        {
            for ( TowerBomb temp : TileGrid.bombList) 
            {	
                if(x == (temp.getX() / blockSize) && y == (temp.getY() / blockSize))
                {
                	temp.strategyTile=strategy;
                	System.out.println("Shooting strategy changed: "+strategy+" of "+temp.name+" at "+temp.getX()/32+" "+temp.getY()/32);
                    break;
                }
            }
        }
        else if(tower.equals("freez tower"))
        {
            for ( TowerFreez temp : TileGrid.freezList) 
            {	
            	temp.strategyTile=strategy;
            	System.out.println("Shooting strategy changed: "+strategy+" of "+temp.name+" at "+temp.getX()/32+" "+temp.getY()/32);
                break;
            }
        }
	}
}
