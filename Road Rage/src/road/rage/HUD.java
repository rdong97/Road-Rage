package road.rage;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Class dedicated to displaying the basic information about the current game
 * (player name, score, ammo, maximum value for ammo, health and maximum health)
 * @author 02-1024-0008
 */
public class HUD {
    
    private final String profileName;
    private Timer timer;
    private double timeElapsed;
    private int score, ammo, maxAmmo, health, maxHealth;
    
    /**
     * Display a generic HUD for a Default Player.
     */
    public HUD() {
        profileName = "Default Player";
        score = 0;
        ammo = 100;
        maxAmmo = 100;
        health = 100;
        maxHealth = 100;
    }
    
    /**
     * Display a HUD with a specific player name, score, health, maximum health,
     * ammo and maximum ammo
     * @param n the name of the current player
     * @param s the score of the current player
     * @param h the maximum health for the current player
     * @param a the maximum ammo for the current player
     */
    public HUD(String n, int s, int h, int a) {
        profileName = n;
        score = s;
        ammo = a;
        maxAmmo = a;
        health = h;
        maxHealth = h;
    }
    
    /**
     * Start the timer for the game.
     */
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
    
    /**
     * Get the current user's statistics 
     * @param stats An ArrayList that contains the statistics of the current 
     * user
     */
    public void liveUpdateHUD(ArrayList<Integer>stats) {
        score = stats.get(0);
        health = stats.get(1);
        maxHealth = stats.get(2);
        ammo = stats.get(3);
        maxAmmo = stats.get(4);
    }
    
    /**
     * Draw the window for the HUD
     * @param window The graphics window for the HUD
     */
    public void draw(Graphics window) {
        try {
            Font myFont=new Font("Impact",Font.PLAIN, 20);
            window.setColor(Color.white);
            window.setFont(myFont);
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not set HUD font.", ex);
        }
        try {
            window.drawImage(ImageManager.getImage(11), 900, 0, 300, 900, null);
            window.drawString("Name: "+profileName, 950,200);
            window.drawString("Score: "+score, 950,400);
            window.drawString("Health: "+health+"/"+maxHealth, 950,500);
            window.drawString("Ammo: "+ammo+"/"+maxAmmo, 950,600);
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not draw non-time items onto HUD.", ex);
        }
        try {
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
            window.drawString("Time: "+displayTime, 950,300);
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not format and display time.", ex);
        }
    }
}