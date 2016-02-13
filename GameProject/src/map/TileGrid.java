package map;
import static graphics.Designer.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.opengl.Texture;

import ai.Path;
import main.Boot;

public class TileGrid {
	int blockSize = 32;
	public static Tile map[][];
	public static ArrayList<Integer> pathCordinate = new ArrayList<>();
	public TileGrid(int rows, int columns){

		map=new Tile[columns][rows];
		//create path corrdinate list


		System.out.println(map.length);
		System.out.println(map[0].length);

		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				System.out.println((i) +" " +(j));
				map[i][j]=new Tile(i*blockSize, j*blockSize, blockSize, blockSize, TileType.Grass);

			}
		}
	}
	/**
	 * This method is creates the tile for the first time and sets the texture of the tile.
	 * This method should not be used again. 
	 * @param newMap
	 * @param rows
	 * @param columns
	 */
	public TileGrid(int[][] newMap, int rows, int columns){

		map=new Tile[columns][rows];
		// for loop to check the static array values and set the corresponding tiles.
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){

				switch(newMap[j][i]){
				case 0:
					System.out.println((i) +" " +(j));
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
	 * This method to set a tile to dirt or grass at a particular position 
	 * <p>
	 * @param  xCoord  X coordinate of the tile 
	 * @param  yCoord  Y coordinate of the tile
	 * @return void
	 */
	public void setTile(int xCoord, int yCoord, TileType tile){
		//map[xCoord][yCoord]=new Tile(xCoord*blockSize, yCoord*blockSize, blockSize, blockSize, tile);
		//System.out.println("Before "+map[xCoord][yCoord].getType().ordinal());

		int xyCoord = yCoord*Boot.getNoRows()+xCoord;//may be x&y needs to be interchanged
		System.out.println(xyCoord);
		System.out.println(pathCordinate);
		if(!pathCordinate.contains(xyCoord) && map[xCoord][yCoord].getType()==TileType.Grass)
		{
			pathCordinate.add(xyCoord);
			map[xCoord][yCoord].setType(tile);
			map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
		}
		else if(pathCordinate.contains(xyCoord) && map[xCoord][yCoord].getType()==TileType.Dirt)
		{
			Iterator<Integer> iter = pathCordinate.iterator();

			while (iter.hasNext()) {
			   Integer num = iter.next();
			    if (num==xyCoord)
			        iter.remove();
			}

			map[xCoord][yCoord].setType(tile);
			map[xCoord][yCoord].setTexture(quickTexture(tile.textureName));
			System.out.println("DIRT");
		}
		System.out.println(Path.isPathValid().name());
		
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
