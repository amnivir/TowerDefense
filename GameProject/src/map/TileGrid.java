package map;
import static graphics.Designer.*;
import main.Boot;

public class TileGrid {
	int blockSize = 32;
	public Tile map[][];
	public TileGrid(int rows, int columns){
		
		map=new Tile[columns][rows];
		System.out.println(map.length);
		System.out.println(map[0].length);
		
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				map[i][j]=new Tile(i*blockSize, j*blockSize, blockSize, blockSize, TileType.Grass);
				
			}
		}
	}
	
	public TileGrid(int[][] newMap, int rows, int columns){
		
		map=new Tile[columns][rows];
		// for loop to check the static array values and set the corresponding tiles.
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				
				switch(newMap[j][i]){
					case 0:
						map[i][j]=new Tile(i*blockSize, j*blockSize, blockSize, blockSize, TileType.Grass);
						break;
					case 1:
						map[i][j]=new Tile(i*blockSize, j*blockSize, blockSize, blockSize, TileType.Dirt);
						break;
					case 2:
						map[i][j]=new Tile(i*blockSize, j*blockSize, blockSize, blockSize, TileType.Water);
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
		map[xCoord][yCoord]=new Tile(xCoord*blockSize, yCoord*blockSize, blockSize, blockSize, tile);
		
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
