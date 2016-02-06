package graphics;

import static graphics.Designer.drawQuadTex;
import main.Boot;
import map.TileType;

public class DesignerButtons {

	int WIDTH;
	int HEIGHT;
	Button grass= null;
	Button water= null;
	Button dirt= null;
	
	public DesignerButtons(int WIDTH,int HEIGHT)
	{
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
	
	public void draw()
	{
		grass= new Button(WIDTH-200,HEIGHT-200,30,30,TileType.Grass,"Grass");
		water= new Button(WIDTH-170,HEIGHT-200,30,30,TileType.Water, "Water");
		dirt= new Button(WIDTH-140,HEIGHT-200,30,30,TileType.Dirt, "Dirt");
		
		drawQuadTex(grass.getTexture(), grass.xx, grass.yy, grass.ww, grass.hh);
		drawQuadTex(dirt.getTexture(), dirt.xx, dirt.yy, dirt.ww, dirt.hh);
		drawQuadTex(water.getTexture(), water.xx, water.yy, water.ww, water.hh);
		
		if(grass.isClicked())
		{
			Boot.player.currentTile = TileType.Grass;
		}
		else if(water.isClicked())
		{
			Boot.player.currentTile = TileType.Water;
		}
		else if(dirt.isClicked())
		{
			Boot.player.currentTile = TileType.Dirt;
		}
	}
}
