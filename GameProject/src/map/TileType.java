package map;

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
