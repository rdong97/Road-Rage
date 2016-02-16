package road.rage;

import java.awt.Graphics;

/**
 * Controls the pattern of debris that occur in the road
 * @author 02-1024-0008
 */
public class Debris extends Entity {

    private int fieldType, xCollisionCoordinate, yCollisionCoordinate, xDebrisWidth, yDebrisLength;
    private boolean isDebris;
    
    /**
     * Generate a piece of debris in a certain location with certain dimensions
     * @param x the x-coordinate of the debris; the x-coordinate of the possible
     * collision point with the debris
     * @param y the y-coordinate of the debris; the y-coordinate of the possible
     * collision point with the debris
     * @param xs the relative speed at which the debris is moving in the x
     * direction (horizontally)
     * @param ys the relative speed at which the debris is moving in the y 
     * direction (vertically)
     * @param ft the field type of the debris: 
     * <p>1: No debris generated </p>
     * <p>2: Debris generated in the left side, middle of the screen</p>
     * <p>3: Debris generated in the center of the screen </p>
     * <p>4: Debris generated in the right side, middle of the screen</p>
     */
    public Debris(int x, int y, int xs, int ys, int ft)
    {
        super(x,y,xs,ys,900,450);
        try {
            fieldType = ft;
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
                yCollisionCoordinate = y+210;
                xDebrisWidth = 300;
                yDebrisLength = 30;
                isDebris = true;
            }
            else if(ft == 3)
            {
                xCollisionCoordinate = 300;
                yCollisionCoordinate = y+210;
                xDebrisWidth = 300;
                yDebrisLength = 30;
                isDebris = true;
            }
            else if(ft == 4)
            {
                xCollisionCoordinate = 600;
                yCollisionCoordinate = y+210;
                xDebrisWidth = 300;
                yDebrisLength = 30;
                isDebris = true;
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
               |||0000|||
               ||||||||||
               ||||||||||

            4. ||||||||||
               ||||||||||
               ||||||0000
               ||||||||||
               ||||||||||

            0 = debris
            | = nothing
            Each field is 900 wide (x)
            and 450 long (y)
            field coordinate refers to top left of screen
            debris coordinate refers to top left of hitbox
            */
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize debris.", ex);
        }
    }
    
    /**
     * Return the value of the x-coordinate of the debris
     * @return xCollisionCoordinate which is the variable that stores the value
     * of the x-coordinate of the debris
     */
    public int getXCollisionCoordinate()
    {
        return xCollisionCoordinate;
    }
    
    /**
     * Return the value of the y-coordinate of the debris
     * @return yCollisionCoordinate which is the variable the stores the value
     * of the y-coordinate of the debris
     */
    public int getYCollisionCoordinate()
    {
        return yCollisionCoordinate;
    }
    
    /**
     * Set the x-coordinate of the debris to a certain value
     * @param x The new value of the x-coordinate of the debris
     */
    public void setXCollisionCoordinate(int x)
    {
        xCollisionCoordinate = x;
    }
    
    /**
     * Set the y-coordinate of the debris to a certain value
     * @param y The new value of the y-coordinate of the debris
     */
    public void setYCollisionCoordinate(int y)
    {
        yCollisionCoordinate = y;
    }
    
    /**
     * Return the value of the width of the debris
     * @return xDebrisWidth which the variable that stores the value of the 
     * width of the debris
     */
    public int getXDebrisWidth()
    {
        return xDebrisWidth;
    }
    
    /**
     * Return the value of the length of the debris
     * @return yDebrisLength which is the variable that stores the value of the 
     * length of the debris
     */
    public int getYDebrisLength()
    {
        return yDebrisLength;
    }
    
    /**
     * Return a true or false value depending on whether there is actually 
     * debris on the road
     * @return isDebris which is true if there is a debris generated and false 
     * if not
     */
    public boolean isDebris()
    {
        return isDebris;
    }
    
    /**
     * Return the integer that corresponds to the type of debris that will be 
     * generated on the road:
     * <p>1: No debris generated </p>
     * <p>2: Debris generated in the left side, middle of the screen</p>
     * <p>3: Debris generated in the center of the screen </p>
     * <p>4: Debris generated in the right side, middle of the screen</p>
     * @return fieldType which the variable that stores the integer that 
     * corresponds to if and where a debris is generated
     */
    public int getDebrisType() {
        return fieldType;
    }
    
    /**
     * Update the values of the x- and y-coordinates based on the speed of the
     * debris
     */
    @Override
    public void findNextLocation() {
        try {
            setXCoordinate(getXCoordinate()+getXSpeed());
            setYCoordinate(getYCoordinate()+getYSpeed());

            setXCollisionCoordinate(getXCollisionCoordinate()+getXSpeed());
            setYCollisionCoordinate(getYCollisionCoordinate()+getYSpeed());
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not calculate debris next location.", ex);
        }       
    }
    /**
     * 
     * @param window 
     */
    @Override
    public void draw(Graphics window) {
        try {
            int imageNum;
            if(getDebrisType()==1) {
                imageNum = 8;
            }
            else if(getDebrisType()==2) {
                imageNum = 9;
            }
            else if(getDebrisType()==3) {
                imageNum = 10;
            }
            else if(getDebrisType()==4) {
                imageNum = 11;
            }
            else {
                imageNum = -1;
            }
            if(getYCoordinate()>-450) {
                window.drawImage(ImageManager.getImage(imageNum),getXCoordinate(), getYCoordinate(), getXWidth(),getYLength(), null);
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not draw debris.",ex);
        }   
    }
}