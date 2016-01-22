/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

/**
 *
 * @author Richard
 */
public class Gunner implements MouseListener{
    private int ammo, maxAmmo;
    private boolean gunFiring;
    private Timer timer;
    private int screenX, screenY;
    private Point hitPoint;
   
    public Gunner()
    {
        ammo = 100;
        maxAmmo = 100;
        hitPoint = new Point(-1,-1);//nothing can be hit
        gunFiring = false;
    }
    public Gunner(int a, int m)
    {
        ammo = a;
        maxAmmo = m;
        hitPoint = new Point(-1,-1);//nothing can be hit
        gunFiring = false;
    }
    public void startAmmoIncrementalTimer() {     
        int delay = 100;
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               ammo++;
               if(ammo>maxAmmo) {
                   ammo = maxAmmo;
               }
            }
        };
        timer =new Timer(delay, task);
        timer.start();
    }
    public int getAmmo()
    {
        return ammo;
    }
    public Point getHitPoint()
    {
        if(gunFiring) {
            gunFiring = false; //make sure only one shot is fired
            return hitPoint;
        }
        else {
            hitPoint.move(-1,-1);
            return hitPoint;
        }
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        screenX = e.getX();
        screenY = e.getY();
        if(ammo>0) {
            hitPoint.move(CoordinateConverter.screenToXCoordinate(screenX),CoordinateConverter.screenToYCoordinate(screenY));
            ammo--;
        }
        gunFiring = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {} 

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
