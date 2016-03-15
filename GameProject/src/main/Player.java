package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import ai.Path;
import ai.PathValidationCode;
import critter.CritterFactory;
import tower.TowerBomb;
import tower.TowerCannon;
import tower.TowerFreez;
import static graphics.Designer.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.text.html.HTMLDocument.Iterator;

import map.GameScreenManager;
import map.TileGrid;
import map.TileType;
/**
 * This class accepts Mouse or Keyboard events and sends the response Model class i.e. Map package   
 * @author s_niga
 *
 */
public class Player extends Observable{

	private TileGrid grid;
	int  blockSize =32;
	public TileType currentTile= TileType.Grass;
	private ArrayList<Integer> intList = new ArrayList<Integer>();
	//	public static TowerCannon towerCannon;
	public static int towerX=0,towerY=0;
	public static int money = 500;
	String tower="";		//for checking which tower it is in run time
	int x,y;				//to get the current x coordinate and y coordinate of map
	Player(TileGrid grid){
		this.grid=grid;


	}

	//TODO remove printing, set proper if and check it should not crash
	/**
	 * This method sets the Green tile to Dirt tile when mouse points to a valid tile
	 * 
	 * <p>
	 * @param  towerX 
	 * @param  towerY
	 * @return void
	 */
	public void setTile(){


		//TODO do not set tile multiple times i.e. set the tile only once and add toggle effect
		while(Mouse.next()){
			if(Mouse.getEventButtonState())
			{

				if(((Mouse.getX() / blockSize) < Boot.getNoColumns()) && (((HEIGHT - Mouse.getY()) / blockSize) < Boot.getNoRows()))

				{	
					//Create the path
					if(Mouse.isButtonDown(0)) // if left mouse key is pressed
					{
						grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile);
					}

					else if(Mouse.isButtonDown(1)) // if right mouse key is pressed
					{
						System.out.println("press 'X' to sell the tower or press 'U' to update its Range");
						if(grid.getTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize)).getType().textureName=="cannonBase")
						{
							tower="cannon tower";
							x=(int)Math.floor(Mouse.getX() / blockSize);
							y=(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize);

						}
						else if(grid.getTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize)).getType().textureName=="bombBase")
						{
							tower="bomb tower";

							x=(int)Math.floor(Mouse.getX() / blockSize);
							y=(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize);
						}
						else if(grid.getTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize)).getType().textureName=="freezBase")
						{
							tower="freez tower";
							System.out.println("yesss->"+tower);
							x=(int)Math.floor(Mouse.getX() / blockSize);
							y=(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize);
						}
					}
				}

				else if((HEIGHT - Mouse.getY()) / blockSize==0)
				{	
					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns())
						currentTile=TileType.Water;

					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns()+1)
						currentTile=TileType.Dirt;

					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns()+2)
						currentTile=TileType.Grass;
				}
				else if((HEIGHT - Mouse.getY()) / blockSize==1)
				{
					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns())
					{
						currentTile=TileType.TowerCannon;
						TileGrid.towerCannon.description();
					}

					else if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns()+1)
					{
						currentTile=TileType.TowerBomb;
						TileGrid.towerBomb.description();
					}
					else if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns()+2)
					{
						currentTile=TileType.TowerFreez;
						TileGrid.towerFreez.description();
					}

				}
				/*
				 * ON pressing the play button critters will be created and will start moving along the path
				 * Notify the observers (Towers) that critters are created  
				 */
				else if((HEIGHT - Mouse.getY()) / blockSize==2)
				{
					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns())
					{	
						if (Path.isPathValid()==PathValidationCode.PATH_OK)
						{
							GameStateManager.setGameState("PLAY");
							System.out.println("Game State changed to = "+GameStateManager.getGameState());
							Boot.critter = CritterFactory.getCritter("Critter_A");
							
							setChanged();
							notifyObservers(this);
						}
						else
						{
							System.out.println("Cannot Play Game as Path is not Valid");
						}

					}
				}

				else{
					currentTile=TileType.Grass;
				}
			}
		}

		//Map save by pressing 's' key and load map by pressing l
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					Boot.gameScreen.saveMap(grid.getTileMatrix(),Boot.gameScreen);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_L) {
					System.out.println("Loading the map");
				}


				if(tower.equals("cannon tower")||tower.equals("bomb tower")||tower.equals("freez tower")) // all operation will perform on third click
				{

					if (Keyboard.getEventKey() == Keyboard.KEY_X) 
					{
						if(tower.equals("cannon tower"))
						{

							for ( TowerCannon temp : TileGrid.cannonList) 
							{	
								if(x==(temp.getX()/blockSize)&&y==(temp.getY()/blockSize))
								{
									currentTile=TileType.Grass;
									grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile);					
									temp.sell();
									TileGrid.cannonList.remove(temp);
									break;
								}
							}

						}

						else if(tower.equals("bomb tower"))
						{
							for ( TowerBomb temp : TileGrid.bombList) 
							{	
								if(x==(temp.getX()/blockSize)&&y==(temp.getY()/blockSize))
								{
									currentTile=TileType.Grass;
									grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile);					
									temp.sell();
									TileGrid.bombList.remove(temp);
									break;
								}
							}

						}
						else if(tower.equals("freez tower"))
						{
							for ( TowerFreez temp : TileGrid.freezList) 
							{	
								if(x==(temp.getX()/blockSize)&&y==(temp.getY()/blockSize))
								{
									currentTile=TileType.Grass;
									grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile);					
									temp.sell();
									TileGrid.freezList.remove(temp);
									break;
								}
							}

						}
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_U) 
					{
						System.out.println("For increasing the range you will be charged $10");
						System.out.println("Press 'SPACE' to enhance the range or e for exit");
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_SPACE)
					{
						if(tower.equals("cannon tower"))
						{					
							for ( TowerCannon temp : TileGrid.cannonList) {

								if(x==(temp.getX()/blockSize)&&y==(temp.getY()/blockSize)){
									temp.update();
									break;
								}
							}
						}
						else if(tower.equals("bomb tower"))
						{
							for ( TowerBomb temp : TileGrid.bombList) {

								if(x==(temp.getX()/blockSize)&&y==(temp.getY()/blockSize)){
									temp.update();
									break;
								}
							}

						}
						else if(tower.equals("freez tower"))
						{
							for ( TowerFreez temp : TileGrid.freezList) {

								if(x==(temp.getX()/blockSize)&&y==(temp.getY()/blockSize)){
									temp.update();
									break;
								}
							}

						}

					}

				}



			}
		}

	}
}
