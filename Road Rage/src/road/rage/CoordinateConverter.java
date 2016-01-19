/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Richard
 */
public class CoordinateConverter {
    
    public Dimension screenSize;
    public static int screenXDimension, screenYDimension,xScale, yScale;
    
    public CoordinateConverter(){
    
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();//screen non-resizable
        screenXDimension = (int)screenSize.getWidth();
        screenYDimension = (int)screenSize.getHeight(); 
        xScale = screenXDimension/500;
        yScale = screenYDimension/300;
    }
    
    public static int screenToXCoordinate(int sX)
    {
        return sX/xScale;
    }
    public static int screenToYCoordinate(int cY, int sY)
    {
        return sY/yScale+cY;
    }
    
}
