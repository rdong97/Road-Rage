/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Richard
 */
public class Store extends JPanel{
    
    private PlayerProfile playerProfile;
    private JFrame storeScreen;
    private Font font;
    private SaveLoad saveLoad;
    private ImageIcon backgroundImage;
    
    public Store(PlayerProfile p) {
        try
        {
            playerProfile = p;
            font = new Font("Arial", Font.PLAIN, 12);//set font
            storeScreen.setSize(405, 130);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//size
            storeScreen.setLocation((int)(screenSize.getWidth()/2)-200,(int)(screenSize.getHeight()/2)-100);//set location to center
            storeScreen.setTitle("Store");//title
            storeScreen.setResizable(false);
            storeScreen.getContentPane().add(this);
            storeScreen.repaint();//paints on info
            storeScreen.setVisible(true);
            this.setLayout(null);
            backgroundImage = new ImageIcon(ImageManager.getImage(66).getScaledInstance(100, 100, 0));//background added
            createButtons();
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not select a player from frame",ex);
        }
    }
    public void updateInfo() {
        //write new info
        JLabel score = new JLabel("Score:"+playerProfile.getScore());
        score.setBounds(null);
        
        JLabel health = new JLabel("Max Health:"+playerProfile.getMaxHealth());
        health.setBounds(null);
        
        JLabel ammo = new JLabel("Max Ammo:"+playerProfile.getMaxAmmo());
        ammo.setBounds(null);
        
        this.add(score);
        this.add(health);
        this.add(ammo);
        this.repaint();
        //save new info
        
        SaveLoad.updateSaveProfile(playerProfile);
        
    }
    
    public void createButtons() {
        JButton upgradeHealthButton = new JButton(backgroundImage);
        //draws out the upgrade health onto button
        upgradeHealthButton.setBorderPainted(false);
        upgradeHealthButton.setContentAreaFilled(false);
        upgradeHealthButton.setHorizontalTextPosition(SwingConstants.CENTER);
        upgradeHealthButton.setText("<html>" + "Upgrade" + "</html>");
        upgradeHealthButton.setFont(font);
        upgradeHealthButton.setForeground(Color.black);
        upgradeHealthButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(playerProfile.getScore()>100) {
                    playerProfile.setMaxHealth(playerProfile.getMaxHealth()+100);
                    playerProfile.setScore(playerProfile.getScore()-100);
                }
                updateInfo();
            }
        });
        upgradeHealthButton.setBounds(300, 20, 40, 20);//set location
        this.add(upgradeHealthButton);//add to frame.


        JButton upgradeAmmoButton = new JButton(backgroundImage);
        //draws out the upgrade health onto button
        upgradeAmmoButton.setBorderPainted(false);
        upgradeAmmoButton.setContentAreaFilled(false);
        upgradeAmmoButton.setHorizontalTextPosition(SwingConstants.CENTER);
        upgradeAmmoButton.setText("<html>" + "Upgrade" + "</html>");
        upgradeAmmoButton.setFont(font);
        upgradeAmmoButton.setForeground(Color.black);
        upgradeAmmoButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(playerProfile.getScore()>100) {
                    playerProfile.setMaxAmmo(playerProfile.getMaxAmmo()+100);
                    playerProfile.setScore(playerProfile.getScore()-100);
                }
                updateInfo();
            }
        });
        upgradeAmmoButton.setBounds(300, 60, 40, 20);//set location
        this.add(upgradeHealthButton);//add to frame.
    }
}

