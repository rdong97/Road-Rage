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
    public int xCoordinate, yCoordinate, xWidth, yLength,xSpeed, ySpeed;
    
    public Entity() {
        xCoordinate = 0;
        yCoordinate = 0;
        xSpeed = 0;
        ySpeed = 0;
        xWidth = 0;
        yLength = 0;
    }
    public Entity(int x, int y, int xs, int ys, int w, int l) {
        xCoordinate = x;
        yCoordinate = y;
        xSpeed = xs;
        ySpeed = ys;
        xWidth = w;
        yLength = l;
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
        
        setXCoordinate(getXCoordinate()+getXSpeed());
        setYCoordinate(getYCoordinate()+getYSpeed());
    }
    @Override
    public abstract void draw(Graphics window);
}
