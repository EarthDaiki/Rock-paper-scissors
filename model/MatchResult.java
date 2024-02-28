/*
* TCSS 305 Winter 2024
* Assignment 0
*/
package model;

/**
 * Represents the result of a Rock Scissors Paper match.
 *
 * @param status The status of the match (WIN, LOSE, DRAW, etc.).
 * @param computerHand The computer's hand in the match.
 * @author Daiki Hagiwara
 * @version 12 Jan 2024
 */
public record MatchResult(Status status, Hands computerHand) {

    /**
     * Gets the status of the match.
     *
     * @return The match status.
     */
    public Status getStatus() {
        return status;
    }
    
    /**
     * Gets the computer's hand in the match.
     *
     * @return The computer's hand.
     */
    public Hands getComputerHand() {
        return computerHand;
    }
}

