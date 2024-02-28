/*
* TCSS 305 Winter 2024
* Assignment 0
*/
package model;

import java.util.Random;

/**
* The Hands enum represents the possible hands in the Rock Scissors Paper game.
* @author Daiki Hagiwara
* @version 13 Jan 2024
 */
public enum Hands {
    
    /**
     * Represents an idle hand.
     */
    IDLE(-1),
    
    /**
     * Represents the rock hand.
     */
    ROCK(0),
    
    /**
     * Represents the scissors hand.
     */
    SCISSORS(1),
    
    /**
     * Represents the paper hand.
     */
    PAPER(2);
    
    /**
     * The current hand.
     */
    private static Hands myHand = Hands.IDLE;
    
    /**
     * Random number generator for obtaining random hands.
     */
    private static final Random MY_RANDOM = new Random();
    
    /**
     * The integer value associated with the hand.
     */
    private final int myValue;
    
    /**
     * Constructs a hand with the specified value.
     * @param theValue The integer value associated with the hand.
     */
    Hands(final int theValue) {
        this.myValue = theValue;
    }

    /**
     * Gets the integer value associated with the hand.
     * @return The integer value.
     */
    public int getValue() {
        return myValue;
    }
    
    /**
     * Sets the current hand.
     * @param theHand The hand to set.
     */
    public static void setHand(final Hands theHand) {
        myHand = theHand;
    }
    
    /**
     * Gets the current hand.
     * @return The current hand.
     */
    public static Hands getHand() {
        return myHand;
    }
    
    /**
     * Gets an array containing only the hands (excluding IDLE).
     * @return An array of hands.
     */
    public static Hands[] getOnlyHands() {
        final Hands[] handsOnly = new Hands[Hands.values().length - 1];
        int index = 0;
        for (Hands hand : Hands.values()) {
            if (hand != Hands.IDLE) {
                handsOnly[index++] = hand;
            }
        }
        return handsOnly;
    }
    
    /**
     * Gets a random hand including IDLE.
     * @return A random hand.
     */
    public static  Hands getRandomHandWithIdle() {
        final Hands[] hands = Hands.values();
        final int index = MY_RANDOM.nextInt(hands.length);
        return hands[index];
    }
    
    /**
     * Gets a random hand excluding IDLE.
     * @return A random hand.
     */
    public static Hands getRandomOnlyHands() {
        final Hands[] handsOnly = getOnlyHands();
        final int index = MY_RANDOM.nextInt(handsOnly.length);
        return handsOnly[index];
    }
}
