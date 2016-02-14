/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

/**
 *
 * @author Richard
 */
public class PlayerProfile {
    
    private final String playerName;
    private int score, maxAmmo, maxHealth;
    
    public PlayerProfile(String n, int s, int h, int a){
        playerName = n;
        score = s;
        maxHealth = h;
        maxAmmo = a;
        
    }
    public void setScore(int s) {
        score = s;
    }
    public void setMaxAmmo(int a) {
        maxAmmo = a;
    }
    public void setMaxHealth(int h) {
        maxHealth = h;
    }
    
    public String getName() {
        return playerName;
    }
    public int getScore() {
        return score;
    }
    public int getMaxAmmo() {
        return maxAmmo;
    }
    public int getMaxHealth() {
        return maxHealth;
    }   
}
