package road.rage;

import java.awt.Graphics;

/**
 * Class dedicated to locations of objects
 * @author 02-1024-0008
 */
public interface Location 
{
    /**
     * Returns the x-coordinate of the location of the object.
     * @return an integer that represents the x-coordinate of the object.
     */
    public int getXCoordinate();
    
    /**
     * Returns the y-coordinate of the location of the object.
     * @return an integer that represents the y-coordinate of the object.
     */
    public int getYCoordinate();
    
    
    /**
     * Returns the vertical width of the size of the object.
     * @return an integer that represents the x-coordinate of the object.
     */
    public int getXWidth();
    
    /**
     * Returns the horizontal length of the size of the object.
     * @return an integer that represents the x-coordinate of the object.
     */
    public int getYLength();
    
    /**
     * Draws the object in the specified Graphics context.
     * @param window the specified Graphics context.
     */
    public void draw(Graphics window);
    
    /**
     * Calculate the next location of the object.
     */
    public void findNextLocation();
}