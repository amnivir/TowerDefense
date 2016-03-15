package junit_test;

import java.awt.Font;
import java.util.Arrays;

import main.Boot;
import main.Player;
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
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		
		beginSession(5,5);
		t1=new TowerFactory().getTower("freez", Designer.quickTexture("freezBase"),new Tile(0, 0, 32, 32, TileType.TowerFreez));
		t2=new TowerFactory().getTower("bomb", Designer.quickTexture("bombBase"),new Tile(0, 0, 32, 32, TileType.TowerBomb));
		t3=new TowerFactory().getTower("cannon", Designer.quickTexture("cannonBase"),new Tile(0, 0, 32, 32, TileType.TowerCannon));
	//	System.out.println(t1.getStartTile());
		Player.money=100;
		
	}

	@Test
	public void testFreezFactory() {
		
			assertTrue(t1 instanceof TowerFreez);
			Display.destroy();
	}
	@Test
	public void testBombFactory() {
		
			assertTrue(t2 instanceof TowerBomb);
			Display.destroy();
	}
	@Test
	public void testCannonFactory() {
		
			assertTrue(t3 instanceof TowerCannon);
			Display.destroy();
		
	}
	@Test
	public void testCannonBuy() {
		
			assertTrue(t3.buy());
			Display.destroy();
		
	}
	@Test
	public void testBombBuy() {
		
			assertTrue(t2.buy());
			Display.destroy();
		
	}
	@Test
	public void testFreezBuy() {
		
			assertTrue(t1.buy());
			Display.destroy();
		
	}
	@Test
	public void testFreezDamage() {
		
			assertEquals(t1.getDamage(),30);
			Display.destroy();
		
	}
	@Test
	public void testBombDamage() {
		
			assertEquals(t2.getDamage(),20);
			Display.destroy();
		
	}
	@Test
	public void testCannonDamage() {
		
			assertEquals(t3.getDamage(),10);
			Display.destroy();
		
	}

}
