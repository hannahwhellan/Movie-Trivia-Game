
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;
import java.util.Vector;
/**
 * Category class maintains a collection of Movies.
 *
 * @ Gaby Inchoco and Hannah Whellan
 * @version April 30, 2019
 */
public class Category {
    // Instance Variables
    private Hashtable<String, LinkedList<Movie>> categoriesHT;

    /**
     * Constructor for objects of class Category
     */
    public Category(String titleFile) {
        // Instantiates 
        categoriesHT = new Hashtable<String, LinkedList<Movie>>();

        try {
            Scanner scan = new Scanner(new File(titleFile));

            while (scan.hasNext()) {
                //read in file with all movie file names in it
                String scriptTitle = scan.nextLine();
                Movie m = new Movie(scriptTitle);
                String category = m.getCategory();

                //System.out.println(m);
                if (!categoriesHT.containsKey(category)) {
                    categoriesHT.put(category, new LinkedList<Movie>());
                }
                categoriesHT.get(category).add(m);
            }
        } catch (IOException ex) {
            System.out.println(ex + "File not found");
        }

    }

    /**
     * Returns the category hashtable of linked lists with all the movies in one category
     * 
     * @return hashtable the hashtbale 
     */
    public Hashtable<String, LinkedList<Movie>> getCategoryHT() {
        return categoriesHT;
    }

    /**
     * Returns the series of movies that are in a category
     * 
     * @return LinkedList<Movie> the list of all the movies in the category
     */
    public LinkedList<Movie> getSeries(String category) {
        return categoriesHT.get(category);
    }

    /**
     * Returns the character that speaks the most throughout the series
     * 
     * @return the chracter name who speaks the most in the category
     */
    public String getMostUsedCharacter(String category) {
        //find the most use character in all the movies
        Hashtable<String, Integer> mainCharactersHT = new Hashtable<String, Integer>();
        LinkedList<Movie> moviesList = categoriesHT.get(category);
        for(Movie m : moviesList) {
            //System.out.println(m);
            String character = m.mainCharacter();
            int speaking = m.getCharHashtable().get(character);
            mainCharactersHT.put(character, speaking);
        }

        //compare their values
        int highest = 0;
        String main = " ";
        for (String character : mainCharactersHT.keySet()) {
            if (mainCharactersHT.get(character) > highest) {
                highest = mainCharactersHT.get(character);
                main = character;
            }
        }

        //return the greatest one
        return main;
    }

    /**
     * Returns the formatted string representation of a category object
     *
     * @return String the formatted string representation of a category object
     */
    public String toString() {
        String result = "{\n";

        for (String s : categoriesHT.keySet()) {
            result += s + " = ";

            result += categoriesHT.get(s);

            result += ",\n";
        }
        result += "}";
        return result;
    }

    /**
     * Main method to test all of the methods in the class
     */
    public static void main(String[] args) {
        Category c1 = new Category("ScriptFilenames.txt");
        System.out.println(c1);
    }
}
