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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class manages the store and upgrades part of the game
 * @author 02-1024-0008
 */
public class Store extends JPanel{
    
    private PlayerProfile playerProfile;
    private JFrame storeScreen;
    private Font font;
    
    /**
     * Constructor that initializes the display of the store on the user's screen
     * @param p The player profile of the current user playing the game and 
     * attempting to upgrade their vehicle
     */
    public Store(PlayerProfile p) {
        try
        {
            storeScreen = new JFrame();
            playerProfile = p;
            font = new Font("Arial", Font.PLAIN, 12);//set font
            storeScreen.setSize(400, 200);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//size
            storeScreen.setLocation((int)(screenSize.getWidth()/2)-200,(int)(screenSize.getHeight()/2)-100);//set location to center
            storeScreen.setTitle("Store");//title
            storeScreen.setResizable(false);
            storeScreen.getContentPane().add(this);
            storeScreen.repaint();//paints on info
            storeScreen.setVisible(true);
            this.setLayout(null);  
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not initialize store screen",ex);
        }
    }
    
    /**
     * Update the display based on the current user's information
     */
    public void updateInfo() {
        //write new info based on upgraded stats
        try {
            this.removeAll();
            JLabel score = new JLabel("Score:"+playerProfile.getScore());
            score.setBounds(10,10,100,20);
            this.add(score);
            JLabel health = new JLabel("Max Health:"+playerProfile.getMaxHealth());
            health.setBounds(10,100,100,20);
            this.add(health);
            JLabel ammo = new JLabel("Max Ammo:"+playerProfile.getMaxAmmo());
            ammo.setBounds(10,140,100,20);
            this.add(ammo);
            createButtons();//add buttons that were removed
            this.repaint();
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not update stats.", ex);
        }
        //save new info
        try {
            SaveLoad.updateSaveProfile(playerProfile);
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not save store updated player profile.", ex);
        }
    }
    
    
    /**
     * Draw the store menu
     * @param g the specified graphics to draw the store menu
     */
    @Override
    public void paintComponent(Graphics g)
    {
        try {
            g.drawImage(ImageManager.getImage(15),0,0,getWidth(),getHeight(),null);
        }
        catch(Exception ex) {
            ErrorLogger.logRuntimeError("Could not draw store graphic", ex);
        }
        repaint();//refresh screen
    }
    
    /**
     * Create the buttons necessary for the user to be able to effectively use 
     * the store
     */
    public void createButtons() {
        try {
            JButton upgradeHealthButton = new JButton();
            //draws out the upgrade health onto button
            upgradeHealthButton.setBorderPainted(false);
            upgradeHealthButton.setContentAreaFilled(true);
            upgradeHealthButton.setHorizontalTextPosition(SwingConstants.CENTER);
            upgradeHealthButton.setText("Upgrade Max Health");
            upgradeHealthButton.setFont(font);
            upgradeHealthButton.setForeground(Color.black);
            upgradeHealthButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(playerProfile.getScore()>100) {
                        playerProfile.setMaxHealth(playerProfile.getMaxHealth()+10);
                        playerProfile.setScore(playerProfile.getScore()-100);
                        Music.play("ButtonSound");
                    }
                    updateInfo();
                }
            });
            upgradeHealthButton.setBounds(200, 100, 200, 20);//set location
            this.add(upgradeHealthButton);//add to frame.

            JButton upgradeAmmoButton = new JButton();
            //draws out the upgrade health onto button
            upgradeAmmoButton.setBorderPainted(false);
            upgradeAmmoButton.setContentAreaFilled(true);
            upgradeAmmoButton.setHorizontalTextPosition(SwingConstants.CENTER);
            upgradeAmmoButton.setText("Upgrade Max Ammo");
            upgradeAmmoButton.setFont(font);
            upgradeAmmoButton.setForeground(Color.black);
            upgradeAmmoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(playerProfile.getScore()>100) {
                        playerProfile.setMaxAmmo(playerProfile.getMaxAmmo()+10);
                        playerProfile.setScore(playerProfile.getScore()-100);
                        Music.play("ButtonSound");
                    }
                    updateInfo();
                }
            });
            upgradeAmmoButton.setBounds(200, 140, 200, 20);//set location
            this.add(upgradeAmmoButton);//add to frame.

            JButton startGame = new JButton();
            //draws out the upgrade health onto button
            startGame.setBorderPainted(false);
            startGame.setContentAreaFilled(true);
            startGame.setHorizontalTextPosition(SwingConstants.CENTER);
            startGame.setText("Start Game");
            startGame.setFont(font);
            startGame.setForeground(Color.black);
            startGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Music.play("ButtonSound");
                    storeScreen.dispose();
                    GameRunner newGame = new GameRunner();
                    newGame.launchRun(playerProfile);
                }
            });
            startGame.setBounds(200, 10, 200, 20);//set location
            this.add(startGame);//add to frame.
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not create store menu buttons.", ex);
        }
    }
}