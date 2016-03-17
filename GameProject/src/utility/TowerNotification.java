package utility;

import java.util.Observable;
import java.util.Observer;

/**
 * This tower sends notification to concrete observer class tower to begin or stop shooting
 * @author s_niga
 *
 */
public class TowerNotification implements Observer{
	
	public static boolean towerShoot = false; 
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	System.out.println("Critters Coming .. Notfication sent to Tower to shoot!");
	towerShoot=true;
	}

}
