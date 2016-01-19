/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class Gunner implements MouseListener{
    private int gunX, gunY, weaponType, ammo, maxAmmo;
    private boolean gunFiring;
    private int screenX, screenY, xTargetCoordinate, yTargetCoordinate;
    private Point hitPoint;
    private ArrayList<Enemy> enemyLocations;
   
    public Gunner()
    {
        gunX = 200;
        gunY = 70;
        weaponType = 1;
        ammo = 100;
        maxAmmo = 100;
        hitPoint = new Point(0,0);
        enemyLocations = new ArrayList<Enemy>();
        gunFiring = false;
    }
    public Gunner(int x, int y, int t, int a, int m)
    {
        gunX = x;
        gunY = y;
        weaponType = t;
        ammo = a;
        maxAmmo = m;
        hitPoint = new Point(0,0);
        enemyLocations = new ArrayList<Enemy>();
        gunFiring = false;
    }
    public void updateLocations(ArrayList<Enemy>enemyLocs)
    {
        enemyLocations = enemyLocs;
    }
    public void fireWeapon()
    {
        //check if the point falls insuide enemy hit box
        ammo--;
    }
    public void draw(Graphics window)
    {
        if(gunFiring)
        {
            //draw bullet lights at gun location
            //draw explosions of enemy being fired
        }
        
        //enemy might explode if line passes over enemy location
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        screenX = e.getX();
        screenY = e.getY();
        hitPoint.move(CoordinateConverter.screenToXCoordinate(screenX),CoordinateConverter.screenToYCoordinate(screenY));
        gunFiring = true;
        //fire
        fireWeapon();
        gunFiring = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        screenX = e.getX();
        screenY = e.getY();
        hitPoint.move(CoordinateConverter.screenToXCoordinate(screenX),CoordinateConverter.screenToYCoordinate(screenY));
        gunFiring = true;
        //fire
        fireWeapon();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gunFiring = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
