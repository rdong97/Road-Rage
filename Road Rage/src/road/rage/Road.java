/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;
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
        //remove at bottom of screen
        //spawn new debris once removed
    }
    public void removeEnemy() {
        //remove in checked collisions
    }
    
    public void checkCollisions() {
        //check enemy next locations, remove if hit
        //check hunter collisions, damage
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
