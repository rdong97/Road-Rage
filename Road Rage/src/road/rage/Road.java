/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Richard
 */
public class Road {
    
    private final Hunter hunter;
    private final ArrayList<Debris>debrisList;
    private final ArrayList<Enemy>enemyList;
    private Timer enemySpawnTimer;
    private int spawnTime;
    
    public Road(PlayerProfile p) {
        hunter = new Hunter(p.getScore(),p.getMaxHealth(),p.getMaxAmmo());  
        hunter.startIncrementalTimers();
        debrisList = new ArrayList<>();
        debrisList.add(new Debris(0,-900,0,3,1));
        debrisList.add(new Debris(0,-450,0,3,1));
        debrisList.add(new Debris(0,0,0,3,1));
        debrisList.add(new Debris(0,450,0,3,1));
        enemyList = new ArrayList<>();
        setSpawnSpeed();
        startSpawnEnemy();
    }
    public void spawnDebris() {
        
        int randomType = (int)(Math.random()*4+1);
        debrisList.add(new Debris(0,-900,0,3,randomType));
    }
    private void setSpawnSpeed() {
        spawnTime = 5000;
        spawnTime=spawnTime-hunter.getScore()-hunter.getMaxHealth()*10-hunter.getMaxAmmo()*10;
    }
    private void startSpawnEnemy() {
        
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Debris closest =debrisList.get(0);
                for(Debris d:debrisList) {
                    if(closest.getYCoordinate()>d.getYCoordinate()) {
                        closest=d;
                    }
                }
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
                setSpawnSpeed();
            }
        };
        enemySpawnTimer = new Timer(spawnTime, timerListener);

        enemySpawnTimer.start();
    }
    public boolean hasGameEnded() {
        for(Enemy e:enemyList) {
            if(e.getYCoordinate()-e.getYLength()<0) {
                return true;
            }
        }
        if(hunter.getHealth()<0) {
            return true;
        }
        return false;
    }
    
    public void removeDebris() {
        Debris toRemove = null;
        for(Debris d:debrisList) {
            if(d.getYCoordinate()>900) {
                toRemove = d;
            }
        }
        if(toRemove!=null)
        {
            debrisList.remove(toRemove);
            spawnDebris();
        } 
    }
    public void removeEnemy(Enemy toMatch) {
        Enemy toRemove = null;
        for(Enemy e:enemyList) {
            if(e.equals(toMatch)) {
                toRemove = e;
            }
        }
        if(toRemove!=null)
        {
            enemyList.remove(toRemove);
            hunter.setScore(hunter.getScore()+10);
        }
    }
    
    public void checkCollisions() {
        //establish points for Hunter hitbox
        ArrayList<Point>hunterDamagePoints = new ArrayList<Point>();
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
        //check collision with debris
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
        
        //Due to staggered enemy locations, only a max of 2 enemies can be collided with hunter at the same time
        Enemy toRemove1 = null;
        Enemy toRemove2 = null;
        for(Enemy e:enemyList) {
            for(Point p:hunterDamagePoints) {
                if(p.getX()>e.getXCoordinate()&&p.getX()<e.getXCoordinate()+e.getXWidth()) {
                    if(p.getY()>e.getYCoordinate()&&p.getY()<e.getYCoordinate()+e.getYLength()) {
                        hunter.setHealth(hunter.getHealth()-5);
                        if(toRemove1 == null) {
                            toRemove1 = e;
                        }
                        else {
                            toRemove2 = e; //both enemies need to be removed, two slots
                        }   
                    }                
                }
            }
        }
        
        //Due to pathing AI, enemies cannot hit debris and will steer away
        //Due to staggered spawning, enemies cannot collide with each other
        if(toRemove1!=null)
        {
            removeEnemy(toRemove1);
        }
        if(toRemove2!=null)
        {
            removeEnemy(toRemove2);
        }
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
    public void updateEntityLocations() {
        if(!hasGameEnded()) {
           removeDebris();
            checkCollisions();
            hunter.findXSpeed();
            hunter.findNextLocation();
            for(Enemy e:enemyList) {
                e.setXSpeed(e.findXSpeed(debrisList));
                e.findNextLocation();
            }
            for(Debris d:debrisList) {
                d.findNextLocation();
            } 
        }     
    }
    public ArrayList<Integer>getLiveStats() {
        ArrayList<Integer>stats = new ArrayList<Integer>();
        stats.add(hunter.getScore());
        stats.add(hunter.getHealth());
        stats.add(hunter.getMaxHealth());
        stats.add(hunter.getAmmo());
        stats.add(hunter.getMaxAmmo());
        return stats;
    }
    public void draw(Graphics window) {
        
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
}
