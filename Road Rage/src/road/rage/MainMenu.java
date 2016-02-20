package road.rage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Class dedicated to displaying the main menu
 * @author 02-1024-0008
 */
public class MainMenu extends JPanel {
    
    public static JFrame menuFrame;
    private JButton play, tutorial, credits, highScore, next, back, exit;  
    
    /**
     * Display the main menu
     */
    public void startMenu() {
        try {
            EventLogger.setupEvent();//set up logs
            ErrorLogger.setupError();
            CrashLogger.setupCrash();
        }
        catch(RuntimeException ex) {
            ErrorLogger.logIOError("Unable to set up log files", ex);
        }
        try {
            ImageManager.importAll();//import all images
        }
        catch(RuntimeException ex) {
            ErrorLogger.logIOError("Unable to import graphics",ex);
        }
        try {
            menuFrame = new JFrame();//initialize frame
            
            //set correct dimmensions.
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double menuWidth = screenSize.getWidth();
            double menuHeight = screenSize.getHeight();
            menuFrame.setSize((int)menuWidth, (int)menuHeight);
            
            //set title, exit and size buttons.
            menuFrame.setTitle("Road Rage");
            menuFrame.setResizable(false);
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            menuFrame.setIconImage(ImageManager.getImage(0));
            
            //start painting screen onto frame.
            menuFrame.getContentPane().add(this);
            menuFrame.setVisible(true);
            EventLogger.logEvent("Game menu load successful");//log menu load
            addButtons();//add menu buttons
            menuFrame.repaint();//start updatin graphics
            Music.play("GameMusic");//play game music
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Unable to initialize menu.", ex);
        }
    }
    
    /**
     * Add the buttons needed for the user to select options displayed by the 
     * main menu
     */
    public void addButtons() {
        try {
            //set fonts, size, color
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Monaco", Font.BOLD, 14)));
            Font font = new Font("WLM Carton", Font.PLAIN, 52);
            Font fontSmall = new Font("WLM Carton", Font.PLAIN, 42);
            Color buttonColor = Color.black;
            
            //create first play button
            play = new JButton("Play");
            play.setBorder(BorderFactory.createEmptyBorder());
            play.setContentAreaFilled(true);
            play.setHorizontalTextPosition(JButton.CENTER);
            play.setVerticalTextPosition(JButton.CENTER);
            play.setFont(font);
            play.setForeground(buttonColor);
            //what happens when button is clicked.
            play.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SelectPlayer selectScreen = new SelectPlayer();
                }
            });
            
            //add tutorial button        
            tutorial = new JButton("Tutorial");
            tutorial.setBorder(BorderFactory.createEmptyBorder());
            tutorial.setContentAreaFilled(true);
            tutorial.setHorizontalTextPosition(JButton.CENTER);
            tutorial.setVerticalTextPosition(JButton.CENTER);
            tutorial.setFont(font);
            tutorial.setForeground(buttonColor);
            tutorial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TutorialSlideShow tutorial = new TutorialSlideShow();
                    tutorial.addButtons();
                }
            });
            
            //add high scores button        
            highScore = new JButton("High Scores");
            highScore.setBorder(BorderFactory.createEmptyBorder());
            highScore.setContentAreaFilled(true);
            highScore.setHorizontalTextPosition(JButton.CENTER);
            highScore.setVerticalTextPosition(JButton.CENTER);
            highScore.setFont(fontSmall);
            highScore.setForeground(buttonColor);
            highScore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    ArrayList<PlayerProfile>profileList = SaveLoad.getProfiles();
                    int maxIndex;
                    PlayerProfile tmp;
                    int n = profileList.size();
                    for (int i = 0; i < n - 1; i++) {
                        maxIndex = i;
                        for (int j = i + 1; j < n; j++)
                          if (profileList.get(j).getScore() > profileList.get(maxIndex).getScore()) {
                              maxIndex = j;
                          }              
                        if (maxIndex != i) {
                          tmp = profileList.get(i);
                          profileList.set(i,profileList.get(maxIndex));
                          profileList.set(maxIndex,tmp);
                        }
                    }
                    String message = "";
                    for(PlayerProfile p:profileList) {
                        message+=p.getName()+": "+p.getScore()+"\n";
                    }
                    JOptionPane.showMessageDialog(menuFrame,message,"High Scores",JOptionPane.INFORMATION_MESSAGE);
                }
            });

            //create credits button.         
            credits = new JButton("Credits");
            credits.setBorder(BorderFactory.createEmptyBorder());
            credits.setContentAreaFilled(true);
            credits.setHorizontalTextPosition(JButton.CENTER);
            credits.setVerticalTextPosition(JButton.CENTER);
            credits.setFont(font);
            credits.setForeground(buttonColor);
            credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(null, "                                                Credits\n"
                         + "                  Developed By BlackBird Mafia Studios\n"
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
            exit.setContentAreaFilled(true);
            exit.setHorizontalTextPosition(JButton.CENTER);
            exit.setVerticalTextPosition(JButton.CENTER);
            exit.setFont(font);
            exit.setForeground(buttonColor);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);//normal user exit
                }
            });
            
            //set positions and size of buttons, which change with screen size; add to screen. 
            play.setBounds(menuFrame.getWidth()/5, menuFrame.getHeight()/5, menuFrame.getWidth()/5, menuFrame.getHeight()/16);
            this.setLayout(null);
            this.add(play);
            tutorial.setBounds(3*menuFrame.getWidth()/5, menuFrame.getHeight()/5, menuFrame.getWidth()/5, menuFrame.getHeight()/16);
            this.add(tutorial);
            highScore.setBounds(menuFrame.getWidth()/5, 3*menuFrame.getHeight()/5, menuFrame.getWidth()/5, menuFrame.getHeight()/16);
            this.add(highScore);
            credits.setBounds(3*menuFrame.getWidth()/5, 3*menuFrame.getHeight()/5, menuFrame.getWidth()/5, menuFrame.getHeight()/16);
            this.add(credits);
            exit.setBounds(2*menuFrame.getWidth()/5, 4*menuFrame.getHeight()/5, menuFrame.getWidth()/5, menuFrame.getHeight()/16);
            this.add(exit);   
        }
        catch(Exception ex) {
            ErrorLogger.logRuntimeError("Could not create menu buttons",ex);
        }   
    }
    @Override
    public void paintComponent(Graphics g){
        try {
            g.drawImage(ImageManager.getImage(0),0,0,getWidth(),getHeight(),null);
        }
        catch(Exception ex) {
            ErrorLogger.logRuntimeError("Could not draw menu graphic", ex);
        }
        repaint();//refresh screen
    }   
}
