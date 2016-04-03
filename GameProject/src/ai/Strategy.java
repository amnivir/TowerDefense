/**
 * 
 */
package ai;

import map.Tile;
import tower.EffectType;
import tower.ShootStrategyEnum;
import critter.Critter;

/**
 * This interface defines the strategy to be used in the game
 * @author s_niga
 */
public interface Strategy 
{
	/**
	 * This method mentions which strategy is to be chosen
	 * @param closestCritter 
	 * @param strategyTile 
	 * @param effectType 
	 * @param y 
	 * @param x 
	 */
	int execute(float x, float y, float speedOfBullet, int damage, EffectType effectType, ShootStrategyEnum strategyTile, Critter closestCritter);
}
