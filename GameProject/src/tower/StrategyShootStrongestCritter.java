/**
 * 
 */
package tower;

import static graphics.Designer.quickTexture;
import critter.Critter;
import ai.Strategy;

/**
 * @author s_niga
 *
 */
public class StrategyShootStrongestCritter extends Tower implements Strategy {

	@Override
	public int execute(float x, float y, float speedOfBullet, int damage,
			EffectType effectType, ShootStrategyEnum strategyTile,
			Critter strongestCritter) {
		System.out.println("shooting tower with strategy Closest tower");

		
//		System.out.println(x+" "+y+" "+speedofBullet+" "+damage+" "+effectType.toString()+" "+strategyTile.name()+" "+targetTile.getX()+" "+targetTile.getY());
		
		shootTiles.add(new ShootTile(quickTexture("bullet"), x, y, speedOfBullet, damage, effectType, strategyTile, strongestCritter));
		return 0;
	}

}
