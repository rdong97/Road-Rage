/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class Road {
    
    private Hunter hunter;
    private ArrayList<Debris>debrisList;
    private ArrayList<Enemy>enemyList;
    
    public Road() {
        hunter = new Hunter();  
        debrisList = new ArrayList<Debris>();
        //add starting default debris
        enemyList = new ArrayList<Enemy>();
    }
    public void spawnDebris() {
        //add random debris when one falls below 0-line
    }
    public void spawnEnemy() {
        //new enemy from list, calculate from hunter location
    }
    public void removeDebris() {
        Debris toRemove = null;
        for(Debris d:debrisList) {
            if(d.getXCoordinate()<=0) {
                toRemove = d;
            }
        }
        if(toRemove!=null)
        {
            debrisList.remove(toRemove);
        }
        spawnDebris();
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
        }
    }
    
    public void checkCollisions() {
        //establish points for Hunter hitbox
        ArrayList<Point>hunterDamagePoints = new ArrayList<Point>();
        hunterDamagePoints.add(new Point(hunter.getXCoordinate(), hunter.getYCoordinate()));
        hunterDamagePoints.add(new Point(hunter.getXCoordinate()+hunter.getXWidth(), hunter.getYCoordinate()));
        hunterDamagePoints.add(new Point(hunter.getXCoordinate(), hunter.getYCoordinate()+hunter.getYLength()));
        hunterDamagePoints.add(new Point(hunter.getXCoordinate(), hunter.getYCoordinate()+hunter.getYLength()+hunter.getYLength()));
        hunterDamagePoints.add(new Point(hunter.getXCoordinate()+hunter.getXWidth()/2, hunter.getYCoordinate()));
        /*
        Hunter Damage Point Locations (denoted by '*')
        
        *--*--*
        |     |
        |     |
        |     |
        |     |
        *-----*
        
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
        
        //Due to staggered enemy locations, only a max of 2 enemies can be hit at the same time
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
    }
    public void updateEntityLocations() {
        hunter.findNextLocation();
        for(Enemy e:enemyList) {
            e.findXSpeed(debrisList);
            e.findNextLocation();
        }
        for(Debris d:debrisList) {
            d.findNextLocation();
        }
    }
    
    public void draw(Graphics window) {
        hunter.draw(window);
        for(Debris d: debrisList) {
            d.draw(window);
        }
        for(Enemy e: enemyList) {
            e.draw(window);
        }
    }
}
