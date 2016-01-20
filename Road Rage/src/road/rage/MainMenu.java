/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Richard
 */
public class MainMenu extends JPanel {
    
    private static JFrame menuFrame = new JFrame();
    private JButton play, tutorial, store, credits, highScore, next, back, exit;  
    private Color blackStartFilter = new Color(0.0f,0.0f,0.0f,1.0f);
    
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
        menuFrame.setTitle("Road Rage");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menuFrame.setIconImage(ImageManager.getImage(86));
        //start painting screen onto frame.
        menuFrame.getContentPane().add(this);
        //startTime = System.currentTimeMillis();//have default start time for comparison
        menuFrame.setVisible(true);
        //BackgroundMusic.play("Race_Car_Music");//start music
        EventLogger.logEvent("Game menu load successful");//log menu load
        menuFrame.repaint();//start updatin graphics
    }
    public void addButtons()
    {
        try
        {
            //set fonts, size, color
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Monaco", Font.BOLD, 14)));
            Font font = new Font("WLM Carton", Font.PLAIN, 52);
            Font fontSmall = new Font("WLM Carton", Font.PLAIN, 42);
            Color buttonColor = Color.black;
            //create first play button
            play = new JButton("Play");
            play.setBorder(BorderFactory.createEmptyBorder());
            play.setContentAreaFilled(false);
            play.setHorizontalTextPosition(JButton.CENTER);
            play.setVerticalTextPosition(JButton.CENTER);
            play.setFont(font);
            play.setForeground(buttonColor);
            final JPanel p = this;
            //what happens when button is clicked.
            play.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                   // SoundFX.playFX("Select");
                   // SelectPlayer p = new SelectPlayer();//opens select player menu
                }
            });
            //add tutorial button        
            tutorial = new JButton("Tutorial");
            tutorial.setBorder(BorderFactory.createEmptyBorder());
            tutorial.setContentAreaFilled(false);
            tutorial.setHorizontalTextPosition(JButton.CENTER);
            tutorial.setVerticalTextPosition(JButton.CENTER);
            tutorial.setFont(font);
            tutorial.setForeground(buttonColor);
            tutorial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    //SoundFX.playFX("Select");
                    //GameRunner g = new GameRunner();
                    //g.start("Tutorial", -1);
                }
            });
            //add store button
            store = new JButton("Store");
            store.setBorder(BorderFactory.createEmptyBorder());
            store.setContentAreaFilled(false);
            store.setHorizontalTextPosition(JButton.CENTER);
            store.setVerticalTextPosition(JButton.CENTER);
            store.setFont(font);
            store.setForeground(buttonColor);
            store.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    //launch store
                }
            });
            //add high scores button        
            highScore = new JButton("High Scores");
            highScore.setBorder(BorderFactory.createEmptyBorder());
            highScore.setContentAreaFilled(false);
            highScore.setHorizontalTextPosition(JButton.CENTER);
            highScore.setVerticalTextPosition(JButton.CENTER);
            highScore.setFont(fontSmall);
            highScore.setForeground(buttonColor);
            highScore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    /*SoundFX.playFX("Select");
                    TreeMap<Integer, String> t = SaveLoad.getHighScores();//calls to get high scores to display.
                    String scores = "";
                    for(int i = 0; i < 4; i++)
                    {
                        scores += t.lastEntry().getValue();
                        scores += " " + t.lastKey() + "\n";
                        t.pollLastEntry();
                    }
                    */
           //         JOptionPane.showMessageDialog(null,scores, "Highscores" ,JOptionPane.PLAIN_MESSAGE);
                }
            });

           //create credits button.         
           credits = new JButton("Credits");
           credits.setBorder(BorderFactory.createEmptyBorder());
           credits.setContentAreaFilled(false);
           credits.setHorizontalTextPosition(JButton.CENTER);
           credits.setVerticalTextPosition(JButton.CENTER);
           credits.setFont(font);
           credits.setForeground(buttonColor);
           credits.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                      //          SoundFX.playFX("Select");
                                 JOptionPane.showMessageDialog(null, "                                                Credits\n"
                                         + "                                Developed By The Techs\n"
                                         + "Project Manager:                Richard Dong\n"
                                         + "Lead Developer:                 Richard Dong\n"
                                         + "Graphics Manager:     George Zhang and Kenneth Wang\n"
                                         + "Documentation Manager:  Abhijeet Venkataraman:\n\n"
                                         + "               Contracted By Congative Though Media\n\n"
                                         + "                                       Developed Using\n"
                                         + "Netbeans:                             Development Enviroment\n"
                                         + "Photoshop:                           Graphics Production\n"
                                         + "Paint Dot Net:                       Graphics Producation\n"
                                         + "BFXR Studios:                      Sound Production\n"
                                         + "BitBucket:                             Group Code Collaberation\n"
                                         + "Google Drive:                       Group Documentation Collaberation\n","Credits",JOptionPane.PLAIN_MESSAGE);
                            }
                    });

            //create exit button.       
            exit = new JButton("Exit");
            exit.setBorder(BorderFactory.createEmptyBorder());
            exit.setContentAreaFilled(false);
            exit.setHorizontalTextPosition(JButton.CENTER);
            exit.setVerticalTextPosition(JButton.CENTER);
            exit.setFont(font);
            exit.setForeground(buttonColor);
            exit.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    //SoundFX.playFX("Select");
                    System.exit(1);
                }
            });
            //set positions and size of buttons, which change with screen size; add to screen. 
            play.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4, menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.setLayout(null);
            this.add(play);
            tutorial.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4+(3*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(tutorial);
            store.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4+(5*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(tutorial);
            highScore.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4+(7*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(highScore);
            credits.setBounds(menuFrame.getWidth()/4, menuFrame.getHeight()/4+(9*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(credits);
            exit.setBounds((int)(menuFrame.getWidth()/4), menuFrame.getHeight()/4+(11*(menuFrame.getHeight()/16)), menuFrame.getWidth()/2, menuFrame.getHeight()/16);
            this.add(exit);   
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create menu buttons",ex);
        }   
    }


    /**
     * Closes the menu.
     */
    public static void closeMenu()
    {
        try
        {   
            menuFrame.dispose();
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not dispose frame",ex);
        }
    }
    
}
