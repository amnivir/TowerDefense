package tower;
import org.newdawn.slick.opengl.Texture;

import map.Tile;
public class TowerFactory {

	//use getTower method to get object of type tower 
	   public Tower getTower(String towerType, Texture tex, Tile tile){
	      if(towerType == null){
	         return null;
	      }		
	      if(towerType.equalsIgnoreCase("cannon")){
	         return new TowerCannon(tex, tile);
	         
	      } 
	      
	      else if(towerType.equalsIgnoreCase("bomb")){
	    	  return new TowerBomb(tex, tile);
	         
	      } 
	      
	      return null;
	   }
}
