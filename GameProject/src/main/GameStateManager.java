package main;

/**
 * THis class maintains the state of the Game
 */
public class GameStateManager{
	
	private static GameState gameState;
	
	/**
	 * This getter function returns the game State
	 * @return GameState 
	 */
	public static GameState getGameState() {
		return gameState;
	}
	/**
	 * This constructor sets the initial state of the game 
	 */
	public GameStateManager()
	{
		gameState=GameState.START;
	}
	
	public static int setGameState(String state)
	{
		gameState=GameState.valueOf(state);
		return gameState.ordinal();
	}
	/**
	 * This ENUM defines the possible state of the game
	 */
	public enum GameState{
		START,EDIT,PLAY,END
	};
			
}