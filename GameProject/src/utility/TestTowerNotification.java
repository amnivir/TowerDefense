package utility;

import java.util.Observable;
import java.util.Observer;

public class TestTowerNotification implements Observer{
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	System.out.println("Critters Coming .. shoot!");	
	}

}
