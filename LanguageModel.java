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
import java.util.Iterator;
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
  //  - If countsFilename is non-null, the ngram counts words are printed to countsFilename, in order
  //    each line has the ngram, then a tab, then the number of times that ngram appears
  //    these should be printed in case-insensitive ascending alphabetic order by the n-grams
  // Notes:
  //  - File saving should be accomplished by calls to saveVocab and saveCounts

  public LanguageModel( String textFilename, int maxOrder, java.util.Random generator, String vocabFilename, String countsFilename ) {
    this.maxOrder = maxOrder;
    this.generator = generator;
    this.vocab = new ArrayList<String>();
    File inputFile = new File(textFilename);
    Scanner input = makeScanner(inputFile);
    HashMap<String,Integer> ngramCounts = new HashMap<String,Integer>();
    HashMap<String,Integer> historyCounts = new HashMap<String,Integer>();

    //get n-gram/history counts & add them to hashmapw
    getCounts(input, ngramCounts, historyCounts, this.vocab, this.maxOrder);

    //print vocab to an output file (if applicable)
    if(vocabFilename != null) {
      saveVocab(this.vocab, vocabFilename);
    }

    //print counts to an output file (if applicable)

    //convert counts to probabilities
    this.p = convertCountsToProbabilities(ngramCounts, historyCounts);

    ArrayList<String> testString = stringToArray("without any");

    System.out.println("Random next word: " + randomNextWord(testString, 4));

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
    ArrayList<String> currHistory = history;
    String currString = "";

    //calls randomnextword -> so do that method to complete this one

    //while not </s> or <fail> ...
      //call draw next word function to get a word
      // add this word to currString with a space
      // add this word to currHistory (the copy of the input history)

    //append </s> or <fail> currString

    return currString;
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

  // randomNextWord
  //  - A new word is drawn (see assignment description for the algorithm to use)
  //  - If no word follows that history for the specified order, return "<fail>"
  private String randomNextWord( ArrayList<String> history, int order) {
    double draw = this.generator.nextDouble();
    double cumulativeSum = 0;

    for (int i = 0; i < this.vocab.size(); i++) {
      String nextWord = vocab.get(i);
      String gramToCheck = arrayToString(history) + " " + nextWord;
      if (this.p.get(gramToCheck) != null)
        cumulativeSum = cumulativeSum + this.p.get(gramToCheck);

      if (cumulativeSum > draw) {
        return nextWord;
      }
    }

    if (!this.p.containsKey(history)){
      return "<fail>";
    }


    return vocab.get(vocab.size()-1);
  }

  // getCounts
  // Read input file and map n-grams/histories to their counts
  private void getCounts(Scanner input, HashMap<String,Integer> ngramCounts, HashMap<String,Integer> historyCounts, ArrayList<String> vocab, int maxOrder) {
    ArrayList<String> linesArray = new ArrayList<String>();
    ArrayList<String> currLine = new ArrayList<String>();
    String currWord = new String();

    //Convert scanner file to an ArrayList
    while(input.hasNextLine()) {
      linesArray.add(input.nextLine());
    }

    //Build ArrayLists and HashMaps
    for (int i = 0; i < linesArray.size(); i++) {
      currLine = stringToArray(linesArray.get(i));

      // BUILD N-GRAMS & HISTORIES
      for (int n = 2; n <= maxOrder; n++) {
        int placeholder = 0;
        while(placeholder + n < currLine.size()) {
          ArrayList<String> currGram = new ArrayList<String>();
          ArrayList<String> currHist = new ArrayList<String>();
          for (int w = placeholder; w < placeholder + n; w++) {

            if (w < currLine.size())
              currGram.add(currLine.get(w));

            if (w < placeholder + n - 1)
              currHist.add(currLine.get(w));
          }

          //Add to nGramCounts or increment the count if its already there
          incrementHashMap(ngramCounts, arrayToString(currGram));
          incrementHashMap(historyCounts, arrayToString(currHist));

          placeholder++;

          // Final Fencepost -- Last N-Gram needs to include </s>
          if (placeholder + n == currLine.size()) {
            ArrayList<String> lastGram = new ArrayList<String>();
            ArrayList<String> lastHist = new ArrayList<String>();
            for (int w = placeholder; w < placeholder + n; w++) {

              if (w < currLine.size())
                lastGram.add(currLine.get(w));

              if (w < placeholder + n - 1)
                lastHist.add(currLine.get(w));
            }
            incrementHashMap(ngramCounts, arrayToString(lastGram));
            incrementHashMap(historyCounts, arrayToString(lastHist));
          }
        }
      }

      // MAKE ARRAYS
      for (int j = 0; j < currLine.size(); j++) {
        currWord = currLine.get(j);


        //BUILD VOCAB LIST
        // only add the word if it doesn't already appear in the list
        if (notInList(vocab, currWord)) {
          vocab.add(currWord);
        }
      }
    }

    //ALPHABETIZE VOCAB LIST
    Collections.sort(vocab);

    return;
  }

  // convertCountsToProbabilities
  //  - this.p.get(ngram) contains the conditional probability P(w|h) for ngram (h,w)
  //      only non-zero probabilities are stored in this.p
  private HashMap<String,Double> convertCountsToProbabilities(HashMap<String,Integer> ngramCounts, HashMap<String,Integer> historyCounts) {
    HashMap<String,Double> p = new HashMap<String,Double>();
    for (HashMap.Entry<String, Integer> curr : ngramCounts.entrySet()) {
      String nGram = curr.getKey();
      Integer nGramCount = curr.getValue();

      ArrayList<String> nGramArray = stringToArray(nGram);
      ArrayList<String> histArray = new ArrayList<String>();

      int histSize = nGramArray.size() - 1; //size of current n-gram

      //pull the history from current n-gram (ie all but last word in the n-gram)
      for (int i = 0; i < histSize; i++) {
        histArray.add(nGramArray.get(i));
      }

      //now get the count on that history
      String currHist = arrayToString(histArray);
      int historyCount = historyCounts.get(currHist);

      //calculate probability
      double probability = (double)(ngramCounts.get(nGram))/(double)(historyCounts.get(currHist));
      p.put(nGram,probability);
    }

      return p;
  }

  // incrementHashMap
  //  - If key was already in map, map.get(key) returns 1 more than it did before
  //  - If key was not in map, map.get(key) returns 1
  private void incrementHashMap(HashMap<String,Integer> map, String key) {
    int count;
    if (map.containsKey(key)) {
      count = map.get(key);
      count++;
      map.put(key, count);
    }
    else {
      map.put(key, 1);
    }
    return;
  }

  // Static Methods

  // arrayToString
  //  - sequence is returned in string form, each element joined by a single space
  //  - If sequence was length 0, the empty string is returned
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
  //  - An ArrayList is returned containing the words in s
  public static ArrayList<String> stringToArray(String s) {
    return new ArrayList<String>(java.util.Arrays.asList(s.split(" ")));
  }
}
