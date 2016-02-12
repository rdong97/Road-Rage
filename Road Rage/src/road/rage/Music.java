/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Richard
 */
public class Music {
    private static Clip clip;
    private static String lastSong = "";

    /**
     * Plays a specific song
     * @param name the song to play.
     */
    public static void play(String name)
    {
        if(clip!=null)
        { 
            clip.stop(); 
        }
        lastSong = name;
        final String songName = name;
        Thread thread;
        thread = new Thread(new Runnable()
        {
            @Override
            public void run() 
            {
                AudioInputStream audioIn = null;//initiates audio stream
                try 
                {
                    audioIn = AudioSystem.getAudioInputStream(Music.class.getResource("Music/"+songName+".wav"));//set path
                    clip = AudioSystem.getClip();//gets clip
                    clip.open(audioIn);//opens clip
                    clip.start();//plays song
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } 
                catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex)
                {
                    ErrorLogger.logIOError("Music could not load: IOException",ex);
                }
            }
        });
        EventLogger.logEvent("Music successfully loaded and played");
        thread.start();//runs the music playing thread
    }
    
    /**
     * Stops the current song from playing
     */
    public static void stop()
    {
        try
        {
            if(clip!=null)
            {
                clip.stop();
                clip = null;
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Could not stop music clip",ex);
        }
    }
}
