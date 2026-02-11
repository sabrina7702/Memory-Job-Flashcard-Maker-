package model;

// a class that represents the level of the application user
public class Levels {
    private int level;
    private static int fillInBlnkPerk = 3;
    private static int matchPerk = 5;
    private static int mCPerk = 7;
    private static int jeopardyPerk = 9;
    
    // EFFECTS: constructs the level of the user level to 1
    public Levels() {
        level = 1;
    }

    // REQUIRES: level < 10
    // MODIFIES: this
    // EFFECTS: increments the level
    public void increaseLevel() {
        level++;
    }

    // EFFECTS: returns true if the user's current level 
    // is at a level with any perk
    public boolean canUnlockPerk() {
        return level == fillInBlnkPerk || level == matchPerk || level == mCPerk || level == jeopardyPerk;
    }
    
    public int getLevel() {
        return level;
    }
}
