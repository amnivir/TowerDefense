package tower;

import static graphics.Designer.*;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.opengl.Texture;

import critter.Critter;
import graphics.Designer;
import map.Tile;
import map.TileGrid;
import map.TileType;
import utility.Clock;
import utility.Wave;


/**
 * This class is a sub class of Tower to shoot the critters
 * @author Gurpreet
 *
 */
public class ShootTile extends Tower
{
	//TODO Fix this multiple types
	private Critter shootingCritter;
	private Tile startEndTile;
	private float xVelocity, yVelocity;
	private boolean alive;
	private Tile towerCordinates;
	private boolean isFreezeInProgress = false;
	private final Timer freezeTimer = new Timer();
	ShootStrategyEnum shootingStrategy;
	EffectType effectType;

	public ShootTile(Texture texture,float x, float y,float speed, int damage, EffectType effect, ShootStrategyEnum strategy, Critter shootingCritter)
	{
		//super();
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.damage = damage;
		this.effectType = effect;
		this.shootingStrategy = strategy;
		this.width = 32;
		this.height = 32;
		this.angle = 0;
		this.shootingCritter = shootingCritter;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		alive = true;
		towerCordinates = new Tile(x, y, width, height, TileType.Grass);
		if(shootingCritter != null)
			calculateDirectionTargetTile();
	}


	
	
	/**
	 * Calculates the direction of the bullet
	 * TODO needs to return a specific tile
	 */
	private void calculateDirectionTargetTile()
	{	
		float totalAllowedMovement = 1.0f;
		float xDistancefromTile = Math.abs(shootingCritter.getX() - x)-1;
		float yDistancefromTile = Math.abs(shootingCritter.getY() - y)-1;
		float totalDistanceFromTarget = (xDistancefromTile + yDistancefromTile);
		float xPercentofMovement = xDistancefromTile / totalDistanceFromTarget;
		xVelocity = xPercentofMovement;
		yVelocity = totalAllowedMovement - xPercentofMovement;
		if(shootingCritter.getX()<x)
		{
			xVelocity *= -1;
		}
		if(shootingCritter.getY()<y)
		{
			yVelocity *= -1;
		}
	}
	/**
	 * THis method update the x and y coordinates of the bullet
	 */
	public void update()
	{
		if(alive && Clock.delta() < 1 && Clock.delta() > 0)
		{
			x += Clock.delta() * speed * xVelocity;
			y += Clock.delta() * speed * yVelocity;

			if(Wave.getCritterList().size()!= 0)
			{
				// select Target critters by strategy
				ArrayList<Critter> critterTargets = null;
				if(shootingStrategy == ShootStrategyEnum.closestCritter)
				{
					critterTargets = new ArrayList<Critter>();
					critterTargets.add(shootingCritter);
				}
				else if(shootingStrategy == ShootStrategyEnum.weakestCritter || shootingStrategy == ShootStrategyEnum.strongestCritter || shootingStrategy == ShootStrategyEnum.nearToEndCritter)
				{
					critterTargets = new ArrayList<Critter>();
					critterTargets.add(shootingCritter);
				}
				
				// apply desired effect on targeted critters
				if (critterTargets.size() > 0)
				{
					
					Critter targetCritter = critterTargets.get(0);
					if(Designer.chechCollision(x, y, 32, 32, targetCritter.getX(), targetCritter.getY(), targetCritter.getWidth(), targetCritter.getHeight()))
					{
						alive = false;
						
						if(effectType == EffectType.cannon )
						{
								critterTargets.get(0).burn(this.damage);
								System.out.println( "health->" + critterTargets.get(0).getHealth());
								if(critterTargets.get(0).getHealth() <= 0)
								{
									System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
								}
						}
						
						else if(effectType == EffectType.bomb)
						{
							Critter middleCritter = critterTargets.get(0);
							Critter	nextCritter = null;
							Critter	previousCritter = null;
							int indexOfMiddleCritter = Wave.getCritterList().indexOf(middleCritter);
							
							if(indexOfMiddleCritter > 0)
								previousCritter = Wave.getCritterList().get(indexOfMiddleCritter - 1);
							
							if(indexOfMiddleCritter < Wave.getCritterList().size()-1)
								nextCritter = Wave.getCritterList().get(indexOfMiddleCritter + 1);
						
							// effect middle critter
							middleCritter.reduceHealth(damage);
							System.out.println( "health->" + middleCritter.getHealth());
							if(middleCritter.getHealth() <= 0)
							{
								System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
							}
							
							// effect previous critter if any
							if(previousCritter != null)
							{
								previousCritter.reduceHealth(damage-5);
								System.out.println( "health->" + previousCritter.getHealth());
								if(previousCritter.getHealth() <= 0)
								{
									System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
								}
							}
							
							// effect next critter if any
							if(nextCritter != null)
							{
								nextCritter.reduceHealth(damage-5);
								System.out.println( "health->" + nextCritter.getHealth());
								if(nextCritter.getHealth() <= 0)
								{
									System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
								}
							}
						}
						
						else if(effectType == EffectType.freeze)
						{
							
							setFreezeTimer(critterTargets.get(0));
							System.out.println( "health->" + critterTargets.get(0).getHealth());
						}
					}
				}
				
				draw();
			}
		}
	}
	/**
	 * THis method returns the array list of the critter
	 */
	public ArrayList<Critter> getTargetCritterOnTargetTile()
	{
		ArrayList<Critter> targetCritters = new ArrayList<Critter>();
		for (Critter critter: Wave.getCritterList())
		{
			if(Designer.chechCollision(critter.getX(), critter.getY(), critter.getWidth(), critter.getHeight(), startEndTile.getX(), startEndTile.getY(), startEndTile.getWidth(), startEndTile.getHeight()))
			{
				targetCritters.add(critter);
			}
		}
		
		return targetCritters;
	}
	/**
	 * THis method sets the freez timer for the critter
	 */
	public void setFreezeTimer(Critter critter)
	{
		critter.setFreezeTimer(this.damage);
	}
}
