package NLP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class StopWordsRemover {
    public static String removeStopWords(String s) {
        String[] words = s.split(" ");
        ArrayList<String> wordsList = new ArrayList<String>();
        Set<String> stopWordsSet = new HashSet<String>();
        stopWordsSet.add("I");
        stopWordsSet.add("THIS");
        stopWordsSet.add("AND");
        stopWordsSet.add("TO");
        stopWordsSet.add("OR");
        stopWordsSet.add("A");

        for(String word : words)
        {
            String wordCompare = word.toUpperCase();
            if(!stopWordsSet.contains(wordCompare))
            {
                wordsList.add(word);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String str : wordsList){
            result.append(str+" ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeStopWords("i love Y"));
    }
}
