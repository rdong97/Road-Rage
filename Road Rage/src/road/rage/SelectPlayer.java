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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Richard
 */
public class SelectPlayer extends JPanel {

    private JFrame selectScreen;
    private ArrayList<PlayerProfile>playerList;
    private Font font;
    
    /**
     * Creates frame for selecting player slot.
     */
    public SelectPlayer()
    {
        try
        {
            selectScreen = new JFrame();
            font = new Font("Arial", Font.PLAIN, 12);//set font
            selectScreen.setSize(400, 200);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//size
            selectScreen.setLocation((int)(screenSize.getWidth()/2)-200,(int)(screenSize.getHeight()/2)-100);//set location to center
            selectScreen.setTitle("Select User");//title
            selectScreen.setResizable(false);
            selectScreen.getContentPane().add(this);
            selectScreen.repaint();//paints on info
            selectScreen.setVisible(true);
            this.setLayout(null);
            
            playerList = SaveLoad.getProfiles();//retrieves profiles from file
            if(playerList == null) {
                for(int i=1;i<5;i++)
                {
                    playerList.add(new PlayerProfile("Player "+i,0,100,100));
                }
                SaveLoad.saveProfileList(playerList);
            }
            else if(playerList.size() < 4) {
                int currentSize = playerList.size();
                for(int i=currentSize+1;i<5;i++)
                {
                    playerList.add(new PlayerProfile("Player "+i,0,100,100));
                }
                SaveLoad.saveProfileList(playerList);
            }
            for(int i=0;i<4;i++)
            {
                createButton(playerList.get(i),i);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not select a player from frame",ex);
        }
    }
    
    /**
     * Creates and adds a button for selecting player slot.
     */
    private void createButton(PlayerProfile p, int slot)
    {
        try
        {
            JButton button = new JButton();
            //draws out the player name onto buttone
            button.setBorderPainted(false);
            button.setContentAreaFilled(true);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setText("<html>" + p.getName() + "</html>");
            button.setFont(font);
            button.setForeground(Color.black);
            button.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    selectScreen.dispose();
                    Store storeScreen = new Store(p);
                }
            });
            button.setBounds(100*slot, 120, 100, 20);//set location
            this.add(button);//add to frame.
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create select player buttons", ex);
        }
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
}
