package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import ai.Path;
import ai.PathValidationCode;
import critter.CritterFactory;
import main.GameStateManager.GameState;
import tower.Tower;
import tower.TowerBomb;
import tower.TowerCannon;
import tower.TowerFreez;
import utility.Wave;
import static graphics.Designer.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.text.html.HTMLDocument.Iterator;

import map.GameScreenManager;
import map.Tile;
import map.TileGrid;
import map.TileType;
/**
 * This Singleton class accepts Mouse or Keyboard events and sends the response Model class i.e. Map package   
 * @author s_niga
 *
 */
public class Controller extends Observable
{
    private static Controller instance = new Controller();
    private TileGrid grid;
    int  blockSize = 32;
    public TileType currentTile = TileType.Grass;
    private ArrayList<Integer> intList = new ArrayList<Integer>();
    public static int towerX = 0, towerY = 0;
    public static int money = 500;
    
    String tower = "";		//for checking which tower it is in run time
    int x, y;//to get the current x coordinate and y coordinate of map
    boolean playTilePressedFirstTime=false;

    private Controller()
    {
        this.grid = View.grid;
    }

    /**
     * Singleton instance
     * @return Player instance
     */
    public static Controller getInstance()
    {
        if(instance == null)
        {
            instance = new Controller();

        }
        return instance;
    }

    //TODO remove printing, set proper if and check it should not crash
    /**
     * This method sets the Green tile to Dirt tile when mouse points to a valid tile
     * <p>
     * @param  towerX 
     * @param  towerY
     * @return void
     */
    public void setTile()
    {
        //TODO do not set tile multiple times i.e. set the tile only once and add toggle effect
        while(Mouse.next())
        {
            if(Mouse.getEventButtonState())
            {
                if(((Mouse.getX() / blockSize) < View.getNoColumns()) && (((HEIGHT - Mouse.getY()) / blockSize) < View.getNoRows()))
                {	
                    //Create the path
                    if(Mouse.isButtonDown(0)) // if left mouse key is pressed
                    {
                        grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize), currentTile);
                    }

                    else if(Mouse.isButtonDown(1)) // if right mouse key is pressed
                    {
                        System.out.println("press 'X' to sell the tower or press 'U' to update its Range or 'D' to see its description");

                        if(grid.getTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize)).getType().textureName == "cannonBase")
                        {
                            tower = "cannon tower";
                            x = (int)Math.floor(Mouse.getX() / blockSize);
                            y = (int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize);
                        }

                        else if(grid.getTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize)).getType().textureName == "bombBase")
                        {
                            tower = "bomb tower";
                            x = (int)Math.floor(Mouse.getX() / blockSize);
                            y =(int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize);
                        }

                        else if(grid.getTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize)).getType().textureName == "freezBase")
                        {
                            tower = "freez tower";
                            System.out.println("yesss->" + tower);
                            x = (int)Math.floor(Mouse.getX() / blockSize);
                            y = (int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize);
                        }
                    }
                }
                
                //User can update map or select scenary only in EDIT mode
                else if((HEIGHT - Mouse.getY()) / blockSize == 0 && GameStateManager.getGameState()==GameState.EDIT)
                {	
                    if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns())
                        currentTile = TileType.Water;

                    if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns()+1)
                        currentTile = TileType.Dirt;

                    if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns()+2)
                        currentTile = TileType.Grass;
                }
                else if((HEIGHT - Mouse.getY()) / blockSize == 1)
                {
                    if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns())
                    {
                        currentTile = TileType.TowerCannon;
                        TileGrid.towerCannon.description();
                        //TileGrid.towerCannon.preaperShoot();
                    }

                    else if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns() + 1)
                    {
                        currentTile = TileType.TowerBomb;
                        TileGrid.towerBomb.description();
                    }

                    else if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns() + 2)
                    {
                        currentTile = TileType.TowerFreez;
                        TileGrid.towerFreez.description();
                    }
                }

                /*
                 * ON pressing the play button critters will be created and will start moving along the path
                 * Notify the observers (Towers) that critters are created  
                 */
                else if((HEIGHT - Mouse.getY()) / blockSize == 2)
                {
                    if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns())
                    {	
                        if (Path.isPathValid() == PathValidationCode.PATH_OK && GameStateManager.getGameState() != GameState.END)
                        {
                            /*
                             * If play button is pressed for first time we transistion to IDLE mode to avoid critter 
                             * wave. On second click the critter movement will start
                             */
                            if(!playTilePressedFirstTime)
                                {
                                 playTilePressedFirstTime=true;
                                 GameStateManager.setGameState("IDLE");
                                 return;
                                }
                            GameStateManager.setGameState("PLAY");
                            Wave.resetCritterCounter();;
                        }
                        else
                        {
                            System.out.println("Cannot Play Game as Path is not Valid");
                        }
                    }
                }

                else if(((HEIGHT - Mouse.getY()) / blockSize == 4||((HEIGHT - Mouse.getY()) / blockSize == 5)))
                {		
                    if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns() + 1)
                    {	
                        System.out.println("Shooting strategy changed");
                        Tower.shootingStrategy = 1;
                    }

                    if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns() + 2)
                    {
                        System.out.println("Shooting strategy changed");
                        Tower.shootingStrategy = 2;
                    }
                    if((int)Math.floor(Mouse.getX() / blockSize) == View.getNoColumns()+3)
                    {
                        System.out.println("Shooting strategy changed");
                        Tower.shootingStrategy = 3;
                    }
                }

                else
                {
                    currentTile = TileType.Grass;
                }
            }
        }

        //Map save by pressing 's' key and load map by pressing l
        while (Keyboard.next()) 
        {
            if (Keyboard.getEventKeyState())
            {
                if (Keyboard.getEventKey() == Keyboard.KEY_S)
                {
                    View.gameScreen.saveMap(grid.getTileMatrix(), View.gameScreen);
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_L) 
                {
                    System.out.println("Loading the map");
                }


                if(tower.equals("cannon tower") || tower.equals("bomb tower") || tower.equals("freez tower")) // all operation will perform on third click
                {
                    if (Keyboard.getEventKey() == Keyboard.KEY_X) 
                    {
                        if(tower.equals("cannon tower"))
                        {
                            for ( TowerCannon temp : TileGrid.cannonList) 
                            {	
                                if(x == (temp.getX() / blockSize) && y == (temp.getY() / blockSize))
                                {
                                    currentTile = TileType.Grass;
                                    grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize), currentTile);					
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
                                if(x == (temp.getX() / blockSize) && y == (temp.getY() / blockSize))
                                {
                                    currentTile = TileType.Grass;
                                    grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize), currentTile);					
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
                                if(x == (temp.getX() / blockSize) && y == (temp.getY() / blockSize))
                                {
                                    currentTile = TileType.Grass;
                                    grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT - Mouse.getY() - 1) / blockSize), currentTile);					
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
                            for ( TowerCannon temp : TileGrid.cannonList)
                            {
                                if(x == (temp.getX() / blockSize) && y == (temp.getY() / blockSize))
                                {
                                    temp.upgrade();
                                    break;
                                }
                            }
                        }

                        else if(tower.equals("bomb tower"))
                        {
                            for ( TowerBomb temp : TileGrid.bombList)
                            {
                                if(x == (temp.getX() / blockSize) && y == (temp.getY() / blockSize))
                                {
                                    temp.upgrade();
                                    break;
                                }
                            }
                        }

                        else if(tower.equals("freez tower"))
                        {
                            for ( TowerFreez temp : TileGrid.freezList) 
                            {
                                if(x == (temp.getX() / blockSize) && y == (temp.getY() / blockSize))
                                {
                                    temp.upgrade();
                                    break;
                                }
                            }
                        }
                    }

                    if (Keyboard.getEventKey() == Keyboard.KEY_D) 
                    {
                        {
                            for(TowerBomb cannonBomb : TileGrid.bombList )
                            {
                                if(x==cannonBomb.getX()/ blockSize && y== cannonBomb.getY()/ blockSize)
                                    cannonBomb.description();
                            }

                        }
                        if(tower.equals("freez tower"))
                        {
                            for(TowerFreez freezTower : TileGrid.freezList )
                            {
                                if(x==freezTower.getX()/ blockSize && y== freezTower.getY()/ blockSize)
                                    freezTower.description();
                            }

                        }
                        if(tower.equals("cannon tower"))
                        {
                            for(TowerCannon cannonTower : TileGrid.cannonList )
                            {
                                if(x==cannonTower.getX()/ blockSize && y== cannonTower.getY()/ blockSize)
                                    cannonTower.description();
                            }
                        }
                    }
                }
            }
        }
    }
}
