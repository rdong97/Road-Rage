package road.rage;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author 02-1024-0008
 */
public class Road {
    
    private Hunter hunter;
    private ArrayList<Debris>debrisList;
    private ArrayList<Enemy>enemyList;
    private Timer enemySpawnTimer;
    private int spawnTime;
    
    public Road(PlayerProfile p) {
        try {
            hunter = new Hunter(p.getScore(),p.getMaxHealth(),p.getMaxAmmo());  
            hunter.startIncrementalTimers();
            debrisList = new ArrayList<>();
            enemyList = new ArrayList<>();
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Unable to initialize road objects", ex);
        }
    }
    public void spawnInitialDebris() {
        try {
            debrisList.add(new Debris(0,-900,0,3,1));//initial blank debris fields
            debrisList.add(new Debris(0,-450,0,3,1));
            debrisList.add(new Debris(0,0,0,3,1));
            debrisList.add(new Debris(0,450,0,3,1));
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Unable to spawn initial debris.", ex);
        }
    }
    public void spawnNewDebris() {
        try {
            int randomType = (int)(Math.random()*4+1); //random number between 1 and 4
            debrisList.add(new Debris(0,-900,0,3,randomType));
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Unable to spawn new random debris.", ex);
        }
    }
    public void removeDebris() {
        try {
            Debris toRemove = null;
            for(Debris d:debrisList) {
                if(d.getYCoordinate()>900) {
                    toRemove = d;
                }
            }
            if(toRemove!=null){
                debrisList.remove(toRemove);
            spawnNewDebris();
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Unable to remove derbis", ex);
        }
    }
    
    public void setEnemySpawnSpeed() {
        try {
            spawnTime = 5000; //original 5000 millisecond (5 second) spawn time
            //reduction based on increase in player skill, aka: score, health, ammo
            spawnTime=spawnTime-hunter.getScore()-hunter.getMaxHealth()*10-hunter.getMaxAmmo()*10;
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError(null, ex);
        }
        
    }
    public void startSpawnEnemy() {
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debris closest =debrisList.get(0);
                for(Debris d:debrisList) {
                    if(closest.getYCoordinate()>d.getYCoordinate()) {
                        closest=d;
                    }
                } 
                //spawn location based on closest debris type to avoid collision
                if(closest.getDebrisType()==1) {
                    enemyList.add(new Enemy(425, 1200, 0, -1, 50, 70, 120, 100));
                }
                else if(closest.getDebrisType()==2) {
                    enemyList.add(new Enemy(530, 1200, 0, -1, 50, 70, 120, 100));
                }
                else if(closest.getDebrisType()==3) {
                    enemyList.add(new Enemy(200, 1200, 0, -1, 50, 70, 120, 100));
                }
                else if(closest.getDebrisType()==4) {
                    enemyList.add(new Enemy(230, 1200, 0, -1, 50, 70, 120, 100));
                }
                setEnemySpawnSpeed();//recheck spawn speed with each spawn
            }
        };
        try {
            enemySpawnTimer = new Timer(spawnTime, timerListener);
            enemySpawnTimer.start();
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not start enemy spawn timer.", ex);
        }
    }
    
    public void removeEnemy(Enemy toMatch) {
        try {
            Enemy toRemove = null;
            for(Enemy e:enemyList) {
                if(e.equals(toMatch)) {
                    toRemove = e;
                }
            }
            if(toRemove!=null) {
                enemyList.remove(toRemove);
                hunter.setScore(hunter.getScore()+10);
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not remove selected enemy", ex);
        }
    }
    
    public boolean hasGameEnded() {
        try {
            for(Enemy e:enemyList) {
                if(e.getYCoordinate()-e.getYLength()<0) {
                    return true; //out of range, objective lost
                }   
            }
            if(hunter.getHealth()<0) {
                return true; //hunter has died
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError(null, ex);
        }
        return false;//default
    }
    public void checkCollisions() {
        //establish points for Hunter hitbox
        ArrayList<Point>hunterDamagePoints = new ArrayList<>();
        try {
            hunterDamagePoints.add(new Point(hunter.getXCoordinate(), hunter.getYCoordinate()));
            hunterDamagePoints.add(new Point(hunter.getXCoordinate()+hunter.getXWidth(), hunter.getYCoordinate()));
            hunterDamagePoints.add(new Point(hunter.getXCoordinate(), hunter.getYCoordinate()+hunter.getYLength()));
            hunterDamagePoints.add(new Point(hunter.getXCoordinate(), hunter.getYCoordinate()+hunter.getYLength()/2));
            hunterDamagePoints.add(new Point(hunter.getXCoordinate()+hunter.getXWidth(), hunter.getYCoordinate()+hunter.getYLength()/2));
            hunterDamagePoints.add(new Point(hunter.getXCoordinate()+hunter.getXWidth(), hunter.getYCoordinate()+hunter.getYLength()));
            hunterDamagePoints.add(new Point(hunter.getXCoordinate()+hunter.getXWidth()/2, hunter.getYCoordinate()));
            /*
            Hunter Damage Point Locations (denoted by '*')

            *---*---*
            |       |
            |       |
            *       *
            |       |
            |       |
            *-------*

            note the extra damage caused by a head on collision
            */
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Unable to create list of hunter damage points.", ex);
        }

        //Check collision with debris
        //If hitpoint is inside debris, register damage. S
        //Speed is low enough so damage must occur.
        try {
            for(Debris d:debrisList){
                if(d.isDebris()) {
                    for(Point p:hunterDamagePoints) {
                        if(p.getX()>d.getXCollisionCoordinate()&&p.getX()<d.getXCollisionCoordinate()+d.getXDebrisWidth()) {
                            if(p.getY()>d.getYCollisionCoordinate()&&p.getY()<d.getYCollisionCoordinate()+d.getYDebrisLength()) {
                                hunter.setHealth(hunter.getHealth()-5);
                            }                
                        }
                    }  
                }
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not check collisions with debris.", ex);
        }
        
        //Due to staggered enemy locations, only a max of 2 enemies can be collided with hunter at the same time
        //Check for collisions with enemies
        //Due to pathing AI, enemies cannot hit debris and will steer away
        //Due to staggered spawning, enemies cannot collide with each other
        try {
            Enemy toRemove1 = null;
            Enemy toRemove2 = null;
            for(Enemy e:enemyList) {
                for(Point p:hunterDamagePoints) {
                    if(p.getX()>e.getXCoordinate()&&p.getX()<e.getXCoordinate()+e.getXWidth()) {
                        if(p.getY()>e.getYCoordinate()&&p.getY()<e.getYCoordinate()+e.getYLength()) {
                            hunter.setHealth(hunter.getHealth()-5);
                            if(toRemove1 == null) {
                                toRemove1 = e;//first slot for enemy to be removed
                            }
                            else {
                                toRemove2 = e; //both enemies need to be removed, two slots
                            }   
                        }                
                    }
                }
            }
            if(toRemove1!=null) {
                removeEnemy(toRemove1);
            }
            if(toRemove2!=null) {
                removeEnemy(toRemove2);
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not check collisions with enemies.", ex);
        }
        
        //Check collision with hunter bullet, or bullet inside enemy hitbox
        try {
            Enemy receivedHit = null;
            if(GameRunner.gunShot==true&&hunter.getAmmo()>0) {
                hunter.setAmmo(hunter.getAmmo()-1);
                for(Enemy e:enemyList) {
                    if(GameRunner.hitPoint.x<e.getXCoordinate()+e.getXWidth()&&GameRunner.hitPoint.x>e.getXCoordinate()) {
                       if(GameRunner.hitPoint.y<e.getXCoordinate()+e.getYLength()&&GameRunner.hitPoint.y>e.getYCoordinate()) {
                           receivedHit = e; //only one enemy in location, jump out of loop
                       }
                    }
                }
            }
            if(receivedHit!=null) {
                removeEnemy(receivedHit);
            }
            GameRunner.gunShot = false; 
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not check collision between enemy and bullet.", ex);
        }
    }
    public void updateEntityLocations() {
        try {
            //update only if game is still running
            if(!hasGameEnded()) {
                removeDebris();//check to see if debris is out of play
                checkCollisions(); //check for collisions
                hunter.findXSpeed(); //find hunter speed based on keystrokes
                hunter.findNextLocation(); //calculate hunter next location
                for(Enemy e:enemyList) {
                    e.setXSpeed(e.findXSpeed(debrisList)); //enemy pathing AI based on debris
                    e.findNextLocation(); //calculate enemy next location
                }
                for(Debris d:debrisList) {
                    d.findNextLocation(); //calculate debris next location based on scrolling
                } 
            }     
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not make commands to move objects", ex);
        }
    }
    public ArrayList<Integer>getLiveStats() {
        try {
            ArrayList<Integer>stats = new ArrayList<Integer>();
            stats.add(hunter.getScore());
            stats.add(hunter.getHealth());
            stats.add(hunter.getMaxHealth());
            stats.add(hunter.getAmmo());
            stats.add(hunter.getMaxAmmo());
            return stats;
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not get stats of hunter vehicle.", ex);
            return null;
        } 
    }
    public void draw(Graphics window) {
        try {
            if(!hasGameEnded()){
                for(Debris d: debrisList) {
                d.draw(window);
                }
                for(Enemy e: enemyList) {
                    e.draw(window);
                }
                hunter.draw(window);  
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not draw road with objects", ex);
        }
    }
}
