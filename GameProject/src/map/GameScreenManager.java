/**
 * 
 */
package map;

import java.io.File;
import java.text.SimpleDateFormat;
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
	private static int noRows;

	@XmlElement
	private static int noColumns;

	public GameScreenManager()
	{

	}
	public GameScreenManager(TileGrid grid)
	{
		this.grid = grid;
		GameScreenManager.noRows=Boot.getNoRows();
		GameScreenManager.noColumns=Boot.getNoColumns();
	}
	/**
	 * 
	 * @return
	 */
	public  int saveMap()
	{
		//System.out.println(Path.isPathValid());
		for(int i=0;i<Boot.getNoRows();i++)
		{	
			for(int j=0;j<Boot.getNoColumns();j++) 
			{	
				tileCoordinates[i][j] =  grid.getTile(i,j).getType().ordinal();
				System.out.print((i)+","+(j)+"-->"+tileCoordinates[i][j]+"  ");
			}
			System.out.println("");
		}




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
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 
	 */
	public static int[][]  loadMap(String mapFileName)
	{	GameScreenManager readCoordinates = null;
		System.out.println("Loading Game..  " + mapFileName);
		
		File file = new File(mapFileName);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(GameScreenManager.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			readCoordinates = (GameScreenManager) jaxbUnmarshaller.unmarshal(file);
			GameScreenManager.noRows=readCoordinates.noRows;
			GameScreenManager.noColumns=readCoordinates.noColumns;
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
		
		return readCoordinates.tileCoordinates;
	}
	public static int getNoColumns() {
		return noColumns;
	}
	public static void setNoColumns(int noColumns) {
		GameScreenManager.noColumns = noColumns;
	}
	public static int getNoRows() {
		return noRows;
	}
	public static void setNoRows(int noRows) {
		GameScreenManager.noRows = noRows;
	}

}
