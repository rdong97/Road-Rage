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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Gaming
 */
public class EndGame extends JPanel{
    
    private PlayerProfile playerProfile;
    private JFrame endScreen;
    private Font font;

    
    public EndGame(PlayerProfile p) {
        try
        {
            endScreen = new JFrame();
            playerProfile = p;
            font = new Font("Arial", Font.PLAIN, 12);//set font
            endScreen.setSize(400, 200);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//size
            endScreen.setLocation((int)(screenSize.getWidth()/2)-200,(int)(screenSize.getHeight()/2)-100);//set location to center
            endScreen.setTitle("Game Over");//title
            endScreen.setResizable(false);
            endScreen.getContentPane().add(this);
            endScreen.repaint();//paints on info
            endScreen.setVisible(true);
            this.setLayout(null);
            createButtons();
            saveProfile();
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not select a player from frame",ex);
        }
    }
    private void createButtons() {
        JButton restartGame = new JButton();
        //draws out the upgrade health onto button
        restartGame.setBorderPainted(false);
        restartGame.setContentAreaFilled(true);
        restartGame.setHorizontalTextPosition(SwingConstants.CENTER);
        restartGame.setText("Restart Game");
        restartGame.setFont(font);
        restartGame.setForeground(Color.black);
        restartGame.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                endScreen.dispose();
                GameRunner newGame = new GameRunner();
                newGame.launchRun(playerProfile);
            }
        });
        restartGame.setBounds(10, 10, 200, 20);//set location
        this.add(restartGame);//add to frame.
        
        
        JButton menu = new JButton();
        //draws out the upgrade health onto button
        menu.setBorderPainted(false);
        menu.setContentAreaFilled(true);
        menu.setHorizontalTextPosition(SwingConstants.CENTER);
        menu.setText("Exit Game");
        menu.setFont(font);
        menu.setForeground(Color.black);
        menu.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                MainMenu newMenu = new MainMenu();
                newMenu.startMenu();
                endScreen.dispose();
            }
        });
        menu.setBounds(10, 40, 200, 20);//set location
        this.add(menu);//add to frame.
        
        JButton exit = new JButton();
        //draws out the upgrade health onto button
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(true);
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setText("Exit Game");
        exit.setFont(font);
        exit.setForeground(Color.black);
        exit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(1);
            }
        });
        exit.setBounds(10, 70, 200, 20);//set location
        this.add(exit);//add to frame.
        
        
        
    }
    @Override
    public void paintComponent(Graphics g)
    {
        
        try {
            g.drawImage(ImageManager.getImage(0),0,0,getWidth(),getHeight(),null);
        }
        catch(Exception ex) {
            ErrorLogger.logRuntimeError("Could not draw menu graphic", ex);
        }
        repaint();//refresh screen
    }
    
    private void saveProfile() {
        SaveLoad.updateSaveProfile(playerProfile);
    }
}
