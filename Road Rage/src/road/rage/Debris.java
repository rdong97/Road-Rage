package road.rage;

import java.awt.Graphics;

/**
 *
 * @author 02-1024-0008
 */
public class Debris extends Entity {

    private int fieldType, xCollisionCoordinate, yCollisionCoordinate, xDebrisWidth, yDebrisLength;
    private boolean isDebris;
    
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
    public int getXCollisionCoordinate()
    {
        return xCollisionCoordinate;
    }
    public int getYCollisionCoordinate()
    {
        return yCollisionCoordinate;
    }
    public void setXCollisionCoordinate(int x)
    {
        xCollisionCoordinate = x;
    }
    public void setYCollisionCoordinate(int y)
    {
        yCollisionCoordinate = y;
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
