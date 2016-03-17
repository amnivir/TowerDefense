package tower;

import static graphics.Designer.*;

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
public class ShootTile extends Tower{
	private Critter targetTile;
	private float xVelocity, yVelocity;
	private boolean alive;
	private Tile towerCordinates;
	public ShootTile(Texture texture,float x, float y,float speed, int damage, Critter targetTile)
	{
		//super();
		this.texture = texture;
		this.x=x;
		this.y=y;
		this.speed = speed;
		this.damage = damage;
		this.width=32;
		this.height=32;
		this.angle=0;
		this.targetTile=targetTile;
		this.xVelocity=0f;
		this.yVelocity=0f;
		alive =true;
		towerCordinates = new Tile(x, y, width, height, TileType.Grass);
		if(targetTile!=null)
			calculateDirection();
	}

	/**
	 * Calculates the direction of the bullet
	 * TODO needs to return a specific tile
	 */
	private void calculateDirection()
	{	float totalAllowedMovement = 1.0f;
	float xDistancefromTile = Math.abs(targetTile.getX()-x);
	float yDistancefromTile = Math.abs(targetTile.getY()-y);
	float totalDistanceFromTarget=xDistancefromTile+yDistancefromTile;
	float xPercentofMovement=xDistancefromTile/totalDistanceFromTarget;
	xVelocity=xPercentofMovement;
	yVelocity=totalAllowedMovement-xPercentofMovement;
	if(targetTile.getX()<x)
		xVelocity*=-1;
	if(targetTile.getY()<y)
		yVelocity*=-1;

	//		System.out.println((xVelocity)+" "+(yVelocity));
	}
	@Override
	public void draw() {
		drawQuadTex(texture, x, y, 32, 32);
		//		drawQuadTexRot(texture,x,y,width,height,angle);
	}

	@Override
	public boolean buy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void description() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if(alive && Clock.delta()<1 &&Clock.delta()>0)
		{

			x += Clock.delta() * speed*xVelocity;
			y += Clock.delta() * speed*yVelocity;

			//    	if(Designer.chechCollision(x, y, 32, 32, targetTile.getX(), targetTile.getY(), targetTile.getWidth(), targetTile.getHeight()))
			if(Wave.getCritterList().size()!=0)
			{
				if(Designer.chechCollision(x, y, 32, 32, Wave.getCritterList().get(0).getX(), Wave.getCritterList().get(0).getY(), Wave.getCritterList().get(0).getWidth(), Wave.getCritterList().get(0).getHeight()))
				{

					alive=false;
					//    		System.out.println("Tower ->"+towerCordinates.getX()/32+" "+towerCordinates.getY()/32 +" hits critter");

					for(TowerCannon cannonTower: TileGrid.cannonList )
					{
						if(cannonTower.getX()==towerCordinates.getX()&&cannonTower.getY()==towerCordinates.getY())
						{
							Wave.getCritterList().get(0).reduceHealth(cannonTower.range);
							System.out.println( "health->"+Wave.getCritterList().get(0).getHealth());
							if(Wave.getCritterList().get(0).getHealth()<=0)
								System.out.println("Tower ->"+towerCordinates.getX()/32+" "+towerCordinates.getY()/32 +" hits critter");

						}


					}

					for(TowerBomb bombTower: TileGrid.bombList )
					{
						if(bombTower.getX()==towerCordinates.getX()&&bombTower.getY()==towerCordinates.getY())
						{
							Wave.getCritterList().get(0).reduceHealth(bombTower.range);
							System.out.println( "health->"+Wave.getCritterList().get(0).getHealth());
							if(Wave.getCritterList().get(0).getHealth()<=0)
								System.out.println("Tower ->"+towerCordinates.getX()/32+" "+towerCordinates.getY()/32 +" hits critter");

						}

					}
					for(TowerFreez freezTower: TileGrid.freezList )
					{
						if(freezTower.getX()==towerCordinates.getX()&&freezTower.getY()==towerCordinates.getY())
						{
							Wave.getCritterList().get(0).reduceHealth(freezTower.range);
							System.out.println( "health->"+Wave.getCritterList().get(0).getHealth());
							if(Wave.getCritterList().get(0).getHealth()<=0)
								System.out.println("Tower ->"+towerCordinates.getX()/32+" "+towerCordinates.getY()/32 +" hits critter");

						}
					}
					//    		Wave.getCritterList().get(0).



				}

				//  		System.out.println("bullet hit tile");
				draw();
			}

		}
	}

	@Override
	public void sell() {
		// TODO Auto-generated method stub

	}

}
