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
 * @author Justin Hill
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
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/CTM.png"))); //0
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/ContractedBy.png"))); //1
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/DevelopedBy.png"))); //2
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/TECHs.png"))); //3
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/MM.png"))); //4
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Maze.png"))); //5
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Mythical.png"))); //6
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/teddy.png"))); //7
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Brick.png"))); //8
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Ice_Stacked.png"))); //9
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Jungle_Rock.png"))); //10
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Rock.png"))); //11
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Wood_Crate_Enlarged.png"))); //12
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Evil_Forest_Demo.png"))); //13
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Pyramids.png")));//14
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Stonehenge.png")));//15
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Alamo.png"))); //16
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Chichen Itza.png"))); //17
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_ChichenItza.png"))); //18
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_EiffelTower.png")));//19
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_SydneyOperaHouse.png")));//20
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_TajMahal.png")));//21
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Wood_Crate_Enlarged_Three.png")));//22
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_Chichen Itza Happy.png")));//23
            
            for(int i = 1; i <= 7; i++) //24-30
            {
                images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Slide" + i + ".PNG")));
            }
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Bob.png")));//31
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Bob1.png")));//32
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Bob2.png")));//33
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Bob3.png")));//34
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Bob4.png")));//35
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Block_Ice_Enlarged.png"))); //36
            for(int i = 1; i <= 15; i++) //37-51
            {
                images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Tutorial/T" + i + ".png")));
            }
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Background_GreatWall.png")));//52
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/GameOver.png")));//53
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/ThankYou.png")));//54
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/GoHome.png")));//55
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Far1.png")));//56
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Far2.png")));//57
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Where1.png")));//58
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Where2.png")));//59
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Buttons/Music_Button.png")));//60
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Buttons/Muted_Music_Button.png")));
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Buttons/Sound_Effects_Button.png")));
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Buttons/Muted_Sound_Effects_Button.png")));
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Buttons/Main_Menu_Button.png")));
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Buttons/Restart_Level_Button.png")));//65
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/SelectPlayer_Screen.png")));//66
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Remember1.png")));//67
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Remember2.png")));//68
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Remember3.png")));//69
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Remember4.png")));//70
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Purpose1.png")));//71
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Purpose2.png")));//72
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Purpose3.png")));//73
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Blood.png")));//74
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Blood2.png")));//75
            for(int i = 1; i <= 6; i++) //76-81
            {
                images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/ToBe" + i + ".png")));
            }
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/See.png")));//82
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Hello World.png")));//83
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Hello World2.png")));//84
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Story/Hello World3.png")));//85
            images.add(ImageIO.read(ImageManager.class.getResource("Graphics/Logo.jpg")));//86
            Thread.sleep(100);    
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
