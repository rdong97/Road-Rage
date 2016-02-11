/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Richard
 */
public class GameRunner extends JPanel implements KeyListener, MouseListener{
    
    public static JFrame gameFrame;//frame for game
    private static Timer timer;//times the refresh rate
    private final int timerSpeed = 60;//refresh rate
    private long startTime;
    private long updateTime = 0;
    private int eventTime = 900;
    private final Color blackStartFilter = new Color(0.0f,0.0f,0.0f,0.0f);
    private BufferedImage back;//backdrop where images are drawn
    
    private HUD hud;
    private Road road;
    public static boolean[] keysPressed; //A, D, left, right
    public static Point hitPoint;
    public static boolean gunShot;
    private String playerName;
    private int score, ammo, maxAmmo, health, maxHealth;
    
    public void launchRun(PlayerProfile p)
    {
        MainMenu.closeMenu();
        GameRunner.gameFrame = new JFrame();
        this.removeAll();//clears frame from menu or previous level
        //calculates and sets a refresh rate.
        startTime = System.currentTimeMillis();
        updateTime = startTime;
        
        //sets the screen size to full screen, resizable.
        gameFrame.setSize(1200,900); //default fram size
        gameFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        //adds other micellaneous items, such as frame name, exit button, and listeners.
        gameFrame.setTitle("Road Rage");
        gameFrame.setResizable(false);
        gameFrame.setLocation(0, 0);//top left corner
        gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        gameFrame.addKeyListener(this);
        gameFrame.addMouseListener(this);
        keysPressed = new boolean[4];
        hitPoint = new Point(0,0);
        gunShot = false;
        startPaint();
    }
    public void startPaint()
    {
        //new timer for refresh rate
        ActionListener timerListener = new ActionListener() 
        {
                @Override
                public void actionPerformed(ActionEvent e){}
        };
        timer = new Timer(timerSpeed, timerListener);

        timer.start();
        gameFrame.repaint();
        gameFrame.add(this); 

        //system for determining start/end Y value based on difficulty, higher 
        //values indicate harder levels as users have less space to create paths
        road = new Road();//new grid   
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }
    
    @Override
    public void paint(Graphics g)
    {    
        if(hud==null)
        {
            hud = new HUD(playerName, score, ammo, maxAmmo, health, maxHealth);//new heads up display
            hud.startTimer();//start level timer, which determines end score.
        }
        if(back==null)//if canvas has not been created, create canvsas
        {
            back=(BufferedImage)createImage(1200,900);
        }
        Graphics2D twoDGraph = (Graphics2D)g;
        Graphics graphToBack= back.createGraphics(); //prepares drawing onto bufferedimage graphics
        road.draw(graphToBack);//draws gamegrid, includes blocks
        hud.draw(graphToBack);//draws heads up display
        twoDGraph.drawImage(back,0,0,gameFrame.getWidth(),gameFrame.getHeight(),null);//draws bufferedimage to frame
        if(System.currentTimeMillis()-updateTime >= eventTime)//updates based on refresh rate
        {
            road.updateEntityLocations();
        }
        repaint();//redo again in loop
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
           keysPressed[0] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
           keysPressed[1] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
           keysPressed[2] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
           keysPressed[3] = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int screenX = e.getX();
        int screenY = e.getY();
        hitPoint.move(screenX,screenY);
        gunShot = true; //set to false in check collisions
        System.out.println(screenX+" "+screenY);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {} 

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
