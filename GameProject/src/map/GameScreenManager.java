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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ai.Path;
import main.Boot;

/**
 * @author eshinig
 *
 */
@XmlRootElement
public class GameScreenManager {

	static TileGrid grid;
	
	
	@XmlElement
	private static int[][] tileCoordinates = new int[Boot.getNoRows()][Boot.getNoColumns()];

	public GameScreenManager()
	{
		
	}
	public GameScreenManager(TileGrid grid)
	{
		this.grid = grid;
	}

	public static int saveMap()
	{	GameScreenManager test = new GameScreenManager();
		Path p = new Path();
		System.out.println(Path.isPathValid());
		for(int i=0;i<Boot.getNoRows();i++)
		{	for(int j=0;j<Boot.getNoColumns();j++) 
		{	tileCoordinates[i][j] =  grid.getTile(i,j).getType().ordinal();
			System.out.print((i)+","+(j)+"-->"+tileCoordinates[i][j]+"  ");
		}
		System.out.println("");
		}
	
	
	
	
	  try {
		  Date date = new Date() ;
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ") ;
		File file = new File("Map_"+dateFormat.format(date) +".xml");
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

	public static int loadMap()
	{
		return 0;
	}

}
