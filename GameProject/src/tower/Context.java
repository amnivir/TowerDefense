/**
 * 
 */
package tower;

import map.Tile;
import critter.Critter;
import ai.Strategy;

/**
 * @author s_niga
 *
 */
public class Context {

	private Strategy strategy;

	public Context(Strategy strategy){
		this.strategy = strategy;
	}

	/*public void executeStrategy(float x, float y, int speedOfShootTile, EffectType effectType, ShootStrategyEnum strategyTile, Critter closestCritter){

		//targetTile = closestCritter.getStartTile();
		//shootTiles.add(new ShootTile(quickTexture("bullet"), x, y, 40, this.damage, effectType, ShootStrategyEnum.closestCritter, targetTile));
		strategy.execute(x,y,speedOfShootTile,effectType,strategyTile,closestCritter);
	}*/

	public void executeStrategy(float x, float y, float speedOfBullet, int damage,
			EffectType effectType, ShootStrategyEnum strategyTile,
			Critter Critter) {
		// TODO Auto-generated method stub
		strategy.execute(x,y,speedOfBullet,damage,effectType,strategyTile,Critter);
	}


}