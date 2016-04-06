/**
 * 
 */
package critter;

import static graphics.Designer.quickTexture;
import map.Tile;

import java.util.Observable;
import java.util.TimerTask;

import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import ai.Path;
import main.View;
import main.Controller;
import utility.Clock;
import utility.CoordinateConverter;
import utility.Wave;
/**
 * This class is the base for critter
 * 
 *
 */
public abstract class Critter
{
	protected int width, height,health;
	protected float speed,x, y;
	protected Texture tex;
	public Tile startTile;
	protected Tile nextTile;
	protected Tile endTile;
	public int pathStepIndex=0;
	protected boolean first = true;
	public boolean isCriterAlive;
	public boolean isStopped = false;
	int stopCounter = 0;
	int burnCounter = 0;
	
	/**
	 * THis constructor initializes the next tile and last tile to the critter
	 */
	public Critter()
	{
		this.nextTile = View.grid.getTile(CoordinateConverter.getYCordinate(Path.continousPath.get(pathStepIndex+1)), 
				CoordinateConverter.getXCordinate(Path.continousPath.get(pathStepIndex+1)));
		this.endTile = View.grid.getTile(CoordinateConverter.getYCordinate(Path.continousPath.get(Path.continousPath.size()-1)), 
				CoordinateConverter.getXCordinate(Path.continousPath.get(Path.continousPath.size()-1)));
		this.isCriterAlive=true;
	}
	
	public void reduceHealth(int damage)
	{
		this.health-=damage;
		if(health<=0)
		{
			this.isCriterAlive=false;
			this.health = 0;
			Controller.money+=10;
			System.out.println("$10 added->"+Controller.money);
		}
	}

	public float getSpeed()
	{
		return speed;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
	
	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width) 
	{
		this.width = width;
	}

	public int getHealth() 
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public float getX() 
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY() 
	{
		return y;
	}

	public void setY(float y) 
	{
		this.y = y;
	}
	
	public Tile getStartTile()
	{
		return startTile;
	}

	public abstract void draw();

	public final void update() 
	{
		boolean critterMovedOneTile=false;
		
		//this first is set to false to avoid reading large Clock.delta
		if(first)
		{
			first =false;
		}
		else
		{
			if(isStopped)
			{
				stopCounter--;
			}
			else
			{
				//Critter move right
				if(x<(nextTile.getX()) && y==nextTile.getY() && critterMovedOneTile==false)
				{	
					x = x+Clock.delta() * speed;
	
					if(x>=endTile.getX() && y==endTile.getY())
					{
						System.out.println("Critter reached Exit point=" +Path.continousPath.get(Path.continousPath.size()-1));
						Controller.getInstance().money-=100;
						System.out.println("Player money reduced by 100! Current Credits="+Controller.getInstance().money);
						this.isCriterAlive=false;
					}
					
					if(x>=nextTile.getX())
					{
						critterMovedOneTile=true;
					}
				}
				//Move critter down
				if(y<(nextTile.getY()) && x==nextTile.getX() && critterMovedOneTile==false)
				{
					y += Clock.delta() * speed;
					
					if(x==endTile.getX() && y>=endTile.getY())
					{
						System.out.println("Critter reached Exit point=" +Path.continousPath.get(Path.continousPath.size()-1));
						this.isCriterAlive=false;
					}
					
					if(y>=nextTile.getY())
					{
						critterMovedOneTile=true;
					}
				}
	
				//Move Critter left
				if(x>(nextTile.getX()) && y==nextTile.getY() && critterMovedOneTile==false)
				{	
					x -= Clock.delta() * speed;
					
					if(x<=endTile.getX() && y==endTile.getY())
					{
						System.out.println("Critter reached Exit point=" +Path.continousPath.get(Path.continousPath.size()-1));
						this.isCriterAlive=false;
					}
					
					if(x<=nextTile.getX())
					{
						critterMovedOneTile=true;
					}
				}
	
				//Move Critter up
				if(y>(nextTile.getY()) && x==nextTile.getX() && critterMovedOneTile==false)
				{	
					y -= Clock.delta() * speed;
	
					if(x==endTile.getX() && y<=endTile.getY())
					{
						System.out.println("Critter reached Exit point=" +Path.continousPath.get(Path.continousPath.size()-1));
						this.isCriterAlive=false;
					}
					
					if(y<=nextTile.getY())
					{
						critterMovedOneTile=true;
					}
				}
				
				drawTex();
				
				//Critter moved to the next tile so change the start tile and next tile for it
				if(critterMovedOneTile)
				{	
					if(pathStepIndex<Path.continousPath.size()-2)
					{	
						startTile=nextTile;
						x=startTile.getX();
						y=startTile.getY();
						pathStepIndex++;
						this.nextTile = View.grid.getTile(CoordinateConverter.getYCordinate(Path.continousPath.get(pathStepIndex+1)), 
								CoordinateConverter.getXCordinate(Path.continousPath.get(pathStepIndex+1)));
						critterMovedOneTile=false;
					}
				}
			}
			if(stopCounter == 0)
			{
				isStopped = false;
				setSpeed(2);
			}
			if(burnCounter == 0)
			{
				drawTex();
			}
			else
			{
				tex = quickTexture("critter_A_brownFire");
				burnCounter--;
			}
		}
	}
	
	public void setFreezeTimer()
	{
		setSpeed(0);
		stopCounter = 30;
		isStopped = true;
	}
	
	public void burn(int damage)
	{
		burnCounter = 30;
		reduceHealth(damage);
		
	}
	
	public void drawTex()
	{
		if(health <= 20)
		{
			tex = quickTexture("critter_A_green");
		}
		else if(health <= 35)
		{
			tex = quickTexture("critter_A_pink");
		}
		else if(health > 35)
		{
			tex = quickTexture("critter_A_brown");
		}
	}
}
