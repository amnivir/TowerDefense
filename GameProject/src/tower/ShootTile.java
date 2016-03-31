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
	private Critter targetTile;
	public String effects= "";
	private Tile startEndTile;
	private float xVelocity, yVelocity;
	private boolean alive;
	private Tile towerCordinates;
	private boolean isFreezeInProgress = false;
	private final Timer freezeTimer = new Timer();
	

	public ShootTile(Texture texture,float x, float y,float speed, int damage, Critter targetTile)
	{
		//super();
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.damage = damage;
		this.width = 32;
		this.height = 32;
		this.angle = 0;
		this.targetTile = targetTile;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		alive = true;
		towerCordinates = new Tile(x, y, width, height, TileType.Grass);
		if(targetTile != null)
			calculateDirectionTargetTile();
	}

	public ShootTile(Texture texture,float x, float y,float speed, int damage, Tile endTile)
	{
		//super();
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.damage = damage;
		this.width = 32;
		this.height = 32;
		this.angle = 0;
		this.startEndTile = endTile;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		alive = true;
		towerCordinates = new Tile(x, y, width, height, TileType.Grass);
		calculateDirectionStartEndTile();
	}

	/**
	 * Calculates the direction of the bullet
	 * TODO needs to return a specific tile
	 */
	private void calculateDirectionStartEndTile()
	{	
		float totalAllowedMovement = 1.0f;
		float xDistancefromTile = Math.abs(startEndTile.getX()- x);
		float yDistancefromTile = Math.abs(startEndTile.getY()- y);
		float totalDistanceFromTarget = xDistancefromTile + yDistancefromTile;
		float xPercentofMovement = xDistancefromTile / totalDistanceFromTarget;
		xVelocity = xPercentofMovement;
		yVelocity = totalAllowedMovement-xPercentofMovement;
		if(startEndTile.getX() < x)
		{
			xVelocity *= -1;
		}
		if(startEndTile.getY() < y)
		{
			yVelocity *= -1;
		}
	}
	
	/**
	 * Calculates the direction of the bullet
	 * TODO needs to return a specific tile
	 */
	private void calculateDirectionTargetTile()
	{	
		float totalAllowedMovement = 1.0f;
		float xDistancefromTile = Math.abs(targetTile.getX() - x)-1;
		float yDistancefromTile = Math.abs(targetTile.getY() - y)-1;
		float totalDistanceFromTarget = (xDistancefromTile + yDistancefromTile);
		float xPercentofMovement = xDistancefromTile / totalDistanceFromTarget;
		xVelocity = xPercentofMovement;
		yVelocity = totalAllowedMovement - xPercentofMovement;
		if(targetTile.getX()<x)
		{
			xVelocity *= -1;
		}
		if(targetTile.getY()<y)
		{
			yVelocity *= -1;
		}
	}
	
	public void update()
	{
		if(alive && Clock.delta() < 1 && Clock.delta() > 0)
		{
			x += Clock.delta() * speed * xVelocity;
			y += Clock.delta() * speed * yVelocity;

			if(Wave.getCritterList().size()!= 0)
			{
				ArrayList<Critter> critterTargets = getTargetCritterOnTargetTile();
				
				if (critterTargets.size() > 0)
				{
					
					Critter targetCritter = critterTargets.get(0);
					if(Designer.chechCollision(x, y, 32, 32, targetCritter.getX(), targetCritter.getY(), targetCritter.getWidth(), targetCritter.getHeight()))
					{
						alive = false;
		
						for(TowerCannon cannonTower : TileGrid.cannonList )
						{
								critterTargets.get(0).reduceHealth(cannonTower.damage);
								System.out.println( "health->" + critterTargets.get(0).getHealth());
								if(critterTargets.get(0).getHealth() <= 0)
								{
									System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
								}
						}
						
						for(TowerBomb bombTower : TileGrid.bombList )
						{
							for(int i = 0; i <= critterTargets.size(); i++)
							{
								Critter c = critterTargets.get(0);
								Critter	nextCritter = null;
								Critter	previousCritter = null;
								
								if(i > 0)
								{
									if(i+1 < critterTargets.size())
									{
										nextCritter = critterTargets.get(i+1);
										nextCritter.reduceHealth(bombTower.damage-10);
										System.out.println( "health->" + nextCritter.getHealth());
										if(nextCritter.getHealth() <= 0)
										{
											System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
										}
									}
									if(i-1 >= 0)
									{
										previousCritter = critterTargets.get(i-1);
										previousCritter.reduceHealth(bombTower.damage-10);
										System.out.println( "health->" + previousCritter.getHealth());
										if(previousCritter.getHealth() <= 0)
										{
										System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
										}
									}
									c.reduceHealth(bombTower.damage);
									System.out.println( "health->" + c.getHealth());
									if(c.getHealth() <= 0)
									{
										System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
									}
								}
								
								else
								{
									if(i+1 < critterTargets.size())
									{
										previousCritter = critterTargets.get(i+1);
										previousCritter.reduceHealth(bombTower.damage-10);
										System.out.println( "health->" + previousCritter.getHealth());
										if(previousCritter.getHealth() <= 0)
										{
											System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
										}
									}
									c.reduceHealth(bombTower.damage);
									System.out.println( "health->" + critterTargets.get(0).getHealth());
									if(critterTargets.get(0).getHealth() <= 0)
									{
										System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
									}
								}
							}
						}
						
						for(TowerFreez freezTower : TileGrid.freezList )
						{
							Wave.setFrozen(true);
							setFreezeTimer();
						}
					}
				}
				
				draw();
			}
		}
	}
	
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
	
	public void setFreezeTimer()
	{
		if(isFreezeInProgress)
		{
			freezeTimer.cancel();
		}
		
		isFreezeInProgress = true;
		System.out.println("Critter wave is frozen");
		
		freezeTimer.schedule(new TimerTask() {
	        @Override
	        public void run() {
	            Wave.setFrozen(false);
	            isFreezeInProgress = false;
	            System.out.println("critter wave is moving");
	        }
	    },1000);
	}
}
