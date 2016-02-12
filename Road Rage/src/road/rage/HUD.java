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
import java.util.ArrayList;
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
        profileName = "Default Player";
        score = 0;
        ammo = 100;
        maxAmmo = 100;
        health = 100;
        maxHealth = 100;
    }
    public HUD(String n, int s, int a, int h) {
        profileName = n;
        score = s;
        ammo = a;
        maxAmmo = a;
        health = h;
        maxHealth = h;
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
    public void liveUpdateHUD(ArrayList<Integer>stats) {
        score = stats.get(0);
        health = stats.get(1);
        maxHealth = stats.get(2);
        ammo = stats.get(3);
        maxAmmo = stats.get(4);
    }
    public void draw(Graphics window) {
        Font myFont=new Font("Impact",Font.PLAIN, 20);
        window.setColor(Color.white);
        window.setFont(myFont);
        
        window.drawImage(ImageManager.getImage(0), 900, 0, 300, 900, null);
        
        String displayTime=timeElapsed+"";
            if(timeElapsed<10) {
                displayTime=displayTime.substring(0,3);
            }
            else if(timeElapsed<100) {
                displayTime=displayTime.substring(0,4);
            }
            else if(timeElapsed<1000) {
                displayTime=displayTime.substring(0,5);
            }
            else if(timeElapsed<10000) {
                displayTime=displayTime.substring(0,6);
            }
        //change window coordinates later!!!!!
        window.drawString("Time: "+displayTime, 950,200);
        window.drawString("Score: "+score, 950,300);
        window.drawString("Health: "+health+"/"+maxHealth, 950,400);
        window.drawString("Ammo: "+ammo+"/"+maxAmmo, 950,500);
    }
}
