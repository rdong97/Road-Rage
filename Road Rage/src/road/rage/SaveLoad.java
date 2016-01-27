/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Richard
 */
public class SaveLoad {
    
    private ArrayList<PlayerProfile>profileList;
    
    public SaveLoad() {
        profileList = new ArrayList<PlayerProfile>();
    }
    
    public ArrayList<PlayerProfile> getProfiles()
    {
        try 
        {
            File f = new File("profiles/profiles.dat");//set path to file
            if(f.exists())
            {
                Scanner lineScanner = new Scanner(new FileReader(f));
                while(lineScanner.hasNext())
                {
                    String[]parts = lineScanner.nextLine().split(",");
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    int maxAmmo = Integer.parseInt(parts[2]);
                    int maxHealth = Integer.parseInt(parts[3]);
                    profileList.add(new PlayerProfile(name,score,maxAmmo,maxHealth));
                }
                lineScanner.close();
                EventLogger.logEvent("Profile list successfully loaded");
                return profileList;
            }
            else//file does not exist
            {
                return null;
            }
        } 
        catch (IOException e) 
        {
            ErrorLogger.logIOError("Cannot load profiles", e);
        }
        catch(NumberFormatException ex)
        {
            ErrorLogger.logRuntimeError("NumberFormatException loading Profiles",ex);
        }
        return null;//if no profiles to return
    }
    public void saveProfileList(ArrayList<PlayerProfile>list) {
       
        ArrayList<PlayerProfile> profileList = list;
        try 
        {
            File f = new File("profiles/profiles.dat");//set file path
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("profiles/profiles.dat")));//set file path
            for(PlayerProfile p:profileList)
            {
                out.write(p.getName()+","+p.getScore()+","+p.getMaxAmmo()+","+p.getMaxHealth());
                out.newLine();
            }
            out.close();
            EventLogger.logEvent("New profile saved in list successfully");
        } 
        catch (IOException e) 
        {
            ErrorLogger.logIOError("Cannot save new profile", e);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Unknown error, unable to save profile", ex);
        }
    }
}
