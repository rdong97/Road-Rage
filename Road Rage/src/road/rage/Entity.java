/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

/**
 *
 * @author Richard
 */
import java.awt.Graphics;

public abstract class Entity implements Location{
    public int xCoordinate, yCoordinate, xWidth, yLength;
    
    public Entity() {
        xCoordinate = 0;
        yCoordinate = 0;
        xWidth = 0;
        yLength = 0;
    }
    public Entity(int x, int y, int w, int l) {
        xCoordinate = x;
        yCoordinate = y;
        xWidth = w;
        yLength = l;
    }
    
    public int getXCoordinate() {
        return xCoordinate;
    }
    public int getYCoordinate() {
        return yCoordinate;
    }
    public int getXWidth() {
        return xWidth;
    }
    public int getYLength() {
        return yLength;
    }
    public void setXCoordinate(int x) {
        xCoordinate = x;
    }
    public void setYCoordinate(int y) {
        yCoordinate = y;
    }
    public abstract void draw(Graphics window);
}
