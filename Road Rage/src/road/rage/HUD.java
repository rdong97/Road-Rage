/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public void startTimer()
    {     
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
    
    public void draw(Graphics window)
    {
        
    }
    
}
