import NLP.TextComparator;
import RDFMolecule.RDFMolecule;
import RDFMoleculeCreator.ElibraryCreator;
import RDFMoleculeCreator.SpringerCreatorFromJSON;
import Translator.YandexTranslator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleApp {
    static Pattern pattern = Pattern.compile(
            "[" +                   //начало списка допустимых символов
                    "а-яА-ЯёЁ" +    //буквы русского алфавита
                    "\\d" +         //цифры
                    "\\s" +         //знаки-разделители (пробел, табуляция и т.д.)
                    "\\p{Punct}" +  //знаки пунктуации
                    "]" +                   //конец списка допустимых символов
                    "*");


    public static void main(String[] args) throws IOException {
        System.out.println("введите фамилию");
        Scanner sc = new Scanner(System.in);
        String familyName = sc.nextLine();
        System.out.println(familyName);
        String name = sc.nextLine();
        String secondName = sc.nextLine();

        RDFMolecule ELibraryRDFMolecule = ElibraryCreator.createMoleculeFromFile(familyName + ".txt", familyName);


        SpringerCreatorFromJSON springerCreatorFromJSON = new SpringerCreatorFromJSON("1c79096220d1b24680d951e4474440d5", "name");
        //RDFMolecule SpringerRdfMolecule = springerCreatorFromJSON.createMolecule("Zagorulko" , "Yuri");


        RDFMolecule SpringerRdfMolecule = springerCreatorFromJSON.createMoleculesByNameOnRus(familyName, name, secondName);


        ArrayList<Object> ArticlesFromSpringer = SpringerRdfMolecule.getObjects("hasArticle");
        ArrayList<Object> ArticlesFromElibrary = ELibraryRDFMolecule.getObjects("hasArticle");

        Set<RDFMolecule> commonArticles = new HashSet<>();
        ArrayList<Object> unrecognizedArticles = new ArrayList<>(ArticlesFromSpringer);

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
                Matcher matcher = pattern.matcher(elibLibraryName);
                if (matcher.matches()) elibLibraryName = YandexTranslator.translate("ru", elibLibraryName);

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
    }
}
