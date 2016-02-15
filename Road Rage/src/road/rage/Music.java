package road.rage;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author 02-1024-0008
 */
public class Music {
    private static Clip clip;

    /**
     * Plays a specific song
     * @param name the song to play.
     */
    public static void play(String name) {
        final String songName = name;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                AudioInputStream audioIn = null;//initiates audio stream
                try {
                    audioIn = AudioSystem.getAudioInputStream(Music.class.getResource("Music/"+songName+".wav"));//set path
                    clip = AudioSystem.getClip();//gets clip
                    clip.open(audioIn);//opens clip
                    clip.start();//plays song
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } 
                catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ErrorLogger.logIOError("Music could not load: IOException",ex);
                }
            }
        });
        try {
            thread.start();//runs the music playing thread
            EventLogger.logEvent("Music successfully loaded and played");
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not start music clip", ex);
        }
    }
    

    public static void stop() {
        try {
            if(clip!=null) {
                clip.stop();
                clip = null;
            }
        }
        catch(Exception ex) {
            ErrorLogger.logRuntimeError("Could not stop music clip",ex);
        }
    }
}
