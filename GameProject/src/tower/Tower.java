package tower;

import org.newdawn.slick.opengl.Texture;

import map.Tile;

public abstract class Tower {
	protected float x,y,width,height;
	protected int damage;
	protected Tile startTile;
	protected Texture texture;
	protected int price;
	
	public abstract void draw();
	public abstract boolean buy();

}
