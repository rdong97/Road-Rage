/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Richard
 */
public class MainMenu extends JPanel implements KeyListener {
    
    public static JFrame menuFrame;
    
    public void startMenu() {
    
        EventLogger.setupEvent();//set up logs
        ErrorLogger.setupError();
        CrashLogger.setupCrash();
        ImageManager.importAll();//import all images
        this.removeAll();//remove anything from frame, clean slate.
        menuFrame = new JFrame();
        //set correct dimmensions.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double menuWidth = screenSize.getWidth();
        double menuHeight = screenSize.getHeight();
        menuFrame.setSize((int)menuWidth, (int)menuHeight);
        //set title, exit and size buttons.
        menuFrame.setTitle("Mythical Maze");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menuFrame.setIconImage(ImageManager.getImage(86));
        //start painting screen onto frame.
        menuFrame.getContentPane().add(this);
        //startTime = System.currentTimeMillis();//have default start time for comparison
        menuFrame.setVisible(true);
        //BackgroundMusic.play("Race_Car_Music");//start music
        menuFrame.addKeyListener(this);//enable controls
        EventLogger.logEvent("Game menu load successful");//log menu load
        menuFrame.repaint();//start updatin graphics
    }
    public static void closeMenu() {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
