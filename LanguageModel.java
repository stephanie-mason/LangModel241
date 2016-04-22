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
import java.util.Scanner;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;


public class LanguageModel {
  HashMap<String,Double> p;         // maps ngrams to conditional probabilities
  ArrayList<String> vocab;          // stores the unique words in the input text
  int maxOrder;                     // maximum n-gram order to compute
  java.util.Random generator;       // a random number generator object
  // Constructor
  //  - this.p maps ngrams (h,w) to the the maximum likelihood estimates
  //    of P(w|h) for all n-grams up to maxOrder
  //    Only non-zero probabilities should be stored in this map
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
    this.maxOrder = maxOrder;
    this.generator = generator;
    File inputFile = new File(textFilename);
    Scanner input = makeScanner(inputFile);
    HashMap<String,Integer> ngramCounts = new HashMap<String,Integer>();
    HashMap<String,Integer> historyCounts = new HashMap<String,Integer>();
    ArrayList<String> vocab = new ArrayList<String>();

    //get n-gram/history counts & add them to hashmapw
    System.out.println("Getting counts...");
    getCounts(input, ngramCounts, historyCounts, vocab, maxOrder);

    //print vocab to an output file (if applicable)
    if(vocabFilename != null) {
      saveVocab(vocab, vocabFilename);
    }



    //  - If countsFilename is non-null, the ngram counts words are printed to countsFilename, in order
    //    each line has the ngram, then a tab, then the number of times that ngram appears
    //    these should be printed in case-insensitive ascending alphabetic order by the n-grams

    //


    /*
    System.out.println("p: " + p.get("some key"));
    System.out.println("first vocab: " + vocab.get(0));
    System.out.println("Max Order: " + maxOrder);
    System.out.println("Generator: " + generator.nextInt());
    */
    return;
  }


  //*** Accessors ***//

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
  //  - Call randomcurrWord to draw each new word
  public String randomCompletion( ArrayList<String> history, int order ) {
    return null;
  }

  //*** Private Helper Methods ***//

  // Create a new Scanner, handling exceptions
  private Scanner makeScanner(File inputFile) {
    try {
      Scanner input = new Scanner(inputFile);
      return input;
    } catch (FileNotFoundException e) {
      System.out.println ("Input file not found");
      System.exit(1);
    }
    return null;
  }

  // Check if a word is already in an ArrayList
  private boolean notInList(ArrayList<String> vocab, String currWord) {
    for (int i=0; i < vocab.size(); i++){
      if (currWord.equals(vocab.get(i))) {
        return false;
      }
    }
    return true;
  }

  // Save vocabulary list to an external file (if applicable)
  private void saveVocab(ArrayList<String> list, String vocabFilename) {
    try {
      PrintStream out = new PrintStream(new FileOutputStream(vocabFilename));
      for (int i = 0; i < list.size(); i++) {
        out.println(list.get(i));
      }
      out.close();
    } catch (FileNotFoundException e){
      System.out.println ("Unable to open file " + vocabFilename);
      System.exit(1);
    }
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

  // randomcurrWord
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
  private String randomcurrWord( ArrayList<String> history, int order) {
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
  private void getCounts(Scanner input, HashMap<String,Integer> ngramCounts, HashMap<String,Integer> historyCounts, ArrayList<String> vocab, int maxOrder) {
    ArrayList<String> trainingArray = new ArrayList<String>();

    String currWord = new String();

    //Get everything out of the input file
    while (input.hasNext()){
      currWord = input.next();
      //BUILD TRAINING ARRAY
      trainingArray.add(currWord);

      //BUILD VOCAB LIST
      // only add the word if it doesn't already appear in the list
      if (notInList(vocab, currWord)) {
        vocab.add(currWord);
      }
    }

    //ALPHABETIZE VOCAB LIST
    Collections.sort(vocab);

    //CREATE N-GRAMS
    //for each order from 2 up to max order, scan the trainingArray (ie if maxorder 4, iterate 2, 3, and 4 orders)
    for (int n = 2; n <= maxOrder; n++) {

      int placeholder = 0;
      //while you are between <s> and </s>, create n-grams & histories
      while(!trainingArray.get(placeholder).equals("</s>")) {
        ArrayList<String> currGram = new ArrayList<String>();
        ArrayList<String> currHist = new ArrayList<String>();
        //create the current n-grams to search for
        for(int i = placeholder; i <= placeholder + n - 1; i++) {
          currGram.add(trainingArray.get(i));
          // create history as well
          if (i < placeholder + n - 1) {
            currHist.add(trainingArray.get(i));
          }
        }

        //add the current n-gram to the the count as the key and initialize count to 0
        ngramCounts.put(arrayToString(currGram), 0);
        historyCounts.put(arrayToString(currHist), 0);
        placeholder++;
      }

      System.out.println("nGram Keys for n = " + n + " : " + ngramCounts.keySet());
      System.out.println("ngram Size: " + ngramCounts.size());
      System.out.println("History Keys for n = " + n + " : " + historyCounts.keySet());
      System.out.println("History Size: " + historyCounts.size());
      System.out.println("Counting occurences...");

      //this is the current n-gram... so scan the rest of the file for this n-gram
        //if you find it, increment count associated with current key
      //after finishing scanning, the next (order) words are the current n-gram. so on so forth. ONLY scan n-grams across lines, but count across entire file


      placeholder ++;

    }
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
