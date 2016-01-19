/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;

public interface Location 
{
    /**
     * Returns the x-coordinate of the location of the object.
     * @return an integer that represents the x-coordinate of the object.
     * @author Richard Dong
     */
    public int getXCoordinate();
    
    /**
     * Returns the y-coordinate of the location of the object.
     * @return an integer that represents the y-coordinate of the object.
     * @author Richard Dong
     */
    public int getYCoordinate();
    
    
    /**
     * Returns the vertical width of the size of the object.
     * @return an integer that represents the x-coordinate of the object.
     * @author Richard Dong
     */
    public int getXWidth();
    
    /**
     * Returns the horizontal length of the size of the object.
     * @return an integer that represents the x-coordinate of the object.
     * @author Richard Dong
     */
    public int getYLength();
    
    /**
     * Draws the object in the specified Graphics context.
     * @param window the specified Graphics context.
     * @author Richard Dong
     */
    public void draw(Graphics window);
}
