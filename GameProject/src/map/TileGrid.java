package map;
import static graphics.Designer.*;
import main.Boot;

public class TileGrid {

	public Tile map[][];
	public TileGrid(int rows, int columns){
		
		map=new Tile[columns][rows];
		System.out.println(map.length);
		System.out.println(map[0].length);
		
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				map[i][j]=new Tile(i*64, j*64, 64, 64, TileType.Grass);
				
			}
		}
	}
	
	public TileGrid(int[][] newMap, int rows, int columns){
		
		map=new Tile[columns][rows];
		System.out.println(map.length);
		System.out.println(map[0].length);
		
		// for loop to check the static array values and set the corresponding tiles.
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				
				switch(newMap[j][i]){
					case 0:
						map[i][j]=new Tile(i*64, j*64, 64, 64, TileType.Grass);
						break;
					case 1:
						map[i][j]=new Tile(i*64, j*64, 64, 64, TileType.Dirt);
						break;
					case 2:
						map[i][j]=new Tile(i*64, j*64, 64, 64, TileType.Water);
						break;	
				}
				
			}
		}
	}
	/**
	 * This method to set a tile at a particular position 
	 * <p>
	 * @param  xCoord  X coordinate of the tile 
	 * @param  yCoord  Y coordinate of the tile
	 * @return void
	 */
	public void setTile(int xCoord, int yCoord, TileType tile){
		map[xCoord][yCoord]=new Tile(xCoord*64, yCoord*64, 64, 64, tile);
		
	}
	/**
	 * This method to set a tile at a particular position 
	 * <p>
	 * @param  xCoord  X coordinate of the tile 
	 * @param  yCoord  Y coordinate of the tile
	 * @return map object at (xCoord, yCoord) coordinate
	 */
	public Tile getTile(int xCoord, int yCoord)	{
		
		return map[xCoord][yCoord];
	}
	
	public void draw(){
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
			Tile tile=map[i][j];
			drawQuadTex(tile.getTexture(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
			}
		}
	}
}
