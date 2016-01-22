/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Timer;

/**
 *
 * @author Richard
 */
public class HUD {
    
    private String profileName;
    private Timer timer;
    private double timeElapsed;
    private int score, ammo, maxAmmo, health, maxHealth;
    
    public HUD() {
        profileName = "Player 1";
        score = 0;
        ammo = 100;
        maxAmmo = 100;
        health = 100;
        maxHealth = 100;
    }
    public HUD(String n, int s, int a, int ma, int h, int mh) {
        profileName = n;
        score = s;
        ammo = a;
        maxAmmo = ma;
        health = h;
        maxHealth = mh;
    }
    
    public void startTimer() {     
        int delay = 100;
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               timeElapsed=timeElapsed+0.1;//counts in tenthes of a second.
            }
        };
        timer =new Timer(delay, task);
        timer.start();
    }
    public void liveUpdateHUD(int s, int a, int h) {
        score = s;
        ammo = a;
        health = h;
    }
    public void draw(Graphics window)
    {
        Font myFont=new Font("Impact",Font.PLAIN, 50);
        window.setColor(Color.white);
        window.setFont(myFont);
        
        //draw HUD skin
        
        String displayTime=timeElapsed+"";
            if(timeElapsed<10)
            {
                displayTime=displayTime.substring(0,3);
            }
            else if(timeElapsed<100)
            {
                displayTime=displayTime.substring(0,4);
            }
            else if(timeElapsed<1000)
            {
                displayTime=displayTime.substring(0,5);
            }
            else if(timeElapsed<10000)
            {
                displayTime=displayTime.substring(0,6);
            }
        //change window coordinates later!!!!!
        window.drawString("Time:", CoordinateConverter.xToScreenCoordinate(0),CoordinateConverter.yToScreenCoordinate(0));
        window.drawString("Score:", CoordinateConverter.xToScreenCoordinate(0),CoordinateConverter.yToScreenCoordinate(0));
        window.drawString("Health:", CoordinateConverter.xToScreenCoordinate(0),CoordinateConverter.yToScreenCoordinate(0));
        window.drawString("Ammo:", CoordinateConverter.xToScreenCoordinate(0),CoordinateConverter.yToScreenCoordinate(0));
        
        
        window.drawString(displayTime, CoordinateConverter.xToScreenCoordinate(0),CoordinateConverter.yToScreenCoordinate(0));
        window.drawString(score+"", CoordinateConverter.xToScreenCoordinate(0),CoordinateConverter.yToScreenCoordinate(0));
        window.drawString(health+"/"+maxHealth, CoordinateConverter.xToScreenCoordinate(0),CoordinateConverter.yToScreenCoordinate(0));
        window.drawString(ammo+"/"+maxAmmo, CoordinateConverter.xToScreenCoordinate(0),CoordinateConverter.yToScreenCoordinate(0));
    }
    
}
