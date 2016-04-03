/**
 * 
 */
package tower;

import static graphics.Designer.quickTexture;
import map.Tile;
import critter.Critter;
import ai.Strategy;

/**
 * @author s_niga
 *
 */
public class StrategyShootClosestCritter extends Tower implements Strategy {

	@Override
	public int execute(float x, float y, float speedofBullet, int damage, EffectType effectType, ShootStrategyEnum strategyTile, Critter closestCritter) {
		
		System.out.println("shooting tower with strategy Closest critter");

		
//		System.out.println(x+" "+y+" "+speedofBullet+" "+damage+" "+effectType.toString()+" "+strategyTile.name()+" "+targetTile.getX()+" "+targetTile.getY());
		
		shootTiles.add(new ShootTile(quickTexture("bullet"), x, y, speedofBullet, damage, effectType, strategyTile, closestCritter));
		return 0;
	}

}
