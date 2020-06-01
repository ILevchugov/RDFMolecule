package NLP;

import Translator.YandexTranslator;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextComparator {
    static  StanfordLemmatizer stanfordLemmatizer = new StanfordLemmatizer();

    public static double compare(String textA, String textB) {
        double treshold = 0;

        List<String> textALemmas = stanfordLemmatizer.lemmatize(textA.toLowerCase());
        List<String> textBLemmas = stanfordLemmatizer.lemmatize(textB.toLowerCase());

        Set <String> lemmasA = new HashSet<>();
        Set <String> lemmasB = new HashSet<>();

        for (String lemma:textALemmas) {
            lemmasA.add(lemma);
        }

        for (String lemma : textBLemmas) {
            lemmasB.add(lemma);
        }

        Set <String> intersectionSet = new HashSet<>();

        for (String lemmaA: lemmasA) {
            for (String lemmaB: lemmasB) {
                if (lemmaA.equals(lemmaB)) {
                    intersectionSet.add(lemmaA);
                }
            }
        }

        treshold = (intersectionSet.size()) / (double) lemmasA.size();

        //System.out.println(textALemmas);
       // System.out.println(textBLemmas);

        return treshold;
    }
}
