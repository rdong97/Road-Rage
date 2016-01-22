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
        super();
    }
    public Enemy(int x, int y, int w, int l, int xs, int ys, int h, int mh, int t) {
        super(x,y,w,l,xs,ys,h,mh);
        enemyType = t;
    }
    public int findXSpeed()//AI for calculating anticollision
    {
        //collision with debris
        //collision with user
        return 0;
    }
    
    @Override
    public void draw(Graphics window) {
        
    } 

    @Override
    public void findNextLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
