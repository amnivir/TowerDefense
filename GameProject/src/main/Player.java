package main;

import org.lwjgl.input.Mouse;
import static graphics.Designer.*;
import map.TileGrid;
import map.TileType;

public class Player {

	private TileGrid grid;
	
	Player(TileGrid grid){
		this.grid=grid;
	}
	
	public void setTile(){
		grid.setTile((int)Math.floor(Mouse.getX() / 64),(int)Math.floor((HEIGHT-Mouse.getY()-1) / 64), TileType.Water);
	}
}
