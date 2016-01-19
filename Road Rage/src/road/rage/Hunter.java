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
    private int vehicleType, weaponType, ammo, maxAmmo, score;
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
        weaponType = 1;
        
        ammo = 100;//weaponry
        maxAmmo = 100; 
        
        score = 0;
        
        gunner = new Gunner();
    }
    public Hunter(int x, int y, int w, int l, int xs, int ys, int h, int mh, int t, int wt, int a, int ma, int s)
    {
        super(x,y,w,l,xs,ys,h,mh);
                
        vehicleType = t;//graphics
        weaponType = wt;
        
        ammo = a;//weaponry
        maxAmmo = ma; 
        
        score = s;
        
        gunner = new Gunner();
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
    public int getWeaponType() {
        return weaponType;
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
    public void setWeaponType(int t) {
        weaponType = t;
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

    @Override
    public void draw(Graphics window) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
