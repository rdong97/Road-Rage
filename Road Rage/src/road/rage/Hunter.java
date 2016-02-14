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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Hunter extends Vehicle {
    
    private int score,ammo,maxAmmo;
    
    private Timer timer;
    
    public Hunter()
    {
        super(440,600,0,0,60,80,100,100);
        ammo = 100;
        maxAmmo = 100;
        score = 0;
        
    }
    public Hunter(int h, int a, int s)
    {
        super(440,600,0,0,60,80,h,h);
        ammo = a;
        maxAmmo = a;        
        
        score = s;
    }

    
    public int getAmmo() {
        return ammo;
    }
    public int getMaxAmmo() {
        return maxAmmo;
    }
    public int getScore() {
        return score;
    }
    
    public void setAmmo(int a) {
        ammo = a;
    }
    public void setMaxAmmo(int a) {
        maxAmmo = a ;
    }
    public void setScore(int s) {
        score = s;
    }
    public void startIncrementalTimers() {     
        int delay = 1000;
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               setHealth(getHealth()+1);
               if(getHealth()>getMaxHealth()) {
                   setHealth(getMaxHealth());
               }
               ammo++;
               if(ammo>maxAmmo) {
                   ammo = maxAmmo;
               }
            }
        };
        timer =new Timer(delay, task);
        timer.start();
    }
    public void findXSpeed() {
        setXSpeed(0);
        if(GameRunner.keysPressed[0]||GameRunner.keysPressed[2]) {
            if(getXCoordinate()>=150) {
                setXSpeed(-10);              
            }
        }
        
        if(GameRunner.keysPressed[1]||GameRunner.keysPressed[3]) {
            if(getXCoordinate()<=750-getXWidth()) {
                setXSpeed(10);          
            }
        }
    }
    @Override
    public void draw(Graphics window) {
        int imageNum = 1;//will change based on skin
        window.drawImage(ImageManager.getImage(imageNum),getXCoordinate(), getYCoordinate(), getXWidth(),getYLength(), null);
    } 
}
