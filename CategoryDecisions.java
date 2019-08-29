//Gaby Inchoco, Hannah Whellan, Jacquelyn Cai
//Return of Blockbuster!

import javafoundations.*;
/**
 * CategoryDecision contains the decision tree to help the user 
 * decide what category of movies to play in.
 *
 * @author Jacquelyn Cai
 * @version 5/16/19
 */
public class CategoryDecisions {
    private LinkedBinaryTree<String> tree;
    private LinkedBinaryTree<String> current;   //which tree you are currently in

    /**
     * Sets up decision tree
     */
    public CategoryDecisions()
    {
        String e1 = "Do you want light-hearted movies (y/n)?";
        String e2 = "Do you want Disney movies?";
        String e3 = "Do you want Science Fiction movies?";
        String e4 = "Do you want Star Wars movies?";
        String e5 = "Do you want Star Trek movies";
        String e6 = "Disney";
        String e7 = "Dreamworks";
        String e8 = "Star Wars";
        String e9 = "Star Trek";
        String e10 = "Lord of the Rings";
        String e11 = "Indiana Jones";

        LinkedBinaryTree<String> n6 = new LinkedBinaryTree<String>(e6); //disney
        LinkedBinaryTree<String> n7 = new LinkedBinaryTree<String>(e7); //dreamworks
        LinkedBinaryTree<String> n8 = new LinkedBinaryTree<String>(e8); //star wars
        LinkedBinaryTree<String> n9 = new LinkedBinaryTree<String>(e9); //star trek
        LinkedBinaryTree<String> n10 = new LinkedBinaryTree<String>(e10);   //lord of rings
        LinkedBinaryTree<String> n11 = new LinkedBinaryTree<String>(e11);   //Indiana Jones

        LinkedBinaryTree<String> n5 = new LinkedBinaryTree<String>(e5, n10, n9);    //startrek?
        LinkedBinaryTree<String> n4 = new LinkedBinaryTree<String>(e4, n5, n8); //star wars?
        LinkedBinaryTree<String> n3 = new LinkedBinaryTree<String>(e3, n11, n4);    // science fiction?
        LinkedBinaryTree<String> n2 = new LinkedBinaryTree<String>(e2, n7, n6); //disney?
        
        tree = new LinkedBinaryTree<String> (e1, n3, n2);   //lighthearted?
        current = tree;
    }

    /**
     * Gets the current tree
     * 
     * @return LinkedBinaryTree<String> the current LBT
     */
    public LinkedBinaryTree<String> getCurrent() {
        return current;
    }

    /**
     * Returns the whole tree
     * 
     * @return LinkedBinaryTree<String> the tree
     */
    public LinkedBinaryTree<String> getTree() {
        return tree;
    }

    /**
     * If the yes button is pressed then go to the right of the tree
     */
    public void yesPressed() {
        if (current.size() > 1) {
            current = current.getRight();
        }
    }

    /**
     * If the no button is pressed then go to the left of the tree
     */
    public void noPressed() {
        if (current.size() > 1) {
            current = current.getLeft();
        }
    }

    /**
     * Returns the element at the current node of the tree
     * 
     * @return String the current element 
     */
    public String getQuestion() {
        return current.getRootElement();
    }
}
