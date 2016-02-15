package road.rage;

import java.awt.Graphics;

/**
 *
 * @author 02-1024-0008
 */
public abstract class Vehicle extends Entity{

    private int health, maxHealth;
    public Vehicle() {
        super();
        try {
            health = 0;
            maxHealth = 0;
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize vehicle.", ex);
        }           
    }
    public Vehicle(int x, int y, int xs, int ys, int w, int l, int h, int mh) {
    
        super(x,y,xs,ys,w,l);
        try {
            health = h;
            maxHealth = mh;
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize vehicle.", ex);
        } 
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
