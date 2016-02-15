package road.rage;

/**
 *
 * @author 02-1024-0008
 */
import java.awt.Graphics;

public abstract class Entity implements Location {
    public int xCoordinate, yCoordinate, xWidth, yLength, xSpeed, ySpeed;
    
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
    public void setXCoordinate(int x) {
        xCoordinate = x;
    }
    public void setYCoordinate(int y) {
        yCoordinate = y;
    }
    public int getXSpeed() {
        return xSpeed;
    }
    public int getYSpeed() {
        return ySpeed;
    }
    public void setXSpeed(int x) {
        xSpeed = x;
    }
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
