import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Arrays;
/**
 * Reads in a text file script and manages a list of characters, a hashtable of word occurrences,
 * an instance variable of its genre, and the number of occurrences each character speaks 
 * and other instance variables that give 
 * information about the movie script and can be used in questions for the user.

 *
 * @Hannah Whellan and Gaby Inchoco
 * @05/16/2019
 */
public class Movie {
    //instance variables
    private Hashtable<String, Integer> characterHashtable;
    private Hashtable<String, Integer> wordFreqHashtable;
    private String category;
    private String firstCharacter;
    private int mainCharacterFreq;
    private int leastCharacterFreq;
    private int higestWordFreq;
    private String[] quote1Array;
    private String[] quote2Array;
    private String title;
    private String[] characterArray;
    private Vector<String> wordsVector;

    /**
     * Constructor that reads in a movie script and instantiates instance variables
     * 
     * @param String filename the script of the movie
     */
    public Movie(String filename) {
        try {
            Scanner scan = new Scanner( new File(filename));
            //getting hardcoded characters
            String characterString = scan.nextLine();
            characterArray = characterString.split(",");

            //gets hardcoded category
            category = scan.nextLine();

            //hardcode 2 quotes
            //index 0  of array is character who says quote and index 1 is the quote
            String quote1String = scan.nextLine(); //quote1
            quote1Array = quote1String.split(",");
            String quote2String = scan.nextLine(); //quote2
            quote2Array = quote2String.split(",");

            //instantiate instance variables
            characterHashtable = new Hashtable<String, Integer>();
            wordFreqHashtable = new Hashtable<String, Integer>();
            wordsVector = new Vector<String>();

            //filling characterHashtable with character name and 0
            for (int i = 0; i < characterArray.length; i++) {
                characterHashtable.put(characterArray[i],0);
            }

            int k = 0;
            while (scan.hasNext()) {
                String word = scan.next();
                //strip words of punctuation 
                word = word.replaceAll("[(.,!?:;*/)]","");

                //filling characterHashtable with frequencies of speaking
                if (characterHashtable.containsKey(word) || characterHashtable.containsKey(word.toLowerCase())) {
                    //find first character who speaks
                    k++;
                    if (k == 1) {
                        firstCharacter = word;
                    }
                    int charFreqAlready = characterHashtable.get(word) + 1;
                    characterHashtable.put(word, charFreqAlready);
                }
                //filling wordFreqHashtable
                else if (!word.equals(word.toUpperCase()) || word.equals("A") || word.equals("I")) {
                    word.toLowerCase();
                    if (!wordFreqHashtable.containsKey(word)) {
                        wordFreqHashtable.put(word, 1);
                        wordsVector.add(word); //for trivia question random words
                    }
                    if (wordFreqHashtable.containsKey(word)) {
                        int frequencyAlready = wordFreqHashtable.get(word) + 1;
                        wordFreqHashtable.put(word, frequencyAlready);
                    }
                }
            }
            removeCommonWords();
            //set title instance variable
            title = filename.replace("Script_", "");
            title = title.replaceAll(".txt", "");
            title = title.replaceAll("_", "");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns the an array of the major characters in a movie
     * 
     * @return String[] characters
     */
    public Vector<String> getCharacters() {
        Vector<String> result = new Vector<String>();
        for (String s: characterArray) {
            result.add(s);
        }
        return result;
    }

    /**
     * Helper method to remove 100 most common words from wordFreqHashtable
     */
    private void removeCommonWords() {
        try {
            Scanner scan = new Scanner(new File("common100words.txt"));
            while (scan.hasNext()) {
                String word = scan.nextLine();
                wordFreqHashtable.remove(word.toUpperCase());
                wordFreqHashtable.remove(word.toLowerCase());
                wordFreqHashtable.remove(word);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns the wordsVector instance varibable
     * 
     * @return Vector<String> the wordsVector 
     */
    public Vector<String> getWordsVector() {
        return wordsVector;
    }

    /**
     * Returns the character frequency hashtable instance variable
     * 
     * @return Hashtable<String, Integer> the hashtable instance variable
     */
    public Hashtable<String, Integer> getCharHashtable() {
        return characterHashtable;
    }

    /**
     * Returns the word frequency hashtable instance variable
     * 
     * @return Hashtable<String, Integer> the hashtable instance variable
     */
    public Hashtable<String, Integer> getWordFreqHashtable() {
        return wordFreqHashtable;
    }

    /**
     * reutrns the category instance variable
     * 
     * @reutrn String the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * reutrns the first character that speaks instance variable
     * 
     * @reutrn String the first character
     */
    public String getFirstCharacter() {
        return firstCharacter;
    }

    /**
     * reutrns the most used word in the script by traversing through the Word Frequency Hashtable
     * 
     * @reutrn String the most used word
     */
    public String mostUsedWord() {
        //design decision to only return one word even if more than one word have same highest freq
        String mostUsed = "";
        int highestWord = 0;
        for (String word : wordFreqHashtable.keySet()) {
            //word has greater frequency
            if (wordFreqHashtable.get(word) > highestWord) {
                highestWord = wordFreqHashtable.get(word); //reassign highestWord int
                mostUsed = word; //set in case there is only one highest word
            }
        }
        higestWordFreq = highestWord;
        return mostUsed;
    }

    /**
     * returns the number of times the most used word appears in the script
     * 
     * @return int the highestWordFreq instance variable
     */
    public int getMostUsedWordFreq() {
        return higestWordFreq;
    }

    /**
     * reutrns the character that speaks the most in the script by traversing 
     * through the Character Frequency Hashtable
     * 
     * @reutrn String the character that speaks the most
     */
    public String mainCharacter() {
        //design decision to only return one character even if more than one character have same highest freq
        String mainChar = "";
        int highestChar = 0;
        for (String charcater : characterHashtable.keySet()) {
            //word has greater frequency
            if (characterHashtable.get(charcater) > highestChar) {
                highestChar = characterHashtable.get(charcater); //reassign highestWord int
                mainChar = charcater; //set in case there is only one highest word
            }
        }
        mainCharacterFreq = highestChar;
        return mainChar;
    }

    /**
     * reutrns the character that speaks the least in the script by traversing 
     * through the Character Frequency Hashtable
     * 
     * @reutrn String the character that speaks the least
     */
    public String leastCharacter() {
        //design decision to only return one character even if more than one character have same highest freq
        String leastChar = "";
        int lowestChar = 100;
        for (String charcater : characterHashtable.keySet()) {
            //word has greater frequency
            if (characterHashtable.get(charcater) < lowestChar) {
                lowestChar = characterHashtable.get(charcater); //reassign highestWord int
                leastChar = charcater; //set in case there is only one highest word
            }
        }
        leastCharacterFreq = lowestChar;
        return leastChar;
    }

    /**
     * returns the number of times the character who speaks the most speaks in the script
     * 
     * @return int the mainCharacterFreq instance variable
     */
    public int getMainCharacterFreq() {
        return mainCharacterFreq;
    }

    /**
     * returns the number of times the character who speaks the least speaks in the script
     * 
     * @return int the leastCharacterFreq instance variable
     */
    public int getLeastCharacterFreq() {
        return leastCharacterFreq;
    }

    /**
     * Returns a the character who says the first hardcoded quote
     * 
     * @return String the character.
     */
    public String getQuote1Character() {
        return quote1Array[0];
    }

    /**
     * Returns a the quote that is the first hardcoded quote
     * 
     * @return String the quote.
     */
    public String getQuote1Quote() {
        return quote1Array[1];
    }

    /**
     * Returns a the character who says the second hardcoded quote
     * 
     * @return String the character.
     */
    public String getQuote2Character() {
        return quote2Array[0];
    }

    /**
     * Returns a the quote that is the second hardcoded quote
     * 
     * @return String the quote.
     */
    public String getQuote2Quote() {
        return quote2Array[1];
    }

    /**
     * Returns the total number of words used in the movie (without the most common 100 words)
     * 
     * @return int the total number of words in the movie script
     */
    public int getMovieSize() {
        return wordFreqHashtable.size();
    }   

    /**
     * Returns a formatted string of the title of the movie
     * 
     * @return String the title of the movie.
     */
    public String toString() {
        return title;
    }

    /**
     * Main method for testing the other methods of the class
     */
    public static void main (String[] args) {
        Movie swNewHope = new Movie("Script_Star Wars_ A New Hope.txt");
        System.out.println(swNewHope);
        System.out.println(swNewHope.getCharHashtable());
        System.out.println(swNewHope.getWordFreqHashtable());
        System.out.println(swNewHope.getWordFreqHashtable().get("father")); //15
        System.out.println(swNewHope.getCategory());  //Star Wars
        System.out.println(swNewHope.getFirstCharacter()); //THREEPIO
        System.out.println(swNewHope.mostUsedWord()); //the
        System.out.println(swNewHope.getMostUsedWordFreq());
        System.out.println(swNewHope.getWordFreqHashtable().get("the")); //null
        System.out.println(swNewHope.mainCharacter()); //LUKE
        System.out.println(swNewHope.getMainCharacterFreq());
        System.out.println(swNewHope.getWordFreqHashtable().get("your")); //null
        System.out.println(swNewHope.getWordFreqHashtable().size()); //4215

        Movie lotr1 = new Movie("Script_Lord of the Rings_ Fellowship of the Ring.txt");
        System.out.println(lotr1);
        System.out.println(lotr1.getCharHashtable());
        System.out.println(lotr1.getWordFreqHashtable());
        System.out.println(lotr1.getWordFreqHashtable().get("father")); //6
        System.out.println(lotr1.getCategory());  //Lord of the Rings
        System.out.println(lotr1.getFirstCharacter()); //Galadriel
        System.out.println(lotr1.mostUsedWord()); //Frodo
        System.out.println(lotr1.getMostUsedWordFreq()); //198
        System.out.println(lotr1.mainCharacter()); //Gandalf
        System.out.println(lotr1.getMainCharacterFreq());//168
        System.out.println(lotr1.getWordFreqHashtable().size()); 

        Movie atckClones = new Movie("Script_Star Wars_ Attack of the Clones.txt");
        System.out.println(atckClones.getWordFreqHashtable().containsKey("he"));

        Movie dragon = new Movie("Script_How to Train Your Dragon.txt");
        System.out.println(dragon.getCharHashtable());
        System.out.println(dragon.leastCharacter());
        System.out.println(dragon.getLeastCharacterFreq());
    }
}
