package road.rage;

/**
 * Class dedicated to any object on the road that moves relative to the screen
 * @author 02-1024-0008
 */
import java.awt.Graphics;

public abstract class Entity implements Location {
    public int xCoordinate, yCoordinate, xWidth, yLength, xSpeed, ySpeed;
    
    /***
     * Default entity
     */
    public Entity() {
        //default entity
        try {
            xCoordinate = 0;
            yCoordinate = 0;
            xSpeed = 0;
            ySpeed = 0;
            xWidth = 0;
            yLength = 0;
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize parent entity", ex);
        }
    }
    
    /**
     * Entity that spawns at a specified location with specified dimensions and 
     * velocity
     * @param x the x-coordinate of the location to spawn the entity
     * @param y the y-coordinate of the location to spawn the entity
     * @param xs the horizontal speed of the entity
     * @param ys the vertical speed of the entity
     * @param w the width of the entity
     * @param l the length of the entity
     */
    public Entity(int x, int y, int xs, int ys, int w, int l) {
        try {
            xCoordinate = x;
            yCoordinate = y;
            xSpeed = xs;
            ySpeed = ys;
            xWidth = w;
            yLength = l;
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize parent entity", ex);
        }
    }
    
    @Override
    public int getXCoordinate() {
        return xCoordinate;
    }
    @Override
    public int getYCoordinate() {
        return yCoordinate;
    }
    @Override
    public int getXWidth() {
        return xWidth;
    }
    @Override
    public int getYLength() {
        return yLength;
    }
    
    /**
     * Set the x-coordinate of the entity to a specified value
     * @param x the value to set the x-coordinate to 
     */
    public void setXCoordinate(int x) {
        xCoordinate = x;
    }
    
    /**
     * Set the y-coordinate of the entity to a specified value
     * @param y the value to set the y-coordinate to
     */
    public void setYCoordinate(int y) {
        yCoordinate = y;
    }
    
    /**
     * Return the horizontal speed of the entity
     * @return xSpeed which is the variable that stores the value of the entity's
     * horizontal speed
     */
    public int getXSpeed() {
        return xSpeed;
    }
    
    /**
     * Return the vertical speed
     * @return ySpeed which is the variable that stores the value of the entity's
     * vertical speed
     */
    public int getYSpeed() {
        return ySpeed;
    }
    
    /**
     * Set the horizontal speed to a specified value
     * @param x the value to set the horizontal speed to
     */
    public void setXSpeed(int x) {
        xSpeed = x;
    }
    
    /**
     * Set the vertical speed to a specified value
     * @param y the value to set the vertical speed to
     */
    public void setYSpeed(int y) {
        ySpeed = y;
    }
    @Override
    public void findNextLocation(){ 
        try {
            setXCoordinate(getXCoordinate()+getXSpeed());
            setYCoordinate(getYCoordinate()+getYSpeed());
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not calculate parent entity next location.", ex);
        }
    }
    @Override
    public abstract void draw(Graphics window);
}
