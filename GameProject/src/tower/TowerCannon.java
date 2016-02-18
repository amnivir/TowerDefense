package tower;

import map.Tile;
import static graphics.Designer.*;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon extends Tower {


	public TowerCannon(Texture texture, Tile startTile, int damage) {
		super();
		this.x=startTile.getX();
		this.y=startTile.getY();
		this.width=startTile.getWidth();
		this.height=startTile.getHeight();
		this.damage=damage;
		this.texture=texture;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw() {
		drawQuadTex(texture, x, y, width, height);
		
	}

	
}
