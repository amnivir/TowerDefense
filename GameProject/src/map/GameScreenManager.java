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
import main.Boot;

/**
 * This class 
 * @author eshinig
 *
 */
@XmlRootElement
public class GameScreenManager {

	private TileGrid grid;


	@XmlElement
	private int[][] tileCoordinates = new int[Boot.getNoRows()][Boot.getNoColumns()];

	@XmlElement
	private int noRows;

	@XmlElement
	private int noColumns;
	
	@XmlElement
	private static ArrayList<Integer> pathCordinates= new ArrayList<Integer>();
	
	public GameScreenManager()
	{

	}
	public GameScreenManager(TileGrid grid)
	{
		this.grid = grid;
		this.noRows=Boot.getNoRows();
		this.noColumns=Boot.getNoColumns();
	}
	/**
	 * 
	 * @return
	 */
	public  boolean saveMap()
	{
		//System.out.println(Path.isPathValid());
		for(int i=0;i<this.noRows;i++)
		{	
			for(int j=0;j<this.noColumns;j++) 
			{	
				tileCoordinates[j][i] =  grid.getTile(i,j).getType().ordinal();
				System.out.print((i)+","+(j)+"-->"+tileCoordinates[i][j]+"  ");
			}
			System.out.println("");
		}
		
		pathCordinates.clear();
		for(Integer coordinate : TileGrid.pathCordinate)
			pathCordinates.add(coordinate);

		try {
			Date date = new Date() ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
			File file = new File("Map_"+noRows+"X"+noColumns+"_"+dateFormat.format(date) +".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(GameScreenManager.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(Boot.gameScreen, file);
			jaxbMarshaller.marshal(Boot.gameScreen, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 
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
			//System.out.println("path cordinate from file"+coordinate);
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