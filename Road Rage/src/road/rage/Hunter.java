package road.rage;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Class dedicated to the hunter that chases the user
 * @author 02-1024-0008
 */
public class Hunter extends Vehicle {
    
    private int score,ammo,maxAmmo;
    private Timer incrementTimer;
    
    /**
     * Create a generic hunter with predetermined characteristics.
     */
    public Hunter() {
        super(440,600,0,0,60,80,100,100);
        try {
            score = 0; 
            ammo = 100;
            maxAmmo = 100;
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize hunter.", ex);
        }
        
    }
    
    /**
     * Create a hunter with specified score, health and ammo
     * @param s the score of the hunter
     * @param h the maximum health of the hunter
     * @param a the maximum amount of ammo for the hunter
     */
    public Hunter(int s, int h, int a) {
        super(440,600,0,0,60,80,h,h);
        try {
            score = s;
            ammo = a;
            maxAmmo = a;         
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize hunter.", ex);
        }      
    }
    
    /**
     * Return the amount of ammo that the hunter currently has
     * @return ammo which is the variable that stores the current amount of ammo
     * that the hunter has
     */
    public int getAmmo() {
        return ammo;
    }
    
    /**
     * Return the maximum amount of ammo the hunter can have
     * @return maxAmmo which is the variable that stores the maximum possible
     * amount of ammo for the hunter to have
     */
    public int getMaxAmmo() {
        return maxAmmo;
    }
    
    /**
     * Return the score of the hunter
     * @return score which is the variable that stores the score of the hunter
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Set the hunter's current ammo to a specified value
     * @param a the value to set the hunter's current amount of ammo to
     */
    public void setAmmo(int a) {
        ammo = a;
    }
    
    /**
     * Set the hunter's maximum possible value of ammo to a specified value
     * @param a the value to set the hunter's maximum possible amount of ammo to
     */
    public void setMaxAmmo(int a) {
        maxAmmo = a ;
    }
    
    /**
     * Set the hunter's score to a specified value
     * @param s the value to set the hunter's score to
     */
    public void setScore(int s) {
        score = s;
    }
    
    /**
     * Start the timer that manages the attacks by the hunter
     */
    public void startIncrementalTimers() {     
        try {
            int delay = 1000;//increase every 1000 milliseconds
            ActionListener task = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   setHealth(getHealth()+1);
                   if(getHealth()>getMaxHealth()) {
                       setHealth(getMaxHealth());
                   }
                   ammo++;
                   if(ammo>maxAmmo) {
                       ammo = maxAmmo;
                   }
                }
            };
            incrementTimer =new Timer(delay, task);
            incrementTimer.start();
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not setup hunter health and ammo increase rate.", ex);
        }
    }
    
    /**
     * Find the new velocity the hunter must take in order to effectively chase
     * the user
     */
    public void findXSpeed() {
        try {
            setXSpeed(0);//default if no keys are pressed
            if(GameRunner.keysPressed[0]||GameRunner.keysPressed[2]) {
                if(getXCoordinate()>=150) {
                    setXSpeed(-10);              
                }
            }
            if(GameRunner.keysPressed[1]||GameRunner.keysPressed[3]) {
                if(getXCoordinate()<=750-getXWidth()) {
                    setXSpeed(10);          
                }
            }
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not calcuate hunter speed based on keystrokes.", ex);
        }
    }
    
    /**
     * Draw the hunter
     * @param window The window in which to draw the hunter
     */
    @Override
    public void draw(Graphics window) {
        try {
            int imageNum = 1;//will change based on skin
            window.drawImage(ImageManager.getImage(imageNum),getXCoordinate(), getYCoordinate(), getXWidth(),getYLength(), null);
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not draw hunter object", ex);
        }
    } 
}
