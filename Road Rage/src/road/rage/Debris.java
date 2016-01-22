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
public class Debris extends Entity {

    private int fieldType,xCollisionCoordinate,yCollisionCoordinate,xDebrisWidth,yDebrisLength;
    private boolean isDebris;
    
    public Debris(int x, int y, int xs, int ys, int ft)
    {
        super(x,y,xs,ys,300,300);
        if(ft == 1)
        {
            xCollisionCoordinate = 0;
            yCollisionCoordinate = 0;
            xDebrisWidth = 0;
            yDebrisLength = 0;
            isDebris = false;
        }
        else if(ft == 2)
        {
            xCollisionCoordinate = 0;
            yCollisionCoordinate = y+80;
            xDebrisWidth = 100;
            yDebrisLength = 40;
            isDebris = true;
        }
        else if(ft == 3)
        {
            xCollisionCoordinate = 200;
            yCollisionCoordinate = y+80;
            xDebrisWidth = 100;
            yDebrisLength = 40;
            isDebris = true;
        }
        else if(ft == 4)
        {
            xCollisionCoordinate = 100;
            yCollisionCoordinate = y+800;
            xDebrisWidth = 100;
            yDebrisLength = 40;
            isDebris = true;
        }
    }
    public int getXCollisionCoordinate()
    {
        return xCollisionCoordinate;
    }
    public int getYCollisionCoordinate()
    {
        return yCollisionCoordinate;
    }
    public int getXDebrisWidth()
    {
        return xDebrisWidth;
    }
    public int getYDebrisLength()
    {
        return yDebrisLength;
    }
    public boolean isDebris()
    {
        return isDebris;
    }
    public int getDebrisType() {
        return fieldType;
    }
    @Override
    public void draw(Graphics window) {
        
    }
    /*fieldtypes
    1. ||||||||||
       ||||||||||
       ||||||||||
       ||||||||||
       ||||||||||
    
    2. ||||||||||
       ||||||||||
       0000||||||
       ||||||||||
       ||||||||||
    
    3. ||||||||||
       ||||||||||
       ||||||0000
       ||||||||||
       ||||||||||
    
    4. ||||||||||
       ||||||||||
       |||0000|||
       ||||||||||
       ||||||||||
    
    0 = debris
    | = nothing
    Each field is 300 wide (x)
    and 300 long (y)
    field coordinate refers to top left of screen
    debris coordinate refers to top left of hitbox
    */

}
