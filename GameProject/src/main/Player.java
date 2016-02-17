package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static graphics.Designer.*;

import java.awt.List;
import java.util.ArrayList;

import map.GameScreenManager;
import map.TileGrid;
import map.TileType;
/**
 * This class accepts Mouse or Keyboard events and sends the response Model class i.e. Map package   
 * @author s_niga
 *
 */
public class Player {

	private TileGrid grid;
	int  blockSize =32;
	public TileType currentTile= TileType.Grass;
	private ArrayList<Integer> intList = new ArrayList<Integer>();

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
		while(Mouse.next()){
			if(Mouse.getEventButtonState())
			{

				if(((Mouse.getX() / blockSize) < Boot.getNoColumns()) && (((HEIGHT - Mouse.getY()) / blockSize) < Boot.getNoRows()))

				{
					if(Mouse.isButtonDown(0)) // if left mouse key is pressed
					{
						grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile);
					}




					if(Mouse.isButtonDown(1))// if right mouse key is pressed
					{
						grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile);
					}

				}
				
				else 
				{
					currentTile=TileType.Water;
				}
			}
		}

		//Map save by pressing 's' key
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					Boot.gameScreen.saveMap();
					System.out.println("MAP saved");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_L) {
					System.out.println("Load the map");
				}
			}



		}


	}
}
