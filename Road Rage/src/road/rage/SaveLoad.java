package road.rage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 02-1024-0008
 */
public class SaveLoad {
    
    public static ArrayList<PlayerProfile> getProfiles() {
        ArrayList<PlayerProfile>profileList= new ArrayList<>();//list of profile
        try {
            File f = new File("profiles/profiles.txt");//set path to file
            if(f.exists()) {
                try (Scanner lineScanner = new Scanner(new FileReader(f))) {
                    while(lineScanner.hasNext()) {
                        String[]parts = lineScanner.nextLine().split(",");
                        String name = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        int maxAmmo = Integer.parseInt(parts[2]);
                        int maxHealth = Integer.parseInt(parts[3]);
                        profileList.add(new PlayerProfile(name,score,maxAmmo,maxHealth));
                    }
                }
                EventLogger.logEvent("Profile list successfully loaded");
                return profileList;
            }
        } 
        catch (IOException e) {
            ErrorLogger.logIOError("Cannot load profiles", e);
            return null;
        }
        catch(NumberFormatException ex) {
            ErrorLogger.logRuntimeError("NumberFormatException loading Profiles",ex);
        }
        return null;//catch all if no profiles to return
    }
    public static void saveProfileList(ArrayList<PlayerProfile>list) {
        ArrayList<PlayerProfile> profileList = list;
        try {
            File f = new File("profiles/profiles.txt");//set file path
            BufferedWriter out = new BufferedWriter(new FileWriter(f));//set file path
            for(PlayerProfile p:profileList) {
                out.write(p.getName()+","+p.getScore()+","+p.getMaxAmmo()+","+p.getMaxHealth()); //write profiles
                out.newLine();//one per line
            }
            out.close();//close writer
            EventLogger.logEvent("New profile saved in list successfully");
        } 
        catch (IOException e) {
            ErrorLogger.logIOError("Cannot save new profile", e);
        }
        catch(Exception ex) {
            ErrorLogger.logRuntimeError("Unknown error, unable to save profile", ex);
        }
    }
    public static void updateSaveProfile(PlayerProfile profile) {
        ArrayList<PlayerProfile>list = getProfiles();
        try {
            for(int i=0;i<list.size();i++) {
                if(list.get(i).getName().equals(profile.getName())) {
                    list.set(i, profile);//find the changed profile in the list, change profile
                }
            }
            saveProfileList(list);//save list
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not update and save changed profile.", ex);
        }
    }
}
