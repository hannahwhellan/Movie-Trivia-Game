//Gaby Inchoco, Hannah Whellan, Jacquelyn Cai
//Return of Blockbuster!

/**
 * TriviaPanel sets up the Panel that contains the game.
 * It communicates with the TriviaQuestion.java class where 
 * the functionality of the game resides.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

/**
 * Contains the components, events, and actions of the GUI
 *
 * @author Jacquelyn Cai
 * @version 5/16/19
 */
public class TriviaPanel extends JPanel {
    private String categoryChosen;  //series that user ends up picking
    private JLabel questionLabel;        //questions in decision tree/quiz
    
    //buttons for the menu and decision tree part of the game
    private JButton okButton, quitButton, yesButton, noButton, playGame;
    //subpanel for two buttons
    private JPanel okQuit;

    //buttons for the quiz part of the game
    private JButton A, B, C, D, next;
    private JPanel answersPanel;    //panel that will hold A, B, C, D
    private TriviaQuestion game;    //instance of the game
    private CategoryDecisions cd;   //decision tree object

    /**
     * Sets up various buttons, panels, and other components
     * 
     * @param CategoryDecisions object for the decision tree
     */
    public TriviaPanel(CategoryDecisions c)
    {
        //decision tree
        cd = c;
        
        //set up label
        questionLabel = new JLabel("Welcome to Return of Blockbuster! First, let's choose a category!", JLabel.CENTER);
        questionLabel.setForeground(Color.yellow);
        questionLabel.setFont(new Font("Serif", Font.BOLD, 20));
        
        //set up buttons
        okButton = new JButton("Ok");
        quitButton = new JButton("Quit :(");
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        playGame = new JButton("Let's Play!");
        
        //add ok and quit buttons to a subpanel
        okQuit = new JPanel();
        okQuit.add(okButton);
        okQuit.add(quitButton);
        okQuit.setBackground(Color.blue);
        
        //add components to the panel
        add(questionLabel);
        add(okQuit);

        // repeat for the quiz components
        A = new JButton("A");
        B = new JButton("B");
        C = new JButton("C");
        D = new JButton("D");
        next = new JButton("Next");
        
        //create/add listeners to buttons
        ButtonListener listener = new ButtonListener();
        
        //decision tree
        okButton.addActionListener(listener);
        quitButton.addActionListener(listener);
        yesButton.addActionListener(listener);
        noButton.addActionListener(listener);
        playGame.addActionListener(listener);

        //quiz
        A.addActionListener(listener);
        B.addActionListener(listener);
        C.addActionListener(listener);
        D.addActionListener(listener);
        next.addActionListener(listener);
        
        //subpanel for the multiple choice buttons
        answersPanel = new JPanel();
        answersPanel.setPreferredSize(new Dimension(250,200));
        answersPanel.setBackground(Color.yellow);
        answersPanel.add(A);
        answersPanel.add(B);
        answersPanel.add(C);
        answersPanel.add(D);

        this.setPreferredSize(new Dimension(2000,250));
        this.setBackground(Color.blue);
    }

    /**
     * Determines the actions completed in response to which buttons are clicked by user
     */
    private class ButtonListener implements ActionListener {
        /**
         * Tells the actions of each button
         * 
         * @param ActionEvent the event
         */
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == okButton) {   
                //change label to first question of decision tree
                questionLabel.setText(cd.getTree().getRootElement());   
                
                //hide ok and quit buttons
                okButton.setVisible(false);
                quitButton.setVisible(false);
                
                //add yes no buttons
                add(yesButton);
                add(noButton);
            }
            
            if (event.getSource() == yesButton) {
                cd.yesPressed();    //decision tree method
                
                //change label to the next question
                questionLabel.setText(cd.getCurrent().getRootElement());    
                
                if (cd.getCurrent().size() == 1) {  //if current is a leaf; i.e., category chosen
                    categoryChosen = cd.getCurrent().getRootElement();
                    yesButton.setVisible(false);
                    noButton.setVisible(false);
                    add(playGame);  //add button that lets user start game
                }
            }
            
            if (event.getSource() == noButton) {
                cd.noPressed();
                questionLabel.setText(cd.getCurrent().getRootElement());

                if (cd.getCurrent().size() == 1) {
                    categoryChosen = cd.getCurrent().getRootElement();
                    noButton.setVisible(false);
                    yesButton.setVisible(false);
                    add(playGame);
                }
            }
            
            //user can quit app
            if (event.getSource() == quitButton) {
                System.exit(0);
            }

            if (event.getSource() == playGame) {
                //Create new game with category instance variable 
                game = new TriviaQuestion(categoryChosen);
                String question = game.generateMovieQuestion(); //generate first question

                //System.out.println(question);
                questionLabel.setText(question);

                add(answersPanel);
                
                //assign the buttons to the generated choices
                LinkedList<String> answers = game.getChoices();
                A.setText(answers.get(0));
                B.setText(answers.get(1));
                C.setText(answers.get(2));
                D.setText(answers.get(3));
                playGame.setVisible(false);
            }

            if (event.getSource() == A) {
                //if the user picks A and its correct:
                if (game.getCorrectIndex() == 0) {    
                    questionLabel.setText("Correct!");
                    
                    //update counters
                    game.addNumCorrect();
                    game.addCount();
                } else {    //if wrong
                    questionLabel.setText("Incorrect! The answer was " + game.getAnswer());
                    
                    //doesn't update correct counter
                    game.addCount();
                }
                //allows user to move onto next question
                next.setVisible(true);
                answersPanel.setVisible(false);
                add(next);
            }

            if (event.getSource() == B) {
                if (game.getCorrectIndex() == 1) {
                    questionLabel.setText("Correct!");
                    game.addNumCorrect();
                    game.addCount();
                } else {
                    questionLabel.setText("Incorrect! The answer was " + game.getAnswer());
                    game.addCount();
                }
                next.setVisible(true);
                answersPanel.setVisible(false);
                add(next);
            }

            if (event.getSource() == C) {
                if (game.getCorrectIndex() == 2) {
                    questionLabel.setText("Correct!");
                    game.addNumCorrect();
                    game.addCount();
                } else {
                    questionLabel.setText("Incorrect! The answer was " + game.getAnswer());
                    game.addCount();
                }
                next.setVisible(true);
                answersPanel.setVisible(false);
                add(next);
            }

            if (event.getSource() == D) {
                if (game.getCorrectIndex() == 3) {
                    questionLabel.setText("Correct!");
                    game.addNumCorrect();
                    game.addCount();
                } else {
                    questionLabel.setText("Incorrect! The answer was " + game.getAnswer());
                    game.addCount();
                }
                next.setVisible(true);
                answersPanel.setVisible(false);
                add(next);
            }

            if (event.getSource() == next) {
                //if the game is over (9 questions), tell user their score
                if (game.isGameOver()) {
                    A.setVisible(false);
                    B.setVisible(false);
                    C.setVisible(false);
                    D.setVisible(false);
                    questionLabel.setText("Game is complete. You're a " + game.getRanking() + 
                        ". You got "+ game.getNumCorrect() + " correct.");
                    next.setVisible(false);
                } else {    //otherwise generate another question
                    next.setVisible(false);
                    answersPanel.setVisible(true);
                    String question = game.generateMovieQuestion();
                    questionLabel.setText(question);

                    LinkedList<String> answers = game.getChoices();
                    //System.out.println(answers);
                    A.setText(answers.get(0));
                    B.setText(answers.get(1));
                    C.setText(answers.get(2));
                    D.setText(answers.get(3));
                }
            }
        }
    }
}

