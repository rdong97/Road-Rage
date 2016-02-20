package road.rage;

/**
 * Class that controls the player profiles
 * @author 02-1024-0008
 */
public class PlayerProfile {
    
    private String playerName;
    private int score, maxAmmo, maxHealth;
    
    /**
     * Initiate a player profile with a specific name, score, max health and max
     * ammo
     * @param n name of the current player
     * @param s score of the current player
     * @param h maximum value of health of the current player
     * @param a maximum value of ammo of the current
     */
    public PlayerProfile(String n, int s, int h, int a) {
        try {
            playerName = n;
            score = s;
            maxHealth = h;
            maxAmmo = a;
        }
        catch(RuntimeException ex) {
            ErrorLogger.logRuntimeError("Could not initialize player profile.", ex);
        }
    }
    
    /**
     * Set the score to a specific value
     * @param s The value to set the score to
     */
    public void setScore(int s) {
        score = s;
    }
    
    /**
     * Set the maximum possible of the ammo to a specific value
     * @param a The value to set the maximum possible value of ammo to
     */
    public void setMaxAmmo(int a) {
        maxAmmo = a;
    }
    
    /**
     * Set the maximum possible value of health to a specific value
     * @param h The value to set the maximum possible value of health to
     */ 
    public void setMaxHealth(int h) {
        maxHealth = h;
    }
    
    /**
     * Return the current user's name
     * @return playerName the variable that stores the current player's  name
     */
    public String getName() {
        return playerName;
    }
    
    /**
     * Return the value of the current user's score
     * @return score the variable that stores the score of the current player
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Return the maximum ammo of the vehicle of the current player
     * @return maxAmmo the variable that stores the maximum possible value for
     * ammo
     */
    public int getMaxAmmo() {
        return maxAmmo;
    }
    
    /**
     * Return the maximum health of the vehicle of the current player
     * @return maxHealth the variable that stores the maximum possible value for
     * health
     */
    public int getMaxHealth() {
        return maxHealth;
    }   
}
