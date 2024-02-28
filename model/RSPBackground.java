/*
* TCSS 305 Winter 2024
* Assignment 0
*/
package model;
/**
 * This program represents the background of the Rock Scissors Paper GUI.
 * Codes for background, win rate, match result, and screen reload.
 *
 * @author Daiki Hagiwara
 * @version 13 Jan 2024
 */
public final class RSPBackground {
    
    /**
     * Get RSPGUI.
     */
    private static RSPGUI gameView = RSPGUI.getMyGameView();
    
    /**
     * Title call.
     */
    private static String startCall =  "Rock Scissors Paper... 1, 2, 3!!";
    
    /**
     * Private constructor to prevent instantiation of this utility class.
     * @param none
     */
    private RSPBackground() {
        // do nothing.
    }
    
    /**
     * Determines the result of the game based on the player's hand and the computer's random hand.
     *
     * @param theHand The player's hand.
     * @return The MatchResult containing the game status and computer's hand.
     */
    public static MatchResult gameResult(final Hands theHand) {
        final Hands computerHand = Hands.getRandomOnlyHands();
        final int result = (theHand.getValue() - computerHand.getValue() + 3) % 3;
        if (result == 0) {
            return new MatchResult(Status.DRAW, computerHand);
        } else if (result == 1) {
            return new MatchResult(Status.LOSE, computerHand);
        } else {
            return new MatchResult(Status.WIN, computerHand);
        }
    }
    
    /**
     * Calculates the win rate based on the provided game status.
     *
     * @param theStatus The game status.
     * @return The win rate as a percentage.
     */
    public static double winRate(final Status theStatus) {
        final int totalGames = theStatus.getTotalGame();

        if (totalGames == 0) {
            return 0.0;
        }
        return ((double) theStatus.getNumberOfWins() / totalGames) * 100.0;
    }
    
    /**
     * Reloads the header panel based on the provided game status.
     *
     * @param theStatus The game status.
     */
    public static void headerReload(final Status theStatus) {
        gameView.myHeader.removeAll();
        if (theStatus == Status.WIN || theStatus == Status.LOSE) {
            Status.addTotalGame();
        } 
        if (theStatus == Status.WIN) {
            Status.addWin();
        } else if (theStatus == Status.LOSE) {
            Status.addLose();
        }
        gameView.headerView(theStatus);
        gameView.myHeader.revalidate();
        gameView.myHeader.repaint();
    }
    
    /**
     * Reloads the main panel based on the provided game status and player's hand.
     *
     * @param theStatus The game status.
     * @param theHand The player's hand.
     */
    public static void mainPanelReload(final Status theStatus, final Hands theHand) {
        final String[] replay = {"Replay!!"};
        final String[] close = {"Close Window"};
        gameView.myMainPanel.removeAll();
        if (theStatus == Status.START) {
            gameView.labelTemplate(startCall, gameView.myMainPanel);
            gameView.makeButton(null, theHand, gameView.myMainPanel);
            gameView.setVisible(true);
        } else if (theStatus == Status.END) {
            gameView.labelTemplate("<html>Thank You For Playing!!<br/>Bye!!</html>", 
                                   gameView.myMainPanel); 
            gameView.makeButton(close, null, gameView.myMainPanel);
        } else if (theStatus == Status.WIN) {
            gameView.labelTemplate("YOU WIN!!", gameView.myMainPanel);
        } else if (theStatus == Status.LOSE) {
            gameView.labelTemplate("YOU LOSE...", gameView.myMainPanel);
        } else if (theStatus == Status.DRAW) {
            if (theStatus.getNumberOfDraws() < 1) {
                gameView.labelTemplate("IT'S A DRAW!", gameView.myMainPanel);
            } else {
                gameView.labelTemplate("IT'S A DRAW AGAIN!!", gameView.myMainPanel);
            }
            gameView.labelTemplate(startCall, gameView.myMainPanel);
            gameView.makeButton(null, theHand, gameView.myMainPanel);
            Status.addDraw();
        } else if (theHand == Hands.ROCK) {
            final MatchResult matchResult = gameResult(theHand);
            gameView.endGameView(matchResult.getStatus(), 
                                 matchResult.getComputerHand(), 
                                 theHand);
        } else if (theHand == Hands.SCISSORS) {
            final MatchResult matchResult = gameResult(theHand);
            gameView.endGameView(matchResult.getStatus(), 
                                 matchResult.getComputerHand(), 
                                 theHand);
        } else if (theHand == Hands.PAPER) {
            final MatchResult matchResult = gameResult(theHand);
            gameView.endGameView(matchResult.getStatus(), 
                                 matchResult.getComputerHand(), 
                                 theHand);
        } else if (theStatus == Status.REPLAY) {
            gameView.labelTemplate(startCall, gameView.myMainPanel);
            gameView.makeButton(null, theHand, gameView.myMainPanel);
            Status.resetDraw();
        }
        
        if (theStatus == Status.WIN || theStatus == Status.LOSE) {
            gameView.makeButton(replay, null, gameView.myMainPanel);
            gameView.makeButton(close, null, gameView.myMainPanel);
        }

        gameView.myMainPanel.revalidate();
        gameView.myMainPanel.repaint();
    }
    
    /**
     * Closes the application window.
     */
    public static void closeWindow() {
        System.exit(0);
    }
}
