package map;
import static graphics.Designer.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.newdawn.slick.opengl.Texture;

import tower.ShootTile;
import tower.Tower;
import tower.TowerBomb;
import tower.TowerCannon;
import tower.TowerFactory;
import tower.TowerFreez;
import utility.Log;
import utility.Wave;
import ai.Path;
import main.View;
import main.Controller;
import main.GameStateManager;
import main.GameStateManager.GameState;

/**
 * This class manages the tiles in the grid
 * @author eshinig
 *
 */
public class TileGrid
{
    int blockSize = 32;
    public static Tile map[][];
    public static final ArrayList<Integer> pathCordinate = new ArrayList<>();
    public static Tile tileMenu[];
    public static Tile buttonMenu[];
    public static Tower towerCannon;
    public static Tower towerBomb;
    public static Tower towerFreez;
    public static List<TowerCannon> cannonList = new ArrayList<TowerCannon>();  	//store all the cannon tower objects in game
    public static List<TowerBomb> bombList = new ArrayList<TowerBomb>();			// store all the bomb tower objects in game
    public static List<TowerFreez> freezList = new ArrayList<TowerFreez>();			// store all the freez tower objects in game
    public static int[][] tileMatrix;

    public TileGrid(int rows, int columns, int width)
    {
        map=new Tile[columns][rows]; //create path corrdinate list
        System.out.println(map.length);
        System.out.println(map[0].length);

        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                System.out.println((i) + " " + (j));
                map[i][j] = new Tile((i + width) * blockSize, j * blockSize, blockSize, blockSize, TileType.Water);
            }
        }
        Log.addLogMessage(this.getClass().getSimpleName().toString() , "Map is initialized");
    }

    /**
     * This method  creates the tile for the first time and sets the texture of the tile in the map
     * * @param newMap
     * @param rows
     * @param columns
     */
    public TileGrid(int[][] newMap, int rows, int columns)
    {
        map=new Tile[columns][rows];
        Tile t;

        // for loop to check the static array values and set the corresponding tiles.
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                switch(newMap[j][i])
                {
                case 0:
                    map[i][j] = new Tile(i * blockSize, j * blockSize, blockSize, blockSize, TileType.Grass);
                    break;
                
                case 1:
                    map[i][j] = new Tile(i * blockSize, j * blockSize, blockSize, blockSize, TileType.Dirt);
                    break;
                
                case 2:
                    map[i][j] = new Tile(i * blockSize, j * blockSize, blockSize, blockSize, TileType.Water);
                    break;	
                
                case 3:
                	map[i][j] = new Tile(i * blockSize, j * blockSize, blockSize, blockSize, TileType.TowerCannon);
                    break;
                
                case 4:
                	map[i][j] = new Tile(i * blockSize, j * blockSize, blockSize, blockSize, TileType.TowerBomb);
                    break;
                    
                case 7:
                	map[i][j] = new Tile(i * blockSize, j * blockSize, blockSize, blockSize, TileType.TowerFreez);
                    break;
                    
                
                
                }
            }
        }

        /*
         * Draw the tile menu on the right side of the map
         */
        tileMenu=new Tile[3];
        tileMenu[0] = new Tile((columns) * 32, 0, 32, 32, TileType.Water);
        tileMenu[1] = new Tile((columns + 1) * 32, 0, 32, 32, TileType.Dirt);
        tileMenu[2] = new Tile((columns + 2) * 32, 0, 32, 32, TileType.Grass);

        /*
         * Draw the buttons on the right side of the map for changing the strategy
         * During edit mode draw only play button, rest of the button are place holder
         */

        buttonMenu = new Tile[5];
        buttonMenu[0] =null;
        buttonMenu[1] =null;
        buttonMenu[2] =null;
        buttonMenu[3] =null;
        buttonMenu[4] =new Tile((View.getNoColumns()) * 32, 2 * 32, 32, 32, TileType.PlayButton);
        /*
         * Draw the Tower Icons on the right side of the map for choosing
         */
        towerCannon = (TowerCannon) TowerFactory.getTower("cannon", quickTexture("cannonBase"), new Tile((columns) * 32, 1 * 32, 32, 32, TileType.TowerCannon));
        towerBomb = (TowerBomb) TowerFactory.getTower("bomb", quickTexture("bombBase"), new Tile((columns + 1) * 32, 1 * 32, 32, 32, TileType.TowerBomb));
        towerFreez = (TowerFreez) TowerFactory.getTower("freez", quickTexture("freezBase"), new Tile((columns + 2) * 32, 1 * 32, 32, 32, TileType.TowerFreez));
    }

    /**
     * This method to set a tile to dirt or grass at a particular position 
     * <p>
     * @param  xCoord  X coordinate of the tile 
     * @param  yCoord  Y coordinate of the tile
     * @return void
     */
    public void setTile(int xCoord, int yCoord, TileType tile)
    {
        int xyCoord = yCoord * View.getNoRows() + xCoord;	//may be x&y needs to be interchanged
        System.out.println(xyCoord);
        System.out.println(pathCordinate);

        //TODO Add game state , no more editing after  
        if(tile.textureName == TileType.Dirt.textureName || tile.textureName == TileType.Grass.textureName)
        {
            if(!pathCordinate.contains(xyCoord) && map[xCoord][yCoord].getType() == TileType.Grass && tile.textureName == TileType.Dirt.textureName)
            {
                pathCordinate.add(xyCoord);
                map[xCoord][yCoord].setType(tile);
                map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
            }
            else if(pathCordinate.contains(xyCoord) && map[xCoord][yCoord].getType() == TileType.Dirt && tile.textureName == TileType.Grass.textureName)
            {
                Iterator<Integer> iter = pathCordinate.iterator();
                //Remove the path
                while (iter.hasNext()) 
                {
                    Integer num = iter.next();
                    if (num == xyCoord)
                        iter.remove();
                }
                map[xCoord][yCoord].setType(tile);
                map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
            }

            else if(!pathCordinate.contains(xyCoord) && map[xCoord][yCoord].getType() == TileType.TowerCannon)
            {
                map[xCoord][yCoord].setType(tile);
                map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
            }

            else if(!pathCordinate.contains(xyCoord) && map[xCoord][yCoord].getType() == TileType.TowerBomb)
            {
                map[xCoord][yCoord].setType(tile);
                map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
            }
            else if(!pathCordinate.contains(xyCoord) && map[xCoord][yCoord].getType() == TileType.TowerFreez)
            {
                Iterator<Integer> iter = pathCordinate.iterator();
                while (iter.hasNext())
                {
                    Integer num = iter.next();
                    if (num==xyCoord)
                    {
                        iter.remove();
                    }
                }
                map[xCoord][yCoord].setType(tile);
                map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
            }
        }

        else if(tile.textureName == TileType.TowerBomb.textureName)
        {
            if(map[xCoord][yCoord].getType() == TileType.Grass)
            { 
                boolean flag = towerBomb.buy();
                if(flag == true)
                {
                    System.out.println("You buy bomb tower");
                    System.out.println("Bomb Tower placed");
                    map[xCoord][yCoord].setType(tile);
                    map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));

                    //calling factory method to make the object of
                    bombList.add((TowerBomb) TowerFactory.getTower("bomb", map[xCoord][yCoord].getTexture(), map[xCoord][yCoord]));

                    System.out.println("Your current money is " + Controller.money);
                    
                  //Logging the last created object
                    String towerInfo = bombList.get(bombList.size()-1).getClass().getSimpleName().toString() + String.valueOf(xCoord) + String.valueOf(yCoord);
                    Log.addLogMessage(towerInfo, "tower bought");
                }
                else
                {
                    System.out.println("You cannot buy ... your money is less ->" + Controller.money);
                }
            }
            else 
            {
                System.out.println("Tower can only be placed on grass!");
            }
        }

        else if(tile.textureName == TileType.TowerFreez.textureName)
        {
            if(map[xCoord][yCoord].getType() == TileType.Grass)
            { 
                boolean flag = towerFreez.buy();
                if(flag == true)
                {
                    System.out.println("You buy freez tower");
                    System.out.println("Freez Tower placed");
                    map[xCoord][yCoord].setType(tile);
                    map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));

                    //calling factory method to make the object of
                    freezList.add((TowerFreez) TowerFactory.getTower("freez", map[xCoord][yCoord].getTexture(), map[xCoord][yCoord]));
                    System.out.println("Your current money is " + Controller.money);
                  //Logging the last created object
                    String towerInfo = freezList.get(freezList.size()-1).getClass().getSimpleName().toString() + String.valueOf(xCoord) + String.valueOf(yCoord);
                    Log.addLogMessage(towerInfo, "tower bought");
                
                }
                else
                {
                    System.out.println("You cannot buy ... your money is less ->" + Controller.money);
                }
            }
            else 
            {
                System.out.println("Tower can only be placed on grass!");
            }
        }

        else if(tile.textureName == TileType.TowerCannon.textureName)
        {
            if(map[xCoord][yCoord].getType() == TileType.Grass)
            { 
                boolean flag = towerCannon.buy();
                if(flag == true)
                {	
                    System.out.println("You buy cannon tower");
                    System.out.println("Cannon Tower placed");
                    map[xCoord][yCoord].setType(tile);
                    map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
                    cannonList.add( (TowerCannon) TowerFactory.getTower("cannon", map[xCoord][yCoord].getTexture(), map[xCoord][yCoord] ));
                    System.out.println("Your current money is " + Controller.money);
                    //Logging the last created object
                    String towerInfo = cannonList.get(cannonList.size()-1).getClass().getSimpleName().toString() + String.valueOf(xCoord) + String.valueOf(yCoord);
                    Log.addLogMessage(towerInfo, "tower bought");
                
                }
                else
                {
                    System.out.println("You cannot buy ... your money is less ->" + Controller.money);
                }
            }
            else 
            {
                System.out.println("Tower can only be placed on grass!");
            }		
        }

        else // if scenery then we don't need to compute the path validation
        {
            map[xCoord][yCoord].setType(tile);
            map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
        }
        System.out.println(Path.isPathValid().name());
    }

    /**
     * This method to get a tile at a particular position 
     * <p>
     * @param  xCoord  X coordinate of the tile 
     * @param  yCoord  Y coordinate of the tile
     * @return map object at (xCoord, yCoord) coordinate
     */
    public Tile getTile(int xCoord, int yCoord)	
    {
        return map[xCoord][yCoord];
    }

    public int[][] getTileMatrix()
    {
        tileMatrix = new int[View.getNoRows()][View.getNoColumns()];
        for(int i = 0; i < View.getNoRows(); i++)
        {	
            for(int j = 0; j < View.getNoColumns(); j++) 
            {	
                tileMatrix[i][j] =  View.grid.getTile(i,j).getType().ordinal();
            }
            System.out.println("");
        }
        return tileMatrix;
    }

    /**
     * This method draws the specific kind of tiles in the map. It drwas Scenary , Tower and Path
     */
    public void draw()
    {
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                Tile tile = map[i][j];
                drawQuadTex(tile.getTexture(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
            }
        }

        //Draw Scenary tile menu only in EDIT mode. this block needs to be updated as the tiles wont be removed
        if(GameStateManager.getGameState()==GameState.EDIT)
        { 
            for(int i = 0; i < tileMenu.length; i++)
            {
                Tile tile = tileMenu[i];
                drawQuadTex(tile.getTexture(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
            }
        }
        /*
         * Draw Play button regardless of state
         */
        drawQuadTex(buttonMenu[4].getTexture(), buttonMenu[4].getX(), buttonMenu[4].getY(), buttonMenu[4].getWidth(), buttonMenu[4].getHeight());

        /*
         * Draw the button if GameState is Play
         */
        if(GameStateManager.getGameState()==GameState.PLAY ||GameStateManager.getGameState()==GameState.IDLE)
        {
            if(buttonMenu[0]==null)
            {
                buttonMenu[0] =new Tile((View.getNoColumns() + 1) * 32, 5 * 32, 32, 32, TileType.Closest);
                buttonMenu[1] =new Tile((View.getNoColumns()+ 3) * 32, 5 * 32, 32, 32, TileType.Strongest);
                buttonMenu[2] =new Tile((View.getNoColumns()+ 2) * 32, 5 * 32, 32, 32, TileType.Weakest);
                buttonMenu[3] =new Tile((View.getNoColumns()+ 4) * 32, 5 * 32, 32, 32, TileType.Nearest);

            }
            for(int i = 0; i < buttonMenu.length; i++)
            {
                Tile tile = buttonMenu[i];
                drawQuadTex(tile.getTexture(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
            }

            towerCannon.draw();
            towerBomb.draw();
            towerFreez.draw();
        }
    }

	public static int loadSavedTowers() {
		
		for ( Tower temp : GameScreenManager.readSavedGame.towerList ) 
        {
			String towerInfo = temp.getClass().getSimpleName().toString() + temp.getX()/32 + temp.getY()/32;
            Log.addLogMessage(towerInfo, "tower Loaded");
        
			
		    if(temp instanceof TowerCannon)
			{
		        TowerCannon ref=(TowerCannon) TowerFactory.getTower("cannon", quickTexture("cannonBase"), new Tile(temp.getX(), temp.getY(), 32, 32, TileType.TowerCannon));
				ref.setDamage(temp.getDamage());
				ref.setRange(temp.getRange());
				TileGrid.cannonList.add(ref);
			}
        
		
		    else if(temp instanceof TowerBomb)
        
			{   
		    	TowerBomb ref=(TowerBomb) TowerFactory.getTower("bomb", quickTexture("cannonBase"), new Tile(temp.getX(), temp.getY(), 32, 32, TileType.TowerBomb));
				ref.setDamage(temp.getDamage());
				ref.setRange(temp.getRange());
				TileGrid.bombList.add(ref);
			}
        
		 
		    else if(temp instanceof TowerFreez)
			{   
		    	TowerFreez ref=(TowerFreez) TowerFactory.getTower("freez", quickTexture("cannonBase"), new Tile(temp.getX(), temp.getY(), 32, 32, TileType.TowerFreez));
				ref.setDamage(temp.getDamage());
				ref.setRange(temp.getRange());
				TileGrid.freezList.add(ref);
			}
        }
	return 0;
	}
}
