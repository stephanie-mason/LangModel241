/*
 * LanguageModel.java
 *
 * Implements methods for training a language model from a text file,
 * writing a vocabulary, and randomly completing sentences
 *
 * Students may only use functionality provided in the packages
 *     java.lang
 *     java.util
 *     java.io
 *
 * Use of any additional Java Class Library components is not permitted
 *
 * Melakenashe Tefera
 * Stephanie Mason
 * January 2016
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LanguageModel {
    HashMap<String,Double> p;         // maps ngrams to conditional probabilities
    ArrayList<String> vocab;          // stores the unique words in the input text
    int maxOrder;                     // maximum n-gram order to compute
    java.util.Random generator;       // a random number generator object

    // Constructor

    // LanguageModel
    // Preconditions:
    //    - textFilename is the name of a plaintext training file (inputFile)
    //    - maxOrder is the maximum n-gram order for which to estimate counts (the 'setting' of the program)
    //  - generator is java.util.Random object (generates random number based on input seed, which is static set to 5 in Program1.java)
    //  - vocabFilename is the name where the vocab file will be written
    //        vocabFilename can also be null (output file for vocabulary)
    //  - countsFilename is the name where the counts will be written
    //        countsFilename can also be null (output file for counts)
    // Postconditions:
    //  - this.p maps ngrams (h,w) to the the maximum likelihood estimates
    //    of P(w|h) for all n-grams up to maxOrder
    //    Only non-zero probabilities should be stored in this map
    //  - this.vocab contains each word appearing in textFilename exactly once
    //    in case-insensitive ascending alphabetic order
    //  - this.maxOrder is assigned maxOrder
    //  - this.generator is assigned generator
    //  - If vocabFilename is non-null, the vocabulary words are printed to it, one per line, in order
    //  - If countsFilename is non-null, the ngram counts words are printed to countsFilename, in order
    //    each line has the ngram, then a tab, then the number of times that ngram appears
    //    these should be printed in case-insensitive ascending alphabetic order by the n-grams
    // Notes:
    //  - n-gram and history counts should be computed with a call to getCounts
    //  - File saving should be accomplished by calls to saveVocab and saveCounts
    //  - convertCountsToProbabilities should be used to then get the probabilities
    //  - If opening any file throws a FileNotFoundException, print to standard error:
    //        "Error: Unable to open file " + filename
    //        (where filename contains the name of the problem file)
    //      and then exit with value 1 (i.e. System.exit(1))

    public LanguageModel( String textFilename, int maxOrder, java.util.Random generator, String vocabFilename, String countsFilename ) {
      this.p = new HashMap<String,Double>(); //ngrams are keys, probabilites are floating point number value
      this.vocab = new ArrayList<String>(); //stores a list of vocabulary appearing in inputFile
      this.maxOrder = maxOrder;
      this.generator = generator;
      //  - If vocabFilename is non-null, the vocabulary words are printed to it, one per line, in order
      // make sure to look at notes above

      //  - If countsFilename is non-null, the ngram counts words are printed to countsFilename, in order
      //    each line has the ngram, then a tab, then the number of times that ngram appears
      //    these should be printed in case-insensitive ascending alphabetic order by the n-grams


      //DELETE
      System.out.println("p: " + p.get("some key"));
//      System.out.println("first vocab: " + vocab.get(0));
      System.out.println("Max Order: " + maxOrder);
      System.out.println("Generator: " + generator.nextInt());
      return;
    }

    // Accessors

    // getMaxOrder
    // Preconditions:
    //  - None
    // Postconditions:
    //  - this.maxOrder is returned
    public int getMaxOrder() {
        return this.maxOrder;
    }

    // randomCompletion
    // Preconditions:
    //  - history contains an initial history to complete
    //  - order is the n-gram order to use when completing the sentence
    // Postconditions:
    //  - history must not be modified (i.e. make a copy of it)
    //  - Starting with an empty string, until </s> or <fail> is drawn:
    //    1) Draw a new word w according to P(w|h)
    //    2) Append a space and then w to the string you're accumulating
    //    3) w is added to the history h
    //   Once </s> or <fail> is reached, append it to the string and return the string
    // Notes:
    //  - Call randomNextWord to draw each new word
    public String randomCompletion( ArrayList<String> history, int order ) {
        return null;
    }

    // Private Helper Methods

    // saveVocab
    // Preconditions:
    //  - vocabFilename is the name where the vocab file will be written
    //        vocabFilename can also be null
    // Postconditions:
    //  - this.vocab contains each word appearing in textFilename exactly once
    //    in case-insensitive ascending alphabetic order
    //  - If opening the file throws a FileNotFoundException, print to standard error:
    //        "Error: Unable to open file " + vocabFilename
    //      and then exit with value 1 (i.e. System.exit(1))
    private void saveVocab(String vocabFilename) {
		return;
    }

    // saveCounts
    // Preconditions:
    //  - countsFilename is the name where the counts will be written
    //     countsFilename can also be null
    //  - ngramCounts.get(ngram) returns the number of times ngram appears
    //     ngrams with count 0 are not included
    // Postconditions:
    //  - If countsFilename is non-null, the ngram counts words are printed to countsFilename,
    //     each line has the ngram, then a tab, then the number of times that ngram appears
    //     ngrams should be printed in case-insensitive ascending alphabetic order
    // Notes:
    //  - If opening the file throws a FileNotFoundException, print to standard error:
    //       "Error: Unable to open file " + countsFilename
    //      and then exit with value 1 (i.e. System.exit(1))
    private void saveCounts(String countsFilename, HashMap<String,Integer> ngramCounts) {
		return;
    }

    // randomNextWord
    // Preconditions:
    //  - history is the history on which to condition the draw
    //  - order is the order of n-gram to use
    //      (i.e. no more than n-1 history words)
    //  - this.generator is the generator passed to the constructor
    // Postconditions:
    //  - A new word is drawn (see assignment description for the algorithm to use)
    //  - If no word follows that history for the specified order, return "<fail>"
    // Notes:
    //  - The nextDouble() method draws a random number between 0 and 1
    //  - ArrayList has a subList method to return an array slice
    private String randomNextWord( ArrayList<String> history, int order) {
		return "";
    }

    // getCounts
    // Preconditions:
    //  - input is an initialized Scanner object associated with the text input file
    //  - ngramCounts is an empty (but non-null) HashMap
    //  - historyCounts is an empty (but non-null) HashMap
    //  - vocab is an empty (but non-null) ArrayList
    //  - maxOrder is the maximum order n-gram for which to extract counts
    // Postconditions:
    //  - ngramCounts.get(ngram) contains the number of times that ngram appears in the input
    //      ngram must be 2+ words long (e.g. "<s> i")
    //  - historyCounts.get(history) contains the number of times that ngram history appears in the input
    //      histories can be a single word (e.g. "<s>")
    //  - vocab contains each word (token) in the input file exactly once, in case-insensitive ascending alphabetic order
    // Notes:
    //  - You may find it useful to implement helper function incrementHashMap and use it
    private void getCounts(java.util.Scanner input, HashMap<String,Integer> ngramCounts, HashMap<String,Integer> historyCounts, ArrayList<String> vocab, int maxOrder) {
        return;
    }

    // convertCountsToProbabilities
    // Preconditions:
    //  - ngramCounts.get(ngram) contains the number of times that ngram appears in the input
    //  - historyCounts.get(history) contains the number of times that ngram history appears in the input
    // Postconditions:
    //  - this.p.get(ngram) contains the conditional probability P(w|h) for ngram (h,w)
    //      only non-zero probabilities are stored in this.p
    private void convertCountsToProbabilities(HashMap<String,Integer> ngramCounts, HashMap<String,Integer> historyCounts) {
        return;
    }

    // incrementHashMap
    // Preconditions:
    //  - map is a non-null HashMap
    //  - key is a key that may or may not be in map
    // Postconditions:
    //  - If key was already in map, map.get(key) returns 1 more than it did before
    //  - If key was not in map, map.get(key) returns 1
    // Notes
    //  - This method is useful, but optional
    private void incrementHashMap(HashMap<String,Integer> map, String key) {
        return;
    }

    // Static Methods

    // arrayToString
    // Preconditions:
    //  - sequence is a List (e.g. ArrayList) of Strings
    // Postconditions:
    //  - sequence is returned in string form, each element joined by a single space
    //  - If sequence was length 0, the empty string is returned
    // Notes:
    //  - Already implemented for you
    public static String arrayToString(List<String> sequence) {
        java.lang.StringBuilder builder = new java.lang.StringBuilder();
        if( sequence.size() == 0 ) {
            return "";
        }
        builder.append(sequence.get(0));
        for( int i=1; i<sequence.size(); i++ ) {
            builder.append(" " + sequence.get(i));
        }
        return builder.toString();
    }

    // stringToArray
    // Preconditions:
    //  - s is a string of words, each separated by a single space
    // Postconditions:
    //  - An ArrayList is returned containing the words in s
    // Notes:
    //  - Already implemented for you
    public static ArrayList<String> stringToArray(String s) {
        return new ArrayList<String>(java.util.Arrays.asList(s.split(" ")));
    }
}
