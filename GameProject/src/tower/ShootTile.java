package tower;

import static graphics.Designer.*;

import java.util.ArrayList;

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
	private Tile startEndTile;
	private float xVelocity, yVelocity;
	private boolean alive;
	private Tile towerCordinates;
	

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
				if(Designer.chechCollision(x, y, 32, 32, Wave.getCritterList().get(0).getX(), Wave.getCritterList().get(0).getY(), Wave.getCritterList().get(0).getWidth(), Wave.getCritterList().get(0).getHeight()))
				{
					alive=false;
					
					for(TowerCannon cannonTower : TileGrid.cannonList )
					{
						if(cannonTower.getX() == towerCordinates.getX() && cannonTower.getY() == towerCordinates.getY())
						{
							Wave.getCritterList().get(0).reduceHealth(cannonTower.damage);
							System.out.println( "health->" + Wave.getCritterList().get(0).getHealth());
							if(Wave.getCritterList().get(0).getHealth() <= 0)
							{
								System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
							}
						}
					}

					for(TowerBomb bombTower : TileGrid.bombList )
					{
						if(bombTower.getX() == towerCordinates.getX() && bombTower.getY() == towerCordinates.getY())
						{
							Wave.getCritterList().get(0).reduceHealth(bombTower.damage);
							System.out.println( "health->" + Wave.getCritterList().get(0).getHealth());
							if(Wave.getCritterList().get(0).getHealth() <= 0)
							{
								System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
							}
						}
					}
					
					for(TowerFreez freezTower : TileGrid.freezList )
					{
						if(freezTower.getX() == towerCordinates.getX() && freezTower.getY() == towerCordinates.getY())
						{
							Wave.getCritterList().get(0).reduceHealth(freezTower.damage);
							System.out.println( "health->" + Wave.getCritterList().get(0).getHealth());
							if(Wave.getCritterList().get(0).getHealth() <= 0)
							{
								System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
							}
						}
					}
				}
				draw();
			}
		}
	}
	
	public void update1()
	{
		if(alive && Clock.delta() < 1 && Clock.delta() > 0)
		{
			x += Clock.delta() * speed * xVelocity;
			y += Clock.delta() * speed * yVelocity;

			if(Wave.getCritterList().size()!= 0)
			{
				Critter critter = getTargetCritterOnTargetTile();
				
				if (critter != null)
				{
					if(Designer.chechCollision(x, y, 32, 32, critter.getX(), critter.getY(), critter.getWidth(), critter.getHeight()))
					{
						alive = false;
						
						critter.reduceHealth(this.damage);
						System.out.println( "health->" + critter.getHealth());
						if(critter.getHealth() <= 0)
						{
							System.out.println("Tower ->" + towerCordinates.getX() / 32 + " " + towerCordinates.getY() / 32 + " hits critter");
						}
					}
				}
				
				draw();
			}
		}
	}
	
	public Critter getTargetCritterOnTargetTile()
	{
		ArrayList<Critter> targetCritters = new ArrayList<Critter>();
		for (Critter critter: Wave.getCritterList())
		{
			if(Designer.chechCollision(critter.getX(), critter.getY(), critter.getWidth(), critter.getHeight(), startEndTile.getX(), startEndTile.getY(), startEndTile.getWidth(), startEndTile.getHeight()))
			{
				targetCritters.add(critter);
			}
		}
		
		if(targetCritters.size() == 0)
			return null;
		
		return targetCritters.get(0);
	}
}
