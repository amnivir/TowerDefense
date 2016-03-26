package tower;

import java.util.Observable;
import java.util.Observer;

import map.TileGrid;

/**
 * This tower sends notification to concrete observer class tower to begin or stop shooting
 * @author s_niga
 *
 */
public class TowerNotification implements Observer
{
	
	public static boolean towerShoot = false; 
	
	@Override
	public void update(Observable o, Object arg) 
	{
		for(TowerCannon cannonTower: TileGrid.cannonList )
        {
            cannonTower.preaperShoot();
        }

        for(TowerBomb bombTower: TileGrid.bombList )
        {
            bombTower.preaperShoot();
        }
        for(TowerFreez freezTower: TileGrid.freezList )
        {
            freezTower.preaperShoot();
        }
		towerShoot = true;
	}

}
