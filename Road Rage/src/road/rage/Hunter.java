package road.rage;

/**
 *
 * @author 02-1024-0008
 */
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Hunter extends Vehicle {
    
    private int score,ammo,maxAmmo;
    private Timer incrementTimer;
    
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
    public int getAmmo() {
        return ammo;
    }
    public int getMaxAmmo() {
        return maxAmmo;
    }
    public int getScore() {
        return score;
    }
    
    public void setAmmo(int a) {
        ammo = a;
    }
    public void setMaxAmmo(int a) {
        maxAmmo = a ;
    }
    public void setScore(int s) {
        score = s;
    }
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
