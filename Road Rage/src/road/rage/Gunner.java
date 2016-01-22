/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Richard
 */
public class Gunner implements MouseListener{
    private int ammo, maxAmmo;
    private int screenX, screenY;
    private Point hitPoint;
   
    public Gunner()
    {
        ammo = 100;
        maxAmmo = 100;
        hitPoint = new Point(0,0);
    }
    public Gunner(int a, int m)
    {
        ammo = a;
        maxAmmo = m;
        hitPoint = new Point(0,0);
    }
    public int getAmmo()
    {
        return ammo;
    }
    public Point getHitPoint()
    {
        return hitPoint;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        screenX = e.getX();
        screenY = e.getY();
        if(ammo>0) {
            hitPoint.move(CoordinateConverter.screenToXCoordinate(screenX),CoordinateConverter.screenToYCoordinate(screenY));
            ammo--;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
