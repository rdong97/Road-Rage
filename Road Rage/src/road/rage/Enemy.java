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

    private int enemyType;
    private ArrayList<Debris>debrisList;
    
    public Enemy() {
        super();
        debrisList = new ArrayList<Debris>();
        enemyType = 1;
    }
    public Enemy(int x, int y, int w, int l, int xs, int ys, int h, int mh, int t) {
        super(x,y,w,l,xs,ys,h,mh);
        debrisList = new ArrayList<Debris>();
        enemyType = t;
        
    }
    public int findXSpeed(ArrayList<Debris>list)//AI for calculating anticollision
    {
        debrisList = list;
        Debris closest = list.get(0);
        for(Debris d:debrisList) {
            if(d.getYCoordinate()>closest.getYCoordinate()) {
                closest = d;
            }
        }
        if(!closest.isDebris()) {
            return 0;
        }
        else if(closest.getDebrisType()==2) {//left debris field
            Point trackPoint = new Point(200,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
            double xDistanceNeeded = trackPoint.getX()-(getXCoordinate()+(getXWidth()/2));
            double yDistanceNeeded = getYCoordinate() - trackPoint.getY();
            int speedNeeded =(int)(getYSpeed()*xDistanceNeeded/yDistanceNeeded);//slope: conversion from distance to speed
            return speedNeeded;
        }
        else if(closest.getDebrisType()==3) {//right debris field
            Point trackPoint = new Point(100,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
            double xDistanceNeeded = trackPoint.getX()-(getXCoordinate()+(getXWidth()/2));
            double yDistanceNeeded = getYCoordinate() - trackPoint.getY();
            int speedNeeded =(int)(getYSpeed()*xDistanceNeeded/yDistanceNeeded);//slope: conversion from distance to speed
            return speedNeeded;
        }
        else if(closest.getDebrisType()==4) {//middle debris field
            Point trackPoint = new Point(20,closest.getYCollisionCoordinate()+closest.getYDebrisLength());
            double xDistanceNeeded = trackPoint.getX()-(getXCoordinate()+(getXWidth()/2));
            double yDistanceNeeded = getYCoordinate() - trackPoint.getY();
            int speedNeeded =(int)(getYSpeed()*xDistanceNeeded/yDistanceNeeded);//slope: conversion from distance to speed
            return speedNeeded;
        }
        else {
            return 0;
        }
    }
    
    @Override
    public void draw(Graphics window) {
        int imageNum = 0;//will change based on skin
        int screenX = CoordinateConverter.xToScreenCoordinate(xCoordinate);
        int screenY = CoordinateConverter.yToScreenCoordinate(yCoordinate);
        window.drawImage(ImageManager.getImage(imageNum),screenX, screenY, null);
    }
}
