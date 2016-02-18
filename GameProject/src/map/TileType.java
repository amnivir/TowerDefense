package map;

/**
 * This class is enum and  defines the type of tiles 
 */
public enum TileType {
	Grass("grass",true),Dirt("dirt",false),Water("water",false),TowerCannon("cannonBase",false),TowerBomb("bombBase",false);

	public String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable)
	{
		this.textureName=textureName;
		this.buildable=buildable;
		
	}
}
