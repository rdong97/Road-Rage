/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class Enemy extends Vehicle{

    private ArrayList<Debris>debrisList;
    
    public Enemy() {
        super();
        debrisList = new ArrayList<Debris>();
    }
    public Enemy(int x, int y, int xs, int ys, int w, int l, int h, int mh) {
        super(x,y,xs,ys,w,l,h,mh);
        debrisList = new ArrayList<Debris>();
        
    }
    public int findXSpeed(ArrayList<Debris>list)//AI for calculating anticollision
    {
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
            Point trackPoint = new Point(0,0);
            if(closest.getDebrisType()==2) {//left debris field
            trackPoint.move(650,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
            }
            else if(closest.getDebrisType()==3) {//middle debris field
                trackPoint.move(250,closest.getYCollisionCoordinate()+closest.getYDebrisLength());

            }
            else if(closest.getDebrisType()==4) {//right debris field
                trackPoint.move(250,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
            }
            double xDistanceNeeded = trackPoint.getX()-(getXCoordinate()+(getXWidth()/2));
            double yDistanceNeeded = getYCoordinate() - trackPoint.getY();
            double timeToMove = yDistanceNeeded/(getYSpeed()+10);
            int speedNeeded =(int)(xDistanceNeeded/timeToMove);
            //slope: conversion from distance to speed
            return speedNeeded;
        }  
    }
    
    @Override
    public void draw(Graphics window) {
        int imageNum = 1;//will change based on skin
        window.drawImage(ImageManager.getImage(imageNum), getXCoordinate(), getYCoordinate(), getXWidth(), getYLength(), null);
    }
}
