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

    private int xSpeed, ySpeed, health, maxHealth;
    public Vehicle() {
        super();
        xSpeed = 0;
        ySpeed = 0;
        health = 0;
        maxHealth = 0;        
    }
    public Vehicle(int x, int y, int w, int l, int xs, int ys, int h, int mh) {
    
        super(x,y,w,l);
        xSpeed = xs;
        ySpeed = ys;
        
        health = h;//health
        maxHealth = mh;
        
    }
    @Override
    public abstract void draw(Graphics window);
    
}
