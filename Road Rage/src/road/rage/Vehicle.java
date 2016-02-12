/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;

/**
 *
 * @author Richard
 */
public abstract class Vehicle extends Entity{

    private int health, maxHealth;
    public Vehicle() {
        super();
        health = 0;
        maxHealth = 0;        
    }
    public Vehicle(int x, int y, int xs, int ys, int w, int l, int h, int mh) {
    
        super(x,y,xs,ys,w,l);
        
        health = h;//health
        maxHealth = mh;
        
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public void setHealth(int h) {
        health = h;
    }
    public void setMaxHealth(int h) {
        maxHealth = h;
    }
    
    @Override
    public abstract void draw(Graphics window); 
}
