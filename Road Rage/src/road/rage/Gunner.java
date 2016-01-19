/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Richard
 */
public class Gunner implements MouseListener{
    private int gunX, gunY, weaponType, ammo, maxAmmo;
    private boolean gunFiring;
    private int screenX, screenY, xCoordinate, yCoordinate;
    public Gunner()
    {
        gunX = 200;
        gunY = 70;
        weaponType = 1;
        ammo = 100;
        maxAmmo = 100;
        gunFiring = false;
    }
    public Gunner(int x, int y, int t, int a, int m)
    {
        gunX = x;
        gunY = y;
        weaponType = t;
        ammo = a;
        maxAmmo = m;
        gunFiring = false;
    }
    public void updateLocation(int x, int y)
    {
        gunX = x;
        gunY = y;
    }
    
    public void draw(Graphics window)
    {
        if(gunFiring)
        {
            //draw bullets being fired
        }
        
        //enemy might explode if line passes over enemy location
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        screenX = e.getX();
        screenY = e.getY();
        xCoordinate = CoordinateConverter.screenToXCoordinate(screenX);
        yCoordinate = CoordinateConverter.screenToYCoordinate(gunX, screenX);
        gunFiring = true;
        //point - slope form of line of bullets
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
