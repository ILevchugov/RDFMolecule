package Comparator;

import NLP.TextComparator;
import RDFMolecule.RDFMolecule;
import RDFMoleculeCreator.ElibraryCreator;
import RDFMoleculeCreator.SpringerCreatorFromJSON;
import Translator.YandexTranslator;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoleculesComparator {
    /*private static Pattern pattern = Pattern.compile(
            "[" +                   //начало списка допустимых символов
                    "а-яА-ЯёЁ" +    //буквы русского алфавита
                    "\\d" +         //цифры
                    "\\s" +         //знаки-разделители (пробел, табуляция и т.д.)
                    "\\p{Punct}" +  //знаки пунктуации
                    "]" +                   //конец списка допустимых символов
                    "*");*/

    private static String regex = "[а-яёА-ЯЁ]+";
    private static Pattern pattern = Pattern.compile(regex);

    @Getter
    private Set<RDFMolecule> commonArticles;
    @Getter
    private ArrayList<Object> unrecognizedArticles;

    public MoleculesComparator() {
        commonArticles = new HashSet<>();
        unrecognizedArticles = new ArrayList<>();
    }



    public Set<RDFMolecule> findAndCompare(String query) throws IOException {
        String[] FIO = query.split(" ");
        String familyName = FIO[0];
        String name = FIO[1];
        String secondName = FIO[2];

        RDFMolecule ELibraryRDFMolecule = ElibraryCreator.createMoleculeFromFile(familyName + ".txt", familyName);


        SpringerCreatorFromJSON springerCreatorFromJSON = new SpringerCreatorFromJSON("1c79096220d1b24680d951e4474440d5", "name");
        //RDFMolecule SpringerRdfMolecule = springerCreatorFromJSON.createMolecule("Zagorulko" , "Yuri");


        RDFMolecule SpringerRdfMolecule = springerCreatorFromJSON.createMoleculesByNameOnRus(familyName, name, secondName);


        ArrayList<Object> ArticlesFromSpringer = SpringerRdfMolecule.getObjects("hasArticle");
        ArrayList<Object> ArticlesFromElibrary = ELibraryRDFMolecule.getObjects("hasArticle");

        commonArticles = new HashSet<>();
        unrecognizedArticles = new ArrayList<>(ArticlesFromSpringer);

        System.out.println("Количество статей из Elibrary : " + ArticlesFromElibrary.size());
        System.out.println("Количество статей из SpringerLink : " + unrecognizedArticles.size());
        /*
        сравнение по предикату прямое, работает ток если на одном языке
         */
        for (int j = 0; j < ArticlesFromElibrary.size(); j++) {
            for (int i = 0; i < ArticlesFromSpringer.size(); i++) {
                RDFMolecule sprArticle = (RDFMolecule) ArticlesFromSpringer.get(i);
                RDFMolecule elibArticle = (RDFMolecule) ArticlesFromElibrary.get(j);
                String sprArticleName = (String) sprArticle.getObjects("title").get(0);
                String elibLibraryName = (String) elibArticle.getObjects("title").get(0);
                if (isCyrillic(elibLibraryName)) {
                   //System.out.println("перевожу: " + elibLibraryName);
                    elibLibraryName = YandexTranslator.translate("en", elibLibraryName);
                    //System.out.println("перевел" + elibLibraryName);
                }

                if (sprArticleName.toUpperCase().equals(elibArticle.getObjects("title").get(0))) {
                    commonArticles.add(sprArticle);
                    System.out.println("Распознана статья");
                    System.out.println(elibArticle.getObjects("title").get(0));
                    System.out.println("spr");
                    System.out.println(sprArticle.getObjects("title").get(0));
                    unrecognizedArticles.remove(sprArticle);
                } else if (TextComparator.compare(sprArticleName, elibLibraryName) > 0.8) {
                    commonArticles.add(sprArticle);
                    System.out.println("Распознана статья");
                    System.out.println(elibArticle.getObjects("title").get(0));
                    System.out.println("spr");
                    System.out.println(sprArticle.getObjects("title").get(0));
                    unrecognizedArticles.remove(sprArticle);
                }
            }

        }

        System.out.println("Распознные статьи");
        System.out.println(commonArticles.size());

        for (RDFMolecule common : commonArticles) {
            System.out.println(common.getObjects("title"));
            // System.out.println(rdfMolecule.getObjects("creators"));
        }

        System.out.println("Не распознанные :");
        for (int i = 0; i < unrecognizedArticles.size(); i++) {
            RDFMolecule rdfMolecule = (RDFMolecule) unrecognizedArticles.get(i);
            rdfMolecule.show();
            // System.out.println(rdfMolecule.getObjects("title"));
            // System.out.println(rdfMolecule.getObjects("creators"));
        }

        return commonArticles;
    }
    public static boolean isCyrillic(String s) {
        boolean result = false;
        for (char a : s.toCharArray()) {
            if (Character.UnicodeBlock.of(a) == Character.UnicodeBlock.CYRILLIC) {
                result = !result;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(isCyrillic("дфд"));
        System.out.println(isCyrillic("дфдававаfff"));
        System.out.println(isCyrillic("ffff f f f f"));
    }
}
