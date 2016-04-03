package map;

/**
 * This class is enum and  defines the type of tiles 
 */
public enum TileType 
{
	Grass("grass",true),Dirt("dirt",false),Water("water",false),TowerCannon("cannonBase",false),
	TowerBomb("bombBase",false),PlayButton("playButton",false),StopButton("stopButton",false),TowerFreez("freezBase",false),
	Closest("closest",false), Strongest("strongest",false), Weakest("weakest",false), Nearest("nearest",false);

	public String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable)
	{
		this.textureName = textureName;
		this.buildable = buildable;
		
	}
}
