package main;
import static helpers.Designer.*;

public class TileGrid {

	public Tile map[][];
	public TileGrid(){
		map=new Tile[20][15];
		
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				map[i][j]=new Tile(i*64, j*64, 64, 64, TileType.Grass);
				
			}
		}
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
