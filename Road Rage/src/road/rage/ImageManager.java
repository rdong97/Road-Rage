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
 * @author Richard Dong
 */
public class ImageManager 
{
    private static final ArrayList<Image> images = new ArrayList<>();
    
    /**
     * Imports images from a local file.
     */
    public static void importAll()
    {
        try 
        {
            //skins
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Menu.png"))); //0
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Hunter.png"))); //1
            
            //tutorials
            for(int i = 1; i <= 7; i++)
            {
               // images.add(ImageIO.read(ImageManager.class.getResource("Tutorial_" + i + ".PNG")));
            }
            
            //entities
            //images.add(ImageIO.read(ImageManager.class.getResource(""))); //0
            //images.add(ImageIO.read(ImageManager.class.getResource(""))); //1

            Thread.sleep(10);    
            EventLogger.logEvent("Images successfully loaded");
        } 
        catch (IOException | InterruptedException ex) 
        {
            ErrorLogger.logIOError("Unable to Import Graphics",ex);
            //log an error to fix local file placement, interrupted extraction.
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Unknown error, unable to import graphics",ex);
        }
    }
    
    /**
     * Retrieves images for use in graphics creation.
     * @param slot the image key used to determine which image to select.
     * @return Image the correct image object reference.
     */
    public static Image getImage(int slot)
    {
        if(images.isEmpty())
        { 
            importAll(); //import images before use.
            addFont(); //import the font used in the menus of the game
        }
        if(slot<images.size()&&images.get(slot)!=null)
        {
            return images.get(slot);
        }
        else//not in parameters, image doesn't exist
        {
            ErrorLogger.logRuntimeError("Image does not exist", new NullPointerException());
            return null;
        }
    } 
    
    /**
     * Adds a font to the game for later use.
     */
    public static void addFont()
    {
        try
        {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, ImageManager.class.getResource("Graphics/wlm_carton.ttf").openStream());
            ge.registerFont(font);
        }
        catch(IOException | FontFormatException e)
        { 
            ErrorLogger.logIOError("Could not import Font", e);
        } 
        catch (Exception ex) 
        {
            ErrorLogger.logRuntimeError("Unknown error, unable to import font", ex);
        }
    }
}
