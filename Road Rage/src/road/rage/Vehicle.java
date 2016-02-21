package road.rage;

import java.awt.Graphics;

/**
 * Basic class that stores all the information pertaining to vehicles in the 
 * game
 * @author 02-1024-0008
 */
public abstract class Vehicle extends Entity{

    private int health, maxHealth;
    
    /**
     * Construct a vehicle with 0 health and 0 max health
     */
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
    
    /**
     * Construct a vehicle with a specified amount of health, specified amount
     * of maximum health, specified dimensions, specified location and specified
     * velocity.
     * @param x The x-coordinate of the location of the vehicle.
     * @param y The y-coordinate of the location of the vehicle.
     * @param xs The speed of the vehicle horizontally.
     * @param ys The speed of the vehicle vertically.
     * @param w The width of the vehicle.
     * @param l The length of the vehicle.
     * @param h The health of the vehicle.
     * @param mh The maximum possible value of the health of the vehicle.
     */
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
    
    /**
     * Return the current value of the vehicle's health
     * @return health, the variable that stores the current value of the
     * vehicle's health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Return the maximum potential health of the vehicle
     * @return maxHealth, the variable that stores the maximum value of the 
     * vehicle's health
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * Set the health to a specific value
     * @param h The value the health should be set to
     */
    public void setHealth(int h) {
        health = h;
    }
    
    /**
     * Set the maximum health to a specific value
     * @param h The value the maximum health should be set to
     */
    public void setMaxHealth(int h) {
        maxHealth = h;
    }
    
    @Override
    public abstract void draw(Graphics window); 
}
