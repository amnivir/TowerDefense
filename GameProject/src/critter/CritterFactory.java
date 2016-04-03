/**
 * 
 */
package critter;

import static graphics.Designer.quickTexture;

import ai.Path;
import main.View;
import utility.CoordinateConverter;

/**
 * THis class creates new critters based on Factory Pattern
 * @author eshinig
 *
 */
public class CritterFactory
{
	private static int critterHealth = 50;
	static public Critter getCritter(String critterType)
	{
		if(critterType==null)
		{
			return null;
		}
		
		if(critterType.equalsIgnoreCase("Critter_A"))
		{
			return new Critter_A(quickTexture("critter_A_brown"), View.grid.getTile(CoordinateConverter.getYCordinate(Path.continousPath.get(0)),CoordinateConverter.getXCordinate(Path.continousPath.get(0))),32,32,2,critterHealth);
		}
		return null;
	}
}
