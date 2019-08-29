//Gaby Inchoco, Hannah Whellan, Jacquelyn Cai
//Return of Blockbuster!

import javax.swing.JFrame;

/**
 * Driver class that sets up the frame and adds the panel.
 * At the end of the game, the user will be able to see how well they did 
 * based on the number of points they received.
 *
 * @author Jacquelyn Cai
 * @version 5/16/19
 */
public class TestTrivia {
    /**
     * Driver class that sets up the frame for the GUI
     */
    public static void main (String[] args) {
        // creates and shows a Frame 
        JFrame frame = new JFrame("Return of Blockbuster!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create an instance of the TicTacToe game
        CategoryDecisions cd = new CategoryDecisions();
        
        //create a panel, and add it to the frame
        TriviaPanel panel = new TriviaPanel(cd);
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
