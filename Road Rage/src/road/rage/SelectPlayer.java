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
    private SaveLoad saveLoad;
    private ImageIcon backgroundImage;
    
    /**
     * Creates frame for selecting player slot.
     */
    public SelectPlayer()
    {
        try
        {
            saveLoad = new SaveLoad();
            font = new Font("Arial", Font.PLAIN, 12);//set font
            selectScreen.setSize(405, 130);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//size
            selectScreen.setLocation((int)(screenSize.getWidth()/2)-200,(int)(screenSize.getHeight()/2)-100);//set location to center
            selectScreen.setTitle("Select User");//title
            selectScreen.setResizable(false);
            selectScreen.getContentPane().add(this);
            selectScreen.repaint();//paints on info
            selectScreen.setVisible(true);
            this.setLayout(null);
            backgroundImage = new ImageIcon(ImageManager.getImage(66).getScaledInstance(100, 100, 0));//background added
            playerList = saveLoad.getProfiles();//retrieves profiles from file
            if(playerList.size()<4)
            { 
                int currentSize = playerList.size();
                for(int i=currentSize;i<4;i++)
                {
                    playerList.add(new PlayerProfile("New Player",0,100,100));
                }
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
            JButton button = new JButton(backgroundImage);
            //draws out the player name onto buttone
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
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
                    //do some action to start game
                }
            });
            button.setBounds(100*slot, 0, 100, 100);//set location
            this.add(button);//add to frame.
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not create select player buttons", ex);
        }
    } 
}
