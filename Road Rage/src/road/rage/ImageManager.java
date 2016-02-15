package road.rage;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Imports images for later use when called upon.
 * @author 02-1024-0008
 */
public class ImageManager 
{
    private static final ArrayList<Image> images = new ArrayList<>();
    
    /**
     * Imports images from a local file.
     */
    public static void importAll() {
        try {
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Menu.png"))); //0 menu skin
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Hunter.png"))); //1 hunter
            
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Hunter.png"))); //2 tutorials
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Hunter.png"))); //3
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Hunter.png"))); //4
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Hunter.png"))); //5
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Hunter.png"))); //6
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Hunter.png"))); //7
            
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/HighwayNone.png"))); //8 highway
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/HighwayLeft.png"))); //9
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/HighwayCenter.png"))); //10
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/HighwayRight.png"))); //11
            
            //entities
            //images.add(ImageIO.read(ImageManager.class.getResource(""))); //0
            //images.add(ImageIO.read(ImageManager.class.getResource(""))); //1

            Thread.sleep(10);    
            EventLogger.logEvent("Images successfully loaded");
        } 
        catch (IOException | InterruptedException ex) {
            ErrorLogger.logIOError("Unable to Import Graphics",ex);
            //log an error to fix local file placement, interrupted extraction.
        }
    }
    
    /**
     * Retrieves images for use in graphics creation.
     * @param slot the image key used to determine which image to select.
     * @return Image the correct image object reference.
     */
    public static Image getImage(int slot) {
        try {
            if(images.isEmpty())
            { 
                importAll(); //import images before use.
                addFont(); //import the font used in the menus of the game
            }
            return images.get(slot);            
        }
        catch(RuntimeException ex) {
             ErrorLogger.logRuntimeError("Image does not exist", ex);
             //image does not exit
             return null;
        }   
    } 
    
    /**
     * Adds a font to the game for later use.
     */
    public static void addFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, ImageManager.class.getResource("Graphics/wlm_carton.ttf").openStream());
            ge.registerFont(font);
        }
        catch(IOException | FontFormatException e)
        { 
            ErrorLogger.logIOError("Could not import Font", e);
        } 
    }
}
