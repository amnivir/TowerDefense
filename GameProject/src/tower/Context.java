/**
 * 
 */
package tower;

import map.Tile;
import critter.Critter;
import ai.Strategy;

/**
 * @author s_niga
 * This class has the object of strategy
 */
public class Context {

	private Strategy strategy;

	public Context(Strategy strategy)
	{
		this.strategy = strategy;
	}

	public int executeStrategy(float x, float y, float speedOfBullet, int damage,
			EffectType effectType, ShootStrategyEnum strategyTile,
			Critter Critter) 
	{
		strategy.execute(x,y,speedOfBullet,damage,effectType,strategyTile,Critter);
		return 0;
	}
}