import java.util.LinkedList;
import java.util.Hashtable;
import java.util.*;

/**
 * TriviaQuestion class prints out questions and randomly generates 
 * multiple choice answers to the screen for the user to select.
 * These questions have to do with random movies within a series that the  user chooses.
 *
 * @Hannah Whellan and Gaby Inchoco and Jacqueline Cai
 * @5/15/2019
 */
public class TriviaQuestion {
    // Instance variables
    private String seriesName;
    private Category allSeries;
    private LinkedList<Movie> moviesList;
    private int count, numCorrect;
    private int answerIndex;
    private String answer;
    private LinkedList<String> choices;
    private Hashtable<String, Integer> usedWords;
    private Random randy;

    /**
     * Constructor for objects of class TriviaQuestion
     * 
     * @param String the series name the user wants to play in
     */
    public TriviaQuestion(String seriesName) {
        this.seriesName = seriesName;
        allSeries = new Category("ScriptFilenames.txt");
        moviesList = allSeries.getSeries(seriesName);
        count = 0;
        numCorrect = 0;
        usedWords = new Hashtable<String, Integer>();
        choices = new LinkedList<String>();
        randy = new Random();
        //there are only 3 Lord of the Rings movies so we are only adding the title to the 
        //choices that are presented in the Lord of the Rings sereis questions.
        if (seriesName.equals("Lord of the Rings")) {
            choices.add("The Hobbit");
        }
    }

    /**
     * Returns the correct answer in the intChoices
     * 
     * @return String the answer instance variable
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Returns index of the correct answer in the intChoices
     *
     * @return int the answer index variable
     */
    public int getCorrectIndex() {
        return answerIndex;
    }

    /**
     * Returns the choices for a given question
     * 
     * @return LinkedList<String> multiple choice options
     */
    public LinkedList<String> getChoices() {
        return choices;
    }

    /**
     * Returns a question about the words with the highest frequency
     * 
     * @return String the question
     */
    public String generateMostUsedWordQuestion() {
        //System.out.println(moviesList);
        int index = randy.nextInt(moviesList.size());
        Movie m = moviesList.get(index);
        String word = m.mostUsedWord();
        answer = Integer.toString(m.getMostUsedWordFreq());
        
        //populate choices
        choices = new LinkedList<String>();
        while(choices.size() < 3) {
            int option = randy.nextInt(Integer.parseInt(answer)*5);
            if (!choices.contains(Integer.toString(option))) {
                choices.add(Integer.toString(option));
            }
        }
        //place the answer in a random slot
        int index2 = randy.nextInt(4);
        answerIndex = index2;
        choices.add(index2,answer); 

        return word + " is the most used word in " + m + ".\n" +
        "How many times does the word \"" + word + "\" appear in " + m + "?";
    }

    /**
     * Returns a question about the words with the highest frequency
     * 
     * @return String the question
     */
    public String mainCharacterQuestion() {
        //generate random 
        int index = randy.nextInt(moviesList.size());
        Movie m = moviesList.get(index);
        answer = m.mainCharacter();
        
        //populate choices
        choices = new LinkedList<String>();
        Vector<String> characters = m.getCharacters();
        characters.remove(answer);
        while(choices.size() < 3) {
            int option = randy.nextInt(characters.size());
            if (!choices.contains(characters.get(option))) {
                choices.add(characters.get(option));
            }
        }
        //place correct answer in random slot
        int index2 = randy.nextInt(4);
        answerIndex = index2;
        choices.add(index2,answer); 
        //result.removeLast();

        return "What character speaks the most in " + m + "?";
    }

    /**
     * Returns a question about the words with the highest frequency
     * 
     * @return String the question
     */
    public String quoteQuestion() {
        //generate random index
        int index = randy.nextInt(moviesList.size());
        Movie m = moviesList.get(index);
        int index3 = randy.nextInt(2);
        //pick quote
        String quote;
        if (index3 == 0) {
            answer = m.getQuote1Character();
            quote = m.getQuote1Quote();
        }
        else {
            answer = m.getQuote2Character();
            quote = m.getQuote2Quote();
        }
        //populate choices
        choices = new LinkedList<String>();
        Vector<String> characters = m.getCharacters();
        characters.remove(answer);

        while(choices.size() < 3) {
            int option = randy.nextInt(characters.size());
            if (!choices.contains(characters.get(option))) {
                choices.add(characters.get(option));
            }
        }
        //place answer at random slot
        int index2 = randy.nextInt(4);
        answerIndex = index2;
        choices.add(index2,answer); 
        //result.removeLast();

        return "Who says \"" + quote + "\" ?";
    }

    /**
     * Returns a question about the words with the highest frequency
     * 
     * @return String the question
     */
    public String seriesQuoteQuestion() {
        //generate random index and movie
        int index = randy.nextInt(moviesList.size());
        Movie m = moviesList.get(index);
        int index3 = randy.nextInt(2);
        String quote;
        //pick quote
        if (index3 == 0) {
            answer = m.toString();
            quote = m.getQuote1Quote();
        }
        else {
            answer = m.toString();
            quote = m.getQuote1Quote();
        }
        
        //populate choices
        choices = new LinkedList<String>();
        LinkedList<Movie> newMoviesList = moviesList;
        //System.out.println("The list we are trying fix:" + newMoviesList);
        newMoviesList.remove(m);

        //System.out.println("The answer we are trying fix:" + answer);
        //System.out.println("The answer should be gone:" + newMoviesList);

        while(choices.size() < 3) {
            //System.out.println(choices.size());
            int option = randy.nextInt(newMoviesList.size());
            //System.out.println("The choice that we are tyring to fix:" + choices);
            if (!choices.contains(newMoviesList.get(option).toString())) {
                choices.add(newMoviesList.get(option).toString());
            }
        }
        //place answer 
        int index2 = randy.nextInt(4);
        answerIndex = index2;
        choices.add(index2,answer); 
        //result.removeLast();

        return "Which movie in the series has the quote \"" + quote + "\"?";
    }

    /**
     * Returns a String question based on the entire category
     *
     * @return String the question
     */
    public String generateCharSeriesQ() {
        answer = allSeries.getMostUsedCharacter(seriesName);
        //generate random answers
        choices = new LinkedList<String>();
        Vector<String> characters = moviesList.get(0).getCharacters();
        characters.remove(answer);
        while(choices.size() < 3) {
            int option = randy.nextInt(characters.size());
            if (!choices.contains(characters.get(option))) {
                choices.add(characters.get(option));
            }
        }
        //place answer
        int index2 = randy.nextInt(4);
        answerIndex = index2;
        choices.add(index2,answer);

        return "Which character speaks the most in the " +  seriesName  + " series?";
    }

    /**
     * Returns a question about the first character to speak
     * 
     * @return String the Question
     */
    public String generateFirstCharQ() {
        //generate random index and movie
        int index = randy.nextInt(moviesList.size()-1);
        Movie m = moviesList.get(index);
        answer = m.getFirstCharacter();
        
        //populate choices
        choices = new LinkedList<String>();
        Vector<String> characters = m.getCharacters();
        characters.remove(answer);
        while(choices.size() < 3) {
            int option = randy.nextInt(characters.size());
            if (!choices.contains(characters.get(option))) {
                choices.add(characters.get(option));
            }
        }
        //place answer
        int index2 = randy.nextInt(4);
        answerIndex = index2;
        choices.add(index2,answer); 

        return "Who is the first character to speak in " + m + "?";
    }

    /**
     * Returns a String question based on a random movie in the category and order is based on 
     * how many times the user has clicked the "next" button
     *
     * @return String the Question
     */
    public String generateMovieQuestion() {
        if (count == 0) {
            //highest freq word
            return generateMostUsedWordQuestion();
        } 
        if (count == 1) {
            //random word freq
            return generateWordFrequency();
        }
        if (count == 2) {
            return  mainCharacterQuestion();
        }
        if (count == 3) {
            //first character to speak
            return generateFirstCharQ();
        }
        if (count == 4) {
            return generateWordFrequency();
        }
        if (count == 5) {
            //random word freq
            return leastCharacterQuestion();
        }
        if (count == 6) {
            return seriesQuoteQuestion();
        }
        if (count == 7) {
            //character who speaks most in series
            return generateCharSeriesQ();
        }
        if (count == 8) {
            //random word freq
            return generateWordFrequency();
        }
        if (count == 9) {
            return quoteQuestion();  
        }
        return " ";
    }

    /**
     * Returns a questions based on the frequency of a random word in script
     * 
     * @return String the question
     */
    public String generateWordFrequency() {
        Random randy = new Random();
        int index = randy.nextInt(moviesList.size());
        Movie m = moviesList.get(index);

        int indexHT = randy.nextInt(m.getWordFreqHashtable().size());
        //System.out.println("index: "  + indexHT);
        String word = m.getWordsVector().get(indexHT);
        Vector<String> availableWords = m.getWordsVector();

        while(!isValidWord(word)) {
            indexHT = randy.nextInt(m.getWordFreqHashtable().size());
            word = m.getWordsVector().get(indexHT);
        }    
        //make sure you have not already asked this same question with the same word
        //if you have then choose a new word
        if (usedWords.contains(word)) {
            availableWords.remove(word);
            indexHT = randy.nextInt(availableWords.size());
            word = availableWords.get(indexHT);
        }
        usedWords.put(word,0);
        //NEEDED TO UPDATE CHOICES HERE

        answer = Integer.toString(m.getWordFreqHashtable().get(word));

        choices = new LinkedList<String>();
        //System.out.println("These are your starting choices" + choices);
        while(choices.size() < 3) {
            //System.out.println("These are your choices" + choices);
            int option = randy.nextInt(Integer.parseInt(answer)*5);
            if (!choices.contains(Integer.toString(option)) && !(Integer.toString(option).equals(answer))) {
                choices.add(Integer.toString(option));
            }
        }
        int index2 = randy.nextInt(4);
        answerIndex = index2;
        choices.add(answerIndex, answer); 

        //answer = Integer.toString(m.getWordFreqHashtable().get(word));

        return "How many times does the word \"" + word + "\" appear in " + m + "?";
    }

    /**
     * Helper method for making sure the word is an actual word and not a combination 
     * of two words from the script.
     * 
     * @param String the word
     * @return boolean true if the word is valid, false otherwise
     */
    private boolean isValidWord(String w) {
        return (w.equals(w.toLowerCase()) || w.equals(w.toUpperCase()));
    }

    /**
     * Returns a question about the words with the highest frequency
     * 
     * @return String the question
     */
    public String leastCharacterQuestion() {
        Random randy = new Random();
        int index = randy.nextInt(moviesList.size()-1);
        Movie m = moviesList.get(index);
        answer = m.leastCharacter();

        choices = new LinkedList<String>();
        Vector<String> characters = m.getCharacters();
        characters.remove(answer);
        characters.remove(m.mainCharacter()); //don't want main character to be an answer because the user already knows
        while(choices.size() < 3) {
            int option = randy.nextInt(characters.size());
            if (!choices.contains(characters.get(option))) {
                choices.add(characters.get(option));
            }
        }
        int index2 = randy.nextInt(4);
        answerIndex = index2;
        choices.add(answerIndex,answer); 

        return "What character speaks the least in " + m + "?";
    }

    /**
     * Sets the num of question played so far
     */
    public void addCount() {
        count++;
    }

    /**
     * Returns the number of questions played so far
     *
     * @return int the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the number of questions answered correctly
     */
    public void addNumCorrect() {
        numCorrect++;
    }

    /**
     * Returns the number of correctly answered questions
     * @return int the number of questions correct
     */
    public int getNumCorrect() {
        return numCorrect;
    }

    /**
     * Returns a string representation of the player's level
     *
     * @return String the ranking of the user
     */
    public String getRanking() {
        double grade = (double)numCorrect/count;
        if (grade >= 0 && grade < .3) {
            return "Film Novice";
        }
        if (grade <= .6 && grade > .3) { 
            return "Motion Feature Amateur";
        }
        else {
            return "Movie Expert";
        }
    }

    /**
     * Returns true if the game is over, false otherwise
     * 
     * @return boolean true if game is over
     */
    public boolean isGameOver() {
        return count >= 9;
    }

    /**
     * main method for testing the other methods of the class
     */
    public static void main(String[] args) {
        TriviaQuestion t = new TriviaQuestion("Disney");
        System.out.println(t.generateMostUsedWordQuestion());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());

        System.out.println(t.mainCharacterQuestion());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());

        System.out.println(t.generateCharSeriesQ());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());

        System.out.println(t.generateFirstCharQ());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());

        System.out.println(t.quoteQuestion());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());

        System.out.println(t.seriesQuoteQuestion());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());

        System.out.println(t.generateWordFrequency());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());

        System.out.println(t.leastCharacterQuestion());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());

        System.out.println(t.seriesQuoteQuestion());
        System.out.println(t.getChoices());
        System.out.println(t.getAnswer());
    }
}
