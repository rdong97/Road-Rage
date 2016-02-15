package road.rage;

/**
 *
 * @author 02-1024-0008
 */
public class PlayerProfile {
    
    private String playerName;
    private int score, maxAmmo, maxHealth;
    
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
