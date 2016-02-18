package tower;

import main.Player;
import map.Tile;
import static graphics.Designer.*;

import org.newdawn.slick.opengl.Texture;

public class TowerBomb extends Tower {


	public TowerBomb(Texture texture, Tile startTile) {
		super();
		this.x=startTile.getX();
		this.y=startTile.getY();
		this.width=startTile.getWidth();
		this.height=startTile.getHeight();
		this.damage=20;
		this.texture=texture;
		this.price=30;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw() {
		drawQuadTex(texture, x, y, width, height);
		
	}

	public boolean buy() {
		if(Player.money >=price)
		{
			Player.money=Player.money-price;
			return true;
		}
		else
			return false;

		
	}

	
}