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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Hunter extends Vehicle implements KeyListener{
    
    private int health, maxHealth, xSpeed, ySpeed;
    private int vehicleType, ammo, maxAmmo, score;
    private boolean[] keysPressed;
    private Gunner gunner;
    
    public Hunter()
    {
        super();
        xCoordinate = 190;//positon
        yCoordinate = 540;//540 to 580, final
        
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
        
        keysPressed = new boolean[4]; //A, D, left, right
        
        gunner = new Gunner();
    }
    public Hunter(int x, int y, int w, int l, int xs, int ys, int h, int mh, int t, int a, int ma, int s)
    {
        super(x,y,xs,ys,w,l,h,mh);
                
        vehicleType = t;//graphics
        
        ammo = a;//weaponry
        maxAmmo = ma; 
        
        score = s;
        
        keysPressed = new boolean[4]; //A, D, left, right
        
        gunner = new Gunner(a,ma);
    }

    public int getXSpeed() {
        return xSpeed;
    }
    public int getYSpeed() {
        return ySpeed;
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
    
    public void setXSpeed(int x) {
        xSpeed = x;
    }
    public void setYSpeed(int y) {
        ySpeed = y;
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

    public void findXSpeed() {
        if(keysPressed[0]||keysPressed[2]) {
            if(getXCoordinate()>=10) {
                setXSpeed(-10);
                setXCoordinate(getXCoordinate()+getXSpeed());                
            }
        }
        if(keysPressed[1]||keysPressed[3]) {
            if(getXCoordinate()<=290) {
                setXSpeed(10);
                setXCoordinate(getXCoordinate()+getXSpeed());                
            }
        }
    }
    @Override
    public void draw(Graphics window) {
        int imageNum = 0;//will change based on skin
        int screenX = CoordinateConverter.xToScreenCoordinate(xCoordinate);
        int screenY = CoordinateConverter.yToScreenCoordinate(yCoordinate);
        window.drawImage(ImageManager.getImage(imageNum),screenX, screenY, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
       
        if(e.getKeyCode() == KeyEvent.VK_A) {
           keysPressed[0] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
           keysPressed[1] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
           keysPressed[2] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
           keysPressed[3] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) {
           keysPressed[0] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
           keysPressed[1] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
           keysPressed[2] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
           keysPressed[3] = true;
        }
    }
}
