/**
 * 
 */
package map;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.lwjgl.Sys;

import ai.Path;
import ai.PathValidationCode;
import main.Boot;

/**
 * This class Manages the Screen and provides the functionality like saving the game and loading the game
 * @author eshinig
 *
 */
@XmlRootElement
public class GameScreenManager {

	private TileGrid grid;


	@XmlElement
	private int[][] tileCoordinates;

	@XmlElement
	private int noRows;

	@XmlElement
	private int noColumns;
	
	@XmlElement
	private static ArrayList<Integer> pathCordinates= new ArrayList<Integer>();
	
	public GameScreenManager()
	{
		this.noRows=Boot.getNoRows();
		this.noColumns=Boot.getNoColumns();
	}
	public GameScreenManager(TileGrid grid)
	{
		this.grid = grid;
		this.noRows=Boot.getNoRows();
		this.noColumns=Boot.getNoColumns();
	}
	/**
	 * This method saves the game if path is valid in the map
	 * @return boolean return true if map is saved false otherwise
	 */
	public  boolean saveMap(int[][] tileMatix, GameScreenManager gs)
	{	tileCoordinates = new int[Boot.getNoRows()][Boot.getNoColumns()];
		if (Path.isPathValid()!=PathValidationCode.PATH_OK)
			{	System.out.println("Map cannot be saved as the path has error: "+Path.isPathValid());
				return false;
			}
		for(int i=0;i<this.noRows;i++)
		{	
			for(int j=0;j<this.noColumns;j++) 
			{	
				tileCoordinates[j][i] =  tileMatix[i][j];
				System.out.print((i)+","+(j)+"-->"+tileCoordinates[i][j]+"  ");
			}
			System.out.println("");
		}
		
		pathCordinates.clear();
		for(Integer coordinate : TileGrid.pathCordinate)
			pathCordinates.add(coordinate);
		
		System.out.println(pathCordinates);
		System.out.println((this.noRows)+(this.noColumns));
		try {
			Date date = new Date() ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
			File file = new File("Map_"+noRows+"X"+noColumns+"_"+dateFormat.format(date) +".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(GameScreenManager.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			//output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(gs, file);
			jaxbMarshaller.marshal(gs, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * This method reads the saved XML file reutrns the array of Tiles 
	 */
	public int[][]  loadMap(String mapFileName)
	{	GameScreenManager readCoordinates = null;
		System.out.println("Loading Game..  " + mapFileName);
		
		File file = new File(mapFileName);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(GameScreenManager.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			readCoordinates = (GameScreenManager) jaxbUnmarshaller.unmarshal(file);
			System.out.println("readCoordinates.noRows"+readCoordinates.noRows);
			this.noRows=readCoordinates.noRows;
			this.noColumns=readCoordinates.noColumns;
			for(int i=0;i<readCoordinates.noRows;i++)
			{	
				for(int j=0;j<readCoordinates.noColumns;j++) 
				{	
					System.out.print((i)+","+(j)+"-->"+readCoordinates.tileCoordinates[i][j]+"  ");
				}
				System.out.println("");
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Integer coordinate:readCoordinates.pathCordinates)
		{
			TileGrid.pathCordinate.add(coordinate);
		}

		return readCoordinates.tileCoordinates;
	}
	
	public int getNoColumns() {
		return noColumns;
	}
	
	public int getNoRows() {
		return noRows;
	}

}
