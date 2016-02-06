package main;

import org.lwjgl.input.Mouse;
import static graphics.Designer.*;
import map.TileGrid;
import map.TileType;

public class Player {

	private TileGrid grid;
	int  blockSize =32;
	public TileType currentTile= TileType.Grass;
	
	Player(TileGrid grid){
		this.grid=grid;
	}
	
	//TODO remove printing, set proper if and check it should not crash
	/**
	 * This method sets the Green tile to Dirt tile when mouse points to a valid tile
	 * 
	 * <p>
	 * @param  none 
	 * @param  none
	 * @return void
	 */
	public void setTile(){
		//TODO do not set tile multiple times i.e. set the tile only once and add toggle effect
		
		if(((Mouse.getX() / blockSize) < Boot.getNoColumns()) && (((HEIGHT - Mouse.getY()) / blockSize) < Boot.getNoRows()))
			
			if(Mouse.isButtonDown(0)) // if left mouse key is pressed
				grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile.Dirt);
			
		
			if(Mouse.isButtonDown(1))// if right mouse key is pressed
			{
				grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile.Grass);
			}
			
	}
}
