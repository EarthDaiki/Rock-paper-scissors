/*
* TCSS 305 Winter 2024
* Assignment 0
*/
package model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
* The RSPGUI class represents the graphical user interface for the Rock Scissors Paper game.
* It extends JFrame and implements ActionListener to handle user interactions.
* @author Daiki Hagiwara
* @version 13 Jan 2024
*/

public class RSPGUI extends JFrame implements ActionListener {
    
    /**
     * The RSPGUI class represents the graphical user interface 
     * for the Rock Scissors Paper game.
     * It extends JFrame and implements ActionListener to handle user interactions.
     */
    private static RSPGUI myGameView;

    /**
     * The serial version UID for ensuring object serialization compatibility.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Button label for the "YES!!" option.
     * Note: There is an error regarding case expressions not being constant expressions 
     * in switch statements.
     */
    private static String yes = "YES!!";

    /**
     * Button label for the "NO..." option.
     * Note: There is an error regarding case expressions not being constant expressions 
     * in switch statements.
     */
    private static String no = "NO...";

    /**
     * Panel for the main content of the GUI.
     */
    protected JPanel myMainPanel;

    /**
     * Panel for the header content of the GUI.
     */
    protected JPanel myHeader;

    /**
     * Number of hand buttons.
     */
    private int myBtnNumbers = 4;

    /**
     * Array of JButtons representing rock, scissors, paper buttons, etc.
     */
    private JButton[] myBtns = new JButton[myBtnNumbers];

    /**
     * X-coordinate of the frame.
     */
    private int myFrameX = 100;

    /**
     * Y-coordinate of the frame.
     */
    private int myFrameY = 100;

    /**
     * Width of the frame.
     */
    private int myFrameWidth = 300;

    /**
     * Height of the frame.
     */
    private int myFrameHeight = 300;
                    
    /**
     * Constructor for creating an instance of RSPGUI.
     * Initializes the frame, panels, and other components.
     * @param none
     */
    RSPGUI() {
        setTitle("Rock Scissors Paper");
        setBounds(myFrameX, myFrameY, myFrameWidth, myFrameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myHeader = new JPanel();
        myHeader.setLayout(new BoxLayout(myHeader, BoxLayout.Y_AXIS));
        myMainPanel = new JPanel();
        myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(myHeader);
        getContentPane().add(myMainPanel);
    }

    /**
     * The main method to start the Rock Scissors Paper game.
     * Creates an instance of RSPGUI, sets it as the game view, and initializes the UI.
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        final RSPGUI gameView = new RSPGUI();
        setMyGameView(gameView);
        gameView.labelTemplate("Let's Play Rock Scissors Paper!", gameView.myMainPanel);
        gameView.makeButton(new String[]{yes, no}, null, gameView.myMainPanel);
        gameView.setVisible(true);
    }

    /**
     * Gets the current game view instance.
     * @param none
     * @return The current RSPGUI instance.
     */
    public static RSPGUI getMyGameView() {
        return myGameView;
    }

    /**
     * Sets the current game view instance.
     * @param theGameView The RSPGUI instance to set.
     */
    public static void setMyGameView(final RSPGUI theGameView) {
        RSPGUI.myGameView = theGameView;
    }

    /**
     * Displays the header view with information about the game status.
     * @param theStatus The current game status.
     */
    public void headerView(final Status theStatus) {
        System.out.println(theStatus);
        labelTemplate("TOTALGAME: " + theStatus.getTotalGame(), myHeader);
        labelTemplate("WIN: " + theStatus.getNumberOfWins()
                + " LOSE: " + theStatus.getNumberOfLoses()
                + " WIN RATE: " + RSPBackground.winRate(theStatus), myHeader);
    }

    /**
     * Creates a label with the specified text and adds it to the given panel.
     * @param theTextLabel The text for the label.
     * @param thePanel The panel to which the label is added.
     */
    public void labelTemplate(final String theTextLabel, final JPanel thePanel) {
        final JLabel label = new JLabel(theTextLabel);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        thePanel.add(label);
    }

    /**
     * Displays the end game view based on the game status and hands played.
     * @param theStatus The game status.
     * @param theComputerHand The computer's hand.
     * @param theYourHand Your hand.
     */
    public void endGameView(final Status theStatus,
                            final Hands theComputerHand,
                            final Hands theYourHand) {
        labelTemplate("Computer: " + theComputerHand, myMainPanel);
        labelTemplate("Your Hand: " + theYourHand, myMainPanel);
        if (theStatus == Status.WIN) {
            RSPBackground.headerReload(theStatus);
            RSPBackground.mainPanelReload(theStatus, theYourHand);
        } else if (theStatus == Status.LOSE) {
            RSPBackground.headerReload(theStatus);
            RSPBackground.mainPanelReload(theStatus, theYourHand);
        } else {
            RSPBackground.mainPanelReload(theStatus, theYourHand);
        }
    }

    /**
     * Creates buttons based on the given labels and adds them to the panel.
     * @param theButtonLabels Labels for the buttons.
     * @param theHand The hand (can be null).
     * @param thePanel The panel to which buttons are added.
     */
    public void makeButton(final String[] theButtonLabels,
                           final Hands theHand,
                           final JPanel thePanel) {
        if (theHand != null) {
            for (Hands hand : Hands.getOnlyHands()) {
                myBtns[hand.ordinal()] = new JButton(hand.toString());
                myBtns[hand.ordinal()].addActionListener(this);
                myBtns[hand.ordinal()].setAlignmentX(Component.CENTER_ALIGNMENT);
                thePanel.add(myBtns[hand.ordinal()]);
            }
        } else {
            for (String buttonLabel: theButtonLabels) {
                final JButton button = new JButton(buttonLabel);
                button.addActionListener(this);
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                thePanel.add(button);
            }
        }
    }

    /**
     * Handles action events triggered by button clicks.
     * @param theE The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(final ActionEvent theE) {
        final String command = theE.getActionCommand();
        final Hands hand;
        final Status status;
        if (command != null) {
            switch (command) {
                case "YES!!":
                    Status.setStatus(Status.START);
                    status = Status.getStatus();
                    Hands.setHand(Hands.IDLE);
                    hand = Hands.getHand();
                    RSPBackground.mainPanelReload(status, hand);
                    RSPBackground.headerReload(status);
                    break;
                case "NO...":
                    Status.setStatus(Status.END);
                    status = Status.getStatus();
                    Hands.setHand(Hands.IDLE);
                    hand = Hands.getHand();
                    RSPBackground.mainPanelReload(status, hand);
                    break;
                case "ROCK":
                    Hands.setHand(Hands.ROCK);
                    hand = Hands.getHand();
                    Status.setStatus(Status.PLAYING);
                    status = Status.getStatus();
                    RSPBackground.mainPanelReload(status, hand);
                    break;
                case "SCISSORS":
                    Hands.setHand(Hands.SCISSORS);
                    hand = Hands.getHand();
                    Status.setStatus(Status.PLAYING);
                    status = Status.getStatus();
                    RSPBackground.mainPanelReload(status, hand);
                    break;
                case "PAPER":
                    Hands.setHand(Hands.PAPER);
                    hand = Hands.getHand();
                    Status.setStatus(Status.PLAYING);
                    status = Status.getStatus();
                    RSPBackground.mainPanelReload(status, hand);
                    break;
                case "Replay!!":
                    Status.setStatus(Status.REPLAY);
                    status = Status.getStatus();
                    Hands.setHand(Hands.IDLE);
                    hand = Hands.getHand();
                    RSPBackground.headerReload(status);
                    RSPBackground.mainPanelReload(status, hand);
                    break;
                case "Close Window":
                    Status.setStatus(Status.IDLE);
                    status = Status.getStatus();
                    RSPBackground.closeWindow();
                    break;
                default:
                    Status.setStatus(Status.ERROR);
                    status = Status.getStatus();
            }
        }
    }
}

