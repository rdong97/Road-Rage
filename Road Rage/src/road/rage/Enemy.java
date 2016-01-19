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
public class Enemy extends Vehicle{

    private int enemyType;
    
    public Enemy() {
        
    }
    public Enemy(int x, int y, int w, int l, int xs, int ys, int h, int mh, int t) {
        super(x,y,w,l,xs,ys,h,mh);
        enemyType = t;
    }
    @Override
    public void draw(Graphics window) {
        
    } 
}
