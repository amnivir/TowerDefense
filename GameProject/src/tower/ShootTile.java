package tower;

import static graphics.Designer.*;

import org.newdawn.slick.opengl.Texture;

import critter.Critter;
import graphics.Designer;
import map.Tile;
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
		if(alive)
		{
		x += Clock.delta() * speed*xVelocity;
    	y += Clock.delta() * speed*yVelocity;
    	
//    	if(Designer.chechCollision(x, y, 32, 32, targetTile.getX(), targetTile.getY(), targetTile.getWidth(), targetTile.getHeight()))
    		if(Wave.getCritterList().size()!=0)
    		{
    	if(Designer.chechCollision(x, y, 32, 32, Wave.getCritterList().get(0).getX(), Wave.getCritterList().get(0).getY(), Wave.getCritterList().get(0).getWidth(), Wave.getCritterList().get(0).getHeight()))
    	{
    		
    		alive=false;
    		
    		
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
