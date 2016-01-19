/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Richard
 */
public class GameRunner extends JPanel {
    
    private static JFrame gameFrame;//frame for game
    private static Timer timer;//times the refresh rate
    private final int timerSpeed = 60;//refresh rate
    private long startTime;
    private long updateTime = 0;
    private int eventTime = 900;
    private boolean introPlaying = false;
    private boolean tutorialPlaying = false;
    private final Color blackStartFilter = new Color(0.0f,0.0f,0.0f,0.0f);
    private BufferedImage back;//backdrop where images are drawn
    
    private HUD hud;
    private Road road;
    private String playerName;
    private int score, ammo, maxAmmo, health, maxHealth;
    
    public GameRunner()
    {
        road = new Road();
    }
    public void startLevel()
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
            back=(BufferedImage)createImage(1500,900);
        }
        Graphics2D twoDGraph = (Graphics2D)g;
        Graphics graphToBack= back.createGraphics(); //prepares drawing onto bufferedimage graphics
        road.draw(graphToBack);//draws gamegrid, includes blocks
        hud.draw(graphToBack);//draws heads up display
        twoDGraph.drawImage(back,0,0,gameFrame.getWidth(),gameFrame.getHeight(),null);//draws bufferedimage to frame
        if(System.currentTimeMillis()-updateTime >= eventTime)//updates based on refresh rate
        {
            updateEntities();
        }
        repaint();//redo again in loop
    }  
    public void updateEntities()//move everything around, enemies based on AI
    {
        
    }
}
