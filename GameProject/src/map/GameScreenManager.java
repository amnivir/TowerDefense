/**
 * 
 */
package map;

import static graphics.Designer.quickTexture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.lwjgl.Sys;

import tower.Tower;
import tower.TowerBomb;
import tower.TowerCannon;
import tower.TowerFactory;
import tower.TowerFreez;
import ai.Path;
import ai.PathValidationCode;
import main.GameStateManager;
import main.View;

/**
 * This class Manages the Screen and provides the functionality like saving the game and loading the game
 * @author eshinig
 *
 */
@XmlRootElement
public class GameScreenManager
{
	private TileGrid grid;

	@XmlElement
	private int[][] tileCoordinates;

	@XmlElement
	private int noRows;

	@XmlElement
	private int noColumns;
	
	@XmlElement
	private static ArrayList<Integer> pathCordinates= new ArrayList<Integer>();
	
	@XmlElement
	private String gameState;

	@XmlElement
	public List<Tower> towerList = new ArrayList<Tower>();
	
	public static GameScreenManager readSavedGame = null;
	
	public GameScreenManager()
	{
		this.noRows = View.getNoRows();
		this.noColumns = View.getNoColumns();
	}
	public GameScreenManager(TileGrid grid)
	{
		this.grid = grid;
		this.noRows = View.getNoRows();
		this.noColumns = View.getNoColumns();
	}
	
	/**
	 * This method saves the game if path is valid in the map
	 * @return boolean return true if map is saved false otherwise
	 */
	public  boolean saveMap(int[][] tileMatix, GameScreenManager gs)
	{	
		tileCoordinates = new int[View.getNoRows()][View.getNoColumns()];
		
		if (Path.isPathValid()!=PathValidationCode.PATH_OK)
		{	
			System.out.println("Map cannot be saved as the path has error: " + Path.isPathValid());
			return false;
		}
		
		for(int i = 0; i < this.noRows; i++)
		{	
			for(int j = 0; j < this.noColumns; j++) 
			{	
				tileCoordinates[j][i] = tileMatix[i][j];
				System.out.print((i) + "," + (j) + "-->" + tileCoordinates[i][j] +"  ");
			}
			System.out.println("");
		}
		
		pathCordinates.clear();
		for(Integer coordinate : TileGrid.pathCordinate)
			pathCordinates.add(coordinate);
		
		gameState = GameStateManager.getGameState().toString();
		

		
		for ( TowerCannon temp : TileGrid.cannonList) 
        {	
           
            {
            	System.out.println("Tower Found! "+temp.getX()+" "+temp.getY());
            	gs.towerList.add(temp);
            	
            }
        }
		
		for ( TowerFreez temp : TileGrid.freezList) 
        {	
           
            {
            	System.out.println("Tower Found! "+temp.getX()+" "+temp.getY());
            	gs.towerList.add(temp);
            	
            }
        }
		
		for ( TowerBomb temp : TileGrid.bombList) 
        {	
           
            {
            	System.out.println("Tower Found! "+temp.getX()+" "+temp.getY());
            	gs.towerList.add(temp);
            	
            }
        }
		
		System.out.println(pathCordinates);
		System.out.println((this.noRows) + (this.noColumns));
		
		try 
		{
			Date date = new Date() ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
			File file = new File("Map_" + noRows + "X" + noColumns + "_" + dateFormat.format(date) + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(GameScreenManager.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			//output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(gs, file);
			jaxbMarshaller.marshal(gs, System.out);
		} 
		catch (JAXBException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * This method reads the saved XML file returns the array of Tiles 
	 */
	public int[][]  loadMap(String mapFileName)
	{	
		
		System.out.println("Loading Game..  " + mapFileName);
		File file = new File(mapFileName);
		JAXBContext jaxbContext;
		try
		{
			jaxbContext = JAXBContext.newInstance(GameScreenManager.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			readSavedGame = (GameScreenManager) jaxbUnmarshaller.unmarshal(file);
			System.out.println("readCoordinates.noRows" + readSavedGame.noRows);
			this.noRows = readSavedGame.noRows;
			this.noColumns = readSavedGame.noColumns;
			GameStateManager.setGameState(readSavedGame.gameState);
			
			for(int i = 0; i < readSavedGame.noRows; i++)
			{	
				for(int j = 0;j < readSavedGame.noColumns; j++) 
				{	
					System.out.print((i) + "," + (j) + "-->" + readSavedGame.tileCoordinates[i][j] + "  ");
				}
				System.out.println("");
			}
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
		
		for(Integer coordinate:readSavedGame.pathCordinates)
		{
			TileGrid.pathCordinate.add(coordinate);
		}
		        
		
		return readSavedGame.tileCoordinates;
	}
	
	public int getNoColumns() 
	{
		return noColumns;
	}
	
	public int getNoRows()
	{
		return noRows;
	}
}
