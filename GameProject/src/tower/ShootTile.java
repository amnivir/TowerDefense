package tower;

import static graphics.Designer.drawQuadTex;

import org.newdawn.slick.opengl.Texture;
import utility.Clock;


/**
 * This class is a sub class of Tower to shoot the critters
 * @author Gurpreet
 *
 */
public class ShootTile extends Tower{

	public ShootTile(Texture texture,float x, float y,float speed, int damage)
	{
		//super();
		this.texture = texture;
		this.x=x;
		this.y=y;
		this.speed = speed;
		this.damage = damage;
	}

	@Override
	public void draw() {
		drawQuadTex(texture, x, y, 32, 32);
		
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
		x += Clock.delta() * speed;
//		y += Clock.delta() * speed;
//		draw();
	}

	@Override
	public void sell() {
		// TODO Auto-generated method stub
		
	}

	}
