/**
 * 
 */
package utility;

import main.View;

/**
 * This class provides helper function to converts the linear array to X & Y coordinate
 * @author eshinig
 *
 */
public class CoordinateConverter {

	/**
	 * This method returns the x cordinate
	 * @param arrayIndex The linear array index
	 * @return X coordinate
	 */
	public static int getXCordinate(int arrayIndex)
	{	
		return arrayIndex/View.getNoRows();
		
	}
	
	/**
	 * This method returns the y coordinate
	 * @param arrayIndex The linear array index
	 * @return Y Coordinate
	 */
	public static int getYCordinate(int arrayIndex){
		return arrayIndex-((arrayIndex/View.getNoRows())*View.getNoRows());
		
	}
}
