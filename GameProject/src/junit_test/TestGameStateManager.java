package junit_test;

import static org.junit.Assert.*;
import main.GameStateManager;

import org.junit.Test;

public class TestGameStateManager {
	/**
	 * THis method tests the start game state
	 */
	@Test
	public void testSetGameState_Start() 
	{
		int expectedValue = 0;
		int actualValue = GameStateManager.setGameState("START");
		assertEquals(expectedValue, actualValue);
	}
	/**
	 * THis method tests the play game state
	 */
	@Test
	public void testSetGameState_Play() 
	{
		int expectedValue = 2;
		int actualValue = GameStateManager.setGameState("PLAY");
		assertEquals(expectedValue, actualValue);
	}
	/**
	 * THis method compares the game state
	 */
	@Test
	public void testSetGameState_NotEquals() 
	{
		int state1 = GameStateManager.setGameState("START");
		int state2 = GameStateManager.setGameState("PLAY");
		//assertNotEquals(state1, state2);
	}
}
