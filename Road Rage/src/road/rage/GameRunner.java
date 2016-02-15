package road.rage;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author 02-1024-0008
 */
public class GameRunner extends JPanel implements KeyListener, MouseListener{
    
    public static JFrame gameFrame;//frame for game
    private static Timer timer;//times the refresh rate
    private final int timerSpeed = 60;//graphics refresh rate
    private long startTime;
    private long updateTime = 0;
    private final int eventTime = 900; //object movement refresh rate
    private BufferedImage back;//backdrop where images are drawn
    
    private HUD hud;
    private Road road;
    public static boolean[] keysPressed; //A, D, left, right
    public static Point hitPoint;
    public static boolean gunShot;
    private PlayerProfile currentProfile;
    
    public void launchRun(PlayerProfile p) {
        try {
            MainMenu.menuFrame.dispose();
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not close main menu.", ex);
        }
        try {
            GameRunner.gameFrame = new JFrame();//initialize
            this.removeAll();//clears frame from menu or previous level
            //calculates and sets a refresh rate
            startTime = System.currentTimeMillis();
            updateTime = startTime;

            //sets the screen size to full screen, resizable.
            gameFrame.setSize(1200,900); //default fram size
            gameFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

            //adds other micellaneous items, such as frame name, exit button, and listeners.
            gameFrame.setTitle("Road Rage");
            gameFrame.setResizable(false);
            gameFrame.setLocation(0, 0);//top left corner
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setVisible(true);
            gameFrame.addKeyListener(this);
            gameFrame.addMouseListener(this);
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize game frame.", ex);
        }
        try {
            currentProfile = p;
            hud = new HUD(p.getName(), p.getScore(), p.getMaxHealth(),p.getMaxAmmo());//new heads up display
            hud.startTimer();//start level timer.
            road = new Road(currentProfile);//new grid 
            road.setEnemySpawnSpeed();//set spawning speed of enemy
            road.startSpawnEnemy();//start spawning enemies
            road.spawnInitialDebris();//spawn in debris that appears before scrolling.
            keysPressed = new boolean[4];
            hitPoint = new Point(0,0);
            gunShot = false;
            startPaint(); //start the repaint timer
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize current profile, HUD, road objects, key listeners, or other frame items.", ex);    
        }
    }
    public void startPaint() {
        //new timer for repaint rate
        try {
            ActionListener timerListener = new ActionListener()  {
                @Override
                public void actionPerformed(ActionEvent e){
                    gameFrame.repaint();//action of repaint
                }
            };
            timer = new Timer(timerSpeed, timerListener);
            timer.start();//start timer
            gameFrame.add(this); //add to frame
        }
        catch(RuntimeException ex){
            ErrorLogger.logRuntimeError("Could not start repaint timer.", ex);
        }  
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }
    
    @Override
    public void paint(Graphics g) {    
        try {
            //if canvas has not been created, create canvsas
            if(back==null){
                back=(BufferedImage)createImage(1200,900);
            }
            //if game has not ended
            if(!road.hasGameEnded()) {
                Graphics2D twoDGraph = (Graphics2D)g;
                Graphics graphToBack= back.createGraphics(); //prepares drawing onto bufferedimage graphics
                road.draw(graphToBack);//draws road, including all objects on road
                hud.liveUpdateHUD(road.getLiveStats());//update information to HUD from road objects
                hud.draw(graphToBack);//draws heads up display
                twoDGraph.drawImage(back,0,0,gameFrame.getWidth(),gameFrame.getHeight(),null);//draws bufferedimage to frame
                if(System.currentTimeMillis()-updateTime >= eventTime)//updates based on refresh rate
                {
                    road.updateEntityLocations();//update object locations based on rate
                }
                repaint();//redo again in loop
            }
            //if game has ended
            else {
                Music.stop();//stop music
                timer.stop();//stop painting
                //create new user information, especially score
                PlayerProfile toSave = new PlayerProfile(currentProfile.getName(),road.getLiveStats().get(0),currentProfile.getMaxHealth(),currentProfile.getMaxAmmo());
                EndGame endFrame = new EndGame(toSave);
                endFrame.saveProfile();//save profile
                endFrame.createButtons();//add buttons to the frame
                gameFrame.dispose();//remove game frame, only frame available is end game frame.
            } 
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not paint objects onto game frame.", ex);
        }
        
    }  
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            if(e.getKeyCode() == KeyEvent.VK_A) {
                keysPressed[0] = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_D) {
               keysPressed[1] = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
               keysPressed[2] = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
               keysPressed[3] = true;
            }  
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not register key press.", ex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            if(e.getKeyCode() == KeyEvent.VK_A) {
                keysPressed[0] = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_D) {
               keysPressed[1] = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
               keysPressed[2] = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
               keysPressed[3] = false;
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not register key release.", ex);
        } 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int screenX = e.getX();
        int screenY = e.getY();
        hitPoint.move(screenX,screenY);
        gunShot = true; //set to false when calculated hit location
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {} 

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
