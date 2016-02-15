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
 * @author 02-1024-0008
 */
public class EndGame extends JPanel {
    
    private PlayerProfile playerProfile;
    private JFrame endScreen;
    private Font font;

    public EndGame(PlayerProfile p) {
        try {
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
            ErrorLogger.logRuntimeError("Could not launch end game frame.",ex);
        }
    }
    private void createButtons() {
        try {
            //restart game button
            JButton restartGame = new JButton();
            restartGame.setBorderPainted(false);
            restartGame.setContentAreaFilled(true);
            restartGame.setHorizontalTextPosition(SwingConstants.CENTER);
            restartGame.setText("Restart Game");
            restartGame.setFont(font);
            restartGame.setForeground(Color.black);
            restartGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameRunner newGame = new GameRunner();
                    newGame.launchRun(playerProfile);
                    Music.play("GameMusic");
                    endScreen.dispose();
                }
            });
            restartGame.setBounds(100, 10, 200, 20);//set location top center
            this.add(restartGame);//add to frame.

            //launch menu button
            JButton menu = new JButton();
            //draws out the upgrade health onto button
            menu.setBorderPainted(false);
            menu.setContentAreaFilled(true);
            menu.setHorizontalTextPosition(SwingConstants.CENTER);
            menu.setText("Main Menu");
            menu.setFont(font);
            menu.setForeground(Color.black);
            menu.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainMenu newMenu = new MainMenu();
                    newMenu.startMenu(); //launch menu
                    endScreen.dispose(); //dispose of end game frame
                }
            });
            menu.setBounds(100, 40, 200, 20);//set location mid center
            this.add(menu);//add to frame.

            //exit game button
            JButton exit = new JButton();
            exit.setBorderPainted(false);
            exit.setContentAreaFilled(true);
            exit.setHorizontalTextPosition(SwingConstants.CENTER);
            exit.setText("Exit Game");
            exit.setFont(font);
            exit.setForeground(Color.black);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);//game exits
                }
            });
            exit.setBounds(100, 70, 200, 20);//set location bottom center
            this.add(exit);//add to frame.
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not add end game frame buttons.", ex);
        }
    }
    @Override
    public void paintComponent(Graphics g)
    {
        try {
            g.drawImage(ImageManager.getImage(0),0,0,getWidth(),getHeight(),null);
        }
        catch(Exception ex) {
            ErrorLogger.logRuntimeError("Could not draw end game frame graphic.", ex);
        }
        repaint();//refresh screen
    }
    
    private void saveProfile() {
        try {
            SaveLoad.updateSaveProfile(playerProfile);
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not execute save profile command.", ex);
        }
    }
}
