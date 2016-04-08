/**
 * 
 */
package tower;

import static graphics.Designer.quickTexture;
import critter.Critter;
import ai.Strategy;

/**
 * @author s_niga
 *This class add new bullets in the list targeting weakest critter
 */
public class StrategyShootWeakestCritter extends Tower implements Strategy {

	/* (non-Javadoc)
	 * @see ai.Strategy#execute(float, float, float, int, tower.EffectType, tower.ShootStrategyEnum, critter.Critter)
	 */
	@Override
	public int execute(float x, float y, float speedOfBullet, int damage,
			EffectType effectType, ShootStrategyEnum strategyTile,
			Critter weakestCritter) {
		System.out.println("shooting tower with strategy Weakest critter");

		
//		System.out.println(x+" "+y+" "+speedofBullet+" "+damage+" "+effectType.toString()+" "+strategyTile.name()+" "+targetTile.getX()+" "+targetTile.getY());
		
		shootTiles.add(new ShootTile(quickTexture("bullet"), x, y, speedOfBullet, damage, effectType, strategyTile, weakestCritter));
		return 0;
	}

}
