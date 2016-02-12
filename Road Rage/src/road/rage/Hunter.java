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
    
    private int health, maxHealth, xSpeed, ySpeed;
    private int vehicleType, ammo, maxAmmo, score;
    
    private Timer timer;
    
    public Hunter()
    {
        super();
        xCoordinate = 440;//positon
        yCoordinate = 600;//position
        
        xWidth = 20;//size
        yLength = 40;
        
        xSpeed = 0;//movement
        ySpeed = 0;
        
        health = 100;//health
        maxHealth = 100;
        
        vehicleType = 1;//graphics
        
        ammo = 100;//weaponry
        maxAmmo = 100; 
        
        score = 0;
        
    }
    public Hunter(int x, int y, int w, int l, int xs, int ys, int h, int mh, int t, int a, int ma, int s)
    {
        super(x,y,xs,ys,w,l,h,mh);
                
        vehicleType = t;//graphics
        
        ammo = a;//weaponry
        maxAmmo = ma; 
        
        score = s;
        
    }

    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getVehicleType() {
        return vehicleType;
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
    public void setHealth(int h) {
        health = h;
    }
    public void setMaxHealth(int h) {
        maxHealth = h;
    }
    public void setVehicleType(int t) {
        vehicleType = t;
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
        int delay = 100;
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               health++;
               if(health>maxHealth) {
                   health = maxHealth;
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
            if(getXCoordinate()<=750) {
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
