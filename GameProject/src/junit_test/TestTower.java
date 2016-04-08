package junit_test;

import java.awt.Font;
import java.util.Arrays;

import main.View;
import main.Controller;
import map.GameScreenManager;
import map.Tile;
import map.TileGrid;
import map.TileType;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import graphics.Designer;
import graphics.Designer.*;
import ai.Path;
import ai.PathValidationCode;
import tower.EffectType;
import tower.ShootStrategyEnum;
import tower.Tower;
import tower.TowerBomb;
import tower.TowerCannon;
import tower.TowerFactory;
import tower.TowerFreez;
import junit.framework.TestCase;
import static graphics.Designer.beginSession;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;

public class TestTower extends TestCase 
{
	Texture tex=null;
	Tile tile=null;
	Tower t1,t2,t3;

	@Before
	public void setUp() throws Exception 
	{
		beginSession(5,5);
		t1=TowerFactory.getTower("freez", Designer.quickTexture("freezBase"),new Tile(0, 0, 32, 32, TileType.TowerFreez));
		t2=TowerFactory.getTower("bomb", Designer.quickTexture("bombBase"),new Tile(0, 0, 32, 32, TileType.TowerBomb));
		t3=TowerFactory.getTower("cannon", Designer.quickTexture("cannonBase"),new Tile(0, 0, 32, 32, TileType.TowerCannon));
		Controller.money=100;
	}
	/**
	 * THis method tests instance of Freez Tower
	 */
	@Test
	public void testFreezFactory() 
	{
		assertTrue(t1 instanceof TowerFreez);
		Display.destroy();
	}
	/**
	 * THis method tests instance of Bomb Tower
	 */
	@Test
	public void testBombFactory()
	{
		assertTrue(t2 instanceof TowerBomb);
		Display.destroy();
	}
	/**
	 * THis method tests instance of Cannon Tower
	 */
	@Test
	public void testCannonFactory() 
	{
		assertTrue(t3 instanceof TowerCannon);
		Display.destroy();
	}
	/**
	 * THis method tests buying of Cannon Tower
	 */
	@Test
	public void testCannonBuy()
	{
		assertTrue(t3.buy());
		Display.destroy();
	}
	/**
	 * THis method tests buying of Bomb Tower
	 */
	@Test
	public void testBombBuy()
	{
		assertTrue(t2.buy());
		Display.destroy();
	}
	/**
	 * THis method tests buying of Freez Tower
	 */
	@Test
	public void testFreezBuy() 
	{
		assertTrue(t1.buy());
		Display.destroy();
	}
	/**
	 * THis method tests damage of Freez Tower
	 */
	@Test
	public void testFreezDamage() 
	{
		assertEquals(t1.getDamage(),30);
		Display.destroy();
	}
	/**
	 * THis method tests Range of Freez Tower
	 */
	@Test
	public void testFreezRange()
	{
		assertEquals(t1.getRange(),5);
		Display.destroy();
	}
	/**
	 * THis method tests Damage of Bomb Tower
	 */
	@Test
	public void testBombDamage() 
	{
		assertEquals(t2.getDamage(),20);
		Display.destroy();
	}
	/**
	 * THis method tests Range of Bomb Tower
	 */
	@Test
	public void testBombRange() 
	{
		assertEquals(t2.getRange(),4);
		Display.destroy();
	}
	/**
	 * THis method tests Damage of Cannon Tower
	 */
	@Test
	public void testCannonDamage() 
	{
		assertEquals(t3.getDamage(),10);
		Display.destroy();
	}
	/**
	 * THis method tests Range of Cannon Tower
	 */
	@Test
	public void testCannonRange() 
	{
		assertEquals(t3.getRange(),3);
		Display.destroy();
	}
	/**
	 * THis method tests Selling of Freez Tower
	 */
	@Test
	public void testFreezSell() 
	{
		int originalMoney = Controller.money;
		int towerPrice = t1.getPrice();
		int expectedValue = originalMoney + towerPrice;
		
		t1.sell();
		int actualValue = Controller.money;
		assertEquals(expectedValue, actualValue);
		Display.destroy();
	}
	/**
	 * THis method tests Selling of Cannon Tower
	 */
	@Test
	public void testCannonSell() 
	{
		int originalMoney = Controller.money;
		int towerPrice = t3.getPrice();
		int expectedValue = originalMoney + towerPrice;
		
		t3.sell();
		int actualValue = Controller.money;
		assertEquals(expectedValue, actualValue);
		Display.destroy();
	}
	/**
	 * THis method tests Selling of Bomb Tower
	 */
	@Test
	public void testBombSell() 
	{
		int originalMoney = Controller.money;
		int towerPrice = t2.getPrice();
		int expectedValue = originalMoney + towerPrice;
		
		t2.sell();
		int actualValue = Controller.money;
		assertEquals(expectedValue, actualValue);
		Display.destroy();
	}
	/**
	 * THis method tests Strategy of Bomb Tower
	 */
	@Test
	public void testBombSetStrategy() 
	{
		assertEquals("bomb tower",Tower.setStrategy("bomb tower", 0, 0, ShootStrategyEnum.closestCritter));
		Display.destroy();
	}
	/**
	 * THis method tests Strategy of Freez Tower
	 */
	@Test
	public void testFreezSetStrategy() 
	{
		assertEquals("freez tower",Tower.setStrategy("freez tower", 0, 0, ShootStrategyEnum.closestCritter));
		Display.destroy();
	}
	/**
	 * THis method tests Strategy of Cannon Tower
	 */
	@Test
	public void testCannonSetStrategy() 
	{
		assertEquals("cannon tower",Tower.setStrategy("cannon tower", 0, 0, ShootStrategyEnum.closestCritter));
		Display.destroy();
	}
	/**
	 * THis method tests Effect of Cannon Tower
	 */
	@Test
	public void testCannonEffect() 
	{
		assertEquals(EffectType.cannon,t3.getTowerEffectType());
		Display.destroy();
	}
	/**
	 * THis method tests Effect of Bomb Tower
	 */
	@Test
	public void testBombEffect() 
	{
		assertEquals(EffectType.bomb,t2.getTowerEffectType());
		Display.destroy();
	}
	/**
	 * THis method tests Effect of Freez Tower
	 */
	@Test
	public void testFreezEffect() 
	{
		assertEquals(EffectType.freeze,t1.getTowerEffectType());
		Display.destroy();
	}
	/**
	 * THis method tests Damage update of Freez Tower
	 */
	@Test
	public void testFreezUpgrade() 
	{
		assertEquals(true,t1.upgrade());
		Display.destroy();
	}
	/**
	 * THis method tests Damage update of Cannon Tower
	 */
	@Test
	public void testCannonUpgrade() 
	{
		assertEquals(true,t3.upgrade());
		Display.destroy();
	}
	/**
	 * THis method tests Damage update of Bomb Tower
	 */
	@Test
	public void testBombUpgrade() 
	{
		assertEquals(true,t2.upgrade());
		Display.destroy();
	}
	/**
	 * THis method tests Range update of Freez Tower
	 */
	@Test
	public void testFreezUpgradeRange() 
	{
		assertEquals(true,t1.upgradeRange());
		Display.destroy();
	}
	/**
	 * THis method tests Range update of Cannon Tower
	 */
	@Test
	public void testCannonUpgradeRange() 
	{
		assertEquals(true,t3.upgradeRange());
		Display.destroy();
	}
	/**
	 * THis method tests Range update of Bomb Tower
	 */
	@Test
	public void testBombUpgradeRange() 
	{
		assertEquals(true,t2.upgradeRange());
		Display.destroy();
	}
}
