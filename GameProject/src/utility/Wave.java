/**
 * 
 */
package utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import critter.Critter;
import critter.CritterFactory;
import main.View;
import main.GameStateManager;
import main.GameStateManager.GameState;
import tower.Tower;
import tower.TowerBomb;
import tower.TowerCannon;
import tower.TowerFreez;
import tower.TowerNotification;
import main.Controller;

/**
 * THis class generates the wave of critter and shoots them. 
 * @author eshinig
 *
 */
public class Wave extends Observable
{
    private static float timeLastSpawn, spawnTime;
    private String critterType;
    private static ArrayList<Critter> critterList;
    private static int numofCrittersInWave = 5;
    private static int critterCounter = 0;	//No more than 3 counter in the wave
    public TowerNotification test;
    private int numOfCrittersCreated = 0; // num of critters actually created in the wave
    public static int numWavesCompleted = 0; // num of waves completed

    public Wave(float spawnTime, String critterType)
    {
        this.critterType = critterType;
        this.spawnTime = spawnTime;
        this.timeLastSpawn = 50;
        this.critterList = new ArrayList<Critter>();
        this.test = new TowerNotification();
    }

	/**
     * This method updates and draws the critter on the map 
     */
    public void update()
    {
    	setChanged();
    	notifyObservers(this);

		if(Clock.delta() < 1 && Clock.delta() > 0)
        {
		    
            timeLastSpawn= timeLastSpawn + Clock.delta();
        }

        if(timeLastSpawn > spawnTime)
        {
            this.Spawn();
            Log.addLogMessage(this.getClass().getSimpleName().toString(),"New Crritter created");
            timeLastSpawn = 0;
        }
	
    
        if(critterList.size() <= numofCrittersInWave)
        {	
            Iterator<Critter> iter = critterList.iterator();

            //Remove the critter if reached endpoint
            while (iter.hasNext())
            {
                Critter critter = iter.next();
                if (!critter.isCriterAlive)
                {
                    iter.remove();
                    if(Controller.money < 0)
                    {
                        GameStateManager.setGameState("END");
                        Log.addLogMessage(this.getClass().getSimpleName().toString(),"Game Ends");
                        System.out.println("No Money left , Game END! " + GameStateManager.getGameState());
                        break;
                    }

                    if(critterList.size()==0 && numOfCrittersCreated >= numofCrittersInWave)
                    {
                        Log.addLogMessage(this.getClass().getSimpleName().toString(),"All Critters Killed");
                        GameStateManager.setGameState("IDLE");
                        System.out.println("Game state changed to IDLE");
                        numOfCrittersCreated = 0;
                        numofCrittersInWave++;
                        numWavesCompleted++;
                        
                    }
                }
                else
                {
                    critter.update();
                    critter.draw();
                }
            }
        }
    }

    /**
     * THis method creates new Critter using factory design pattern
     */
    private void Spawn()
    {
        if(critterCounter < numofCrittersInWave)
        {
            critterList.add(CritterFactory.getCritter(critterType));
            critterCounter++;
            numOfCrittersCreated++;
            //System.out.println("CritterList SIze=="+critterList.size() + " Number of Critters created="+critterCounter);
        }
    }

    /**
     * Reset Critter counter to zero to enable or create another wave 
     */
    public static int resetCritterCounter()
    {
        critterCounter = 0;
        return critterCounter;

    }

    /**
     * getter of critter list 
     */
    public static ArrayList<Critter> getCritterList()
    {
        return critterList;
    }
}
