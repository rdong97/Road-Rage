package road.rage;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Class dedicated to controlling the enemies
 * @author 02-1024-0008
 */
public class Enemy extends Vehicle {

    private ArrayList<Debris>debrisList;
    private int randomizer;
    
    /**
     * Create a generic enemy.
     */
    public Enemy() {
        super();
        try {
            debrisList = new ArrayList<>();
            randomizer = (int)(2*Math.random());//gives 0 or 1, to be used in random pathing assignment
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize enemy object.", ex);
        }
    }
    
    /**
     * Create an enemy with a specified location, velocity, health, maximum
     * health and dimensions
     * @param x the x-coordinate of the location of the enemy
     * @param y the y-coordinate of the location of the enemy
     * @param xs the horizontal speed of the enemy
     * @param ys the vertical speed of the enemy
     * @param w the width of the enemy
     * @param l the length of the enemy
     * @param h the health of the enemy
     * @param mh the maximum health of the enemy
     */
    public Enemy(int x, int y, int xs, int ys, int w, int l, int h, int mh) {
        super(x,y,xs,ys,w,l,h,mh);
        try {
            debrisList = new ArrayList<>();
            randomizer = (int)(2*Math.random());//gives 0 or 1, to be used in random pathing assignment
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize enemy object.", ex);
        }
    }
    
    /**
     * Find the horizontal speed that the enemy must take in order to continue to
     * chase the user
     * @param list A list containing the pertinent information about all debris 
     * on the map
     * @return The speed needed to take by the enemy in order to successfully chase
     * the user without crashing
     */
    public int findXSpeed(ArrayList<Debris>list)//AI for calculating anticollision
    {
        try {
            debrisList = list;
            Debris closest = list.get(list.size()-1);
            for(Debris d:debrisList) {
                if(d.isDebris()) {
                    if(d.getYCollisionCoordinate()<this.getYCoordinate()) {
                        if(d.getYCoordinate()>closest.getYCoordinate()) {
                        closest = d;
                        }
                    }
                }          
            }
            if(!closest.isDebris()) {
                return 0;
            }
            else {
                Point trackPoint = new Point(0,450);//default center of road

                if(closest.getDebrisType()==2) {//left debris field
                    if(randomizer==0) {
                        trackPoint.move(650,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
                    }
                    else if(randomizer==1) {
                        trackPoint.move(450,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
                    }
                }
                else if(closest.getDebrisType()==3) {//middle debris field
                    if(randomizer==0) {
                        trackPoint.move(250,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
                    }
                    else if(randomizer==1) {
                        trackPoint.move(650,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
                    }
                }
                else if(closest.getDebrisType()==4) {//right debris field
                    if(randomizer==0) {
                        trackPoint.move(250,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
                    }
                    else if(randomizer==1) {
                        trackPoint.move(450,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
                    }
                }
                double xDistanceNeeded = trackPoint.getX()-(getXCoordinate()+(getXWidth()/2));
                double yDistanceNeeded = getYCoordinate() - trackPoint.getY();
                double timeToMove = yDistanceNeeded/(getYSpeed()+6);
                int speedNeeded =(int)(xDistanceNeeded/timeToMove);
                //slope: conversion from distance to speed
                return speedNeeded;
            }
        }  
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not calculate enemy pathing speed", ex);
            return 0;//error in calculating speed.
        }
    }
    
    @Override
    /**
     * Draw the enemy in the given screen.
     * @param window The window in which to draw the enemy
     */
    public void draw(Graphics window) {
        try {
            int imageNum = 10;//will change based on skin
            window.drawImage(ImageManager.getImage(imageNum), getXCoordinate(), getYCoordinate(), getXWidth(), getYLength(), null);
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not draw enemy object.", ex);
        }
    }
}
