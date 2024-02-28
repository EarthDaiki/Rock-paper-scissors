/*
* TCSS 305 Winter 2024
* Assignment 0
*/
package model;

/**
 * The Status enum represents the different states of the Rock Scissors Paper game.
 *
 * @author Daiki Hagiwara
 * @version 13 Jan 2024
 */
public enum Status {
    
    /**
     * Represents the idle state.
     */
    IDLE,
    
    /**
     * Represents the start state.
     */
    START,
    
    /**
     * Represents the end state.
     */
    END,
    
    /**
     * Represents the playing state.
     */
    PLAYING,
    
    /**
     * Represents the replay state.
     */
    REPLAY,
    
    /**
     * Represents the win state.
     */
    WIN,
    
    /**
     * Represents the lose state.
     */
    LOSE,
    
    /**
     * Represents the draw state.
     */
    DRAW,
    
    /**
     * Represents the error state.
     */
    ERROR;
    
    /**
     * The current status.
     */
    private static Status myStatus = Status.IDLE;
    
    /**
     * The number of wins.
     */
    private static int myNumberOfWins;
    
    /**
     * The number of loses.
     */
    private static int myNumberOfLoses;
    
    /**
     * The total number of games played.
     */
    private static int myTotalGames;
    
    /**
     * The number of draws.
     */
    private static int myNumberOfDraws;
    
    /**
     * Sets the current status.
     * @param theStatus The status to set.
     */
    public static void setStatus(final Status theStatus) {
        myStatus = theStatus;
    }
    
    /**
     * Gets the current status.
     * @return The current status.
     */
    public static Status getStatus() {
        return myStatus;
    }
    
    /**
     * Gets the total number of games played.
     * @return The total number of games.
     */
    public int getTotalGame() {
        return myTotalGames;
    }
    
    /**
     * Gets the number of wins.
     * @return The number of wins.
     */
    public int getNumberOfWins() {
        return myNumberOfWins;
    }
    
    /**
     * Gets the number of loses.
     * @return The number of loses.
     */
    public int getNumberOfLoses() {
        return myNumberOfLoses;
    }
    
    /**
     * Gets the number of draws.
     * @return The number of draws.
     */
    public int getNumberOfDraws() {
        return myNumberOfDraws;
    }
    
    /**
     * Adds 1 to the total number of games played.
     */
    public static void addTotalGame() {
        myTotalGames++;
    }
    
    /**
     * Adds 1 to the number of wins.
     */
    public static void addWin() {
        myNumberOfWins++;
    }
    
    /**
     * Adds 1 to the number of loses.
     */
    public static void addLose() {
        myNumberOfLoses++;
    }
    
    /**
     * Adds 1 to the number of draws.
     */
    public static void addDraw() {
        myNumberOfDraws++;
    }
    
    /**
     * Resets the number of draws to 0.
     */
    public static void resetDraw() {
        myNumberOfDraws = 0;
    }
}
