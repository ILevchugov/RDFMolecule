package RDFMoleculeCreator;

import JSONReciever.JsonReciever;
import RDFMolecule.RDFMolecule;
import Transliterator.Transliterator;
import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SpringerCreatorFromJSON {
    private final String apiKey;
    private final String queryType;

    public SpringerCreatorFromJSON(String apiKey, String queryType) {
        this.apiKey = apiKey;
        this.queryType = queryType;
    }

    public RDFMolecule createMolecule(String familyName, String name) {
        String creator = familyName + ", " + name.charAt(0);
        RDFMolecule rdfMolecule = new RDFMolecule(familyName);
        JSONObject jsonObject = JsonReciever.getJsonFromSpringer(apiKey, queryType, 1, familyName);
        JSONArray records = jsonObject.getJSONArray("records");
        for (int i = 0; i < records.length(); i++) {
            RDFMolecule article = new RDFMolecule("Article");
            article.addPairsFromJson(records.getJSONObject(i));
            boolean hasQueryCreator = false;
            System.out.println("креаторы");
            for (int j = 0; j < records.getJSONObject(i).getJSONArray("creators").length(); j++) {
                System.out.println(records.getJSONObject(i).getJSONArray("creators").getJSONObject(j).get("creator"));
                if (records.getJSONObject(i).getJSONArray("creators").getJSONObject(j).getString("creator").contains(creator)) {
                    hasQueryCreator = true;
                }
            }
            if (hasQueryCreator) {
                rdfMolecule.addPair(new Pair<>("hasArticle", article));
            }
        }

        return rdfMolecule;
    }

    /*
    списиок молекул по различным вариациям написания на латинице
     */
  /*  public RDFMolecule createMoleculesByNameOnRus(String familyName, String name, String secondName) {
        Set<RDFMolecule> rdfMoleculesArticles = new HashSet<>();
        ArrayList<StringBuilder> engFamilyVariants = Transliterator.transliterate(familyName);
        name = Transliterator.transliterate(name).get(0).toString();
        secondName = Transliterator.transliterate(secondName).get(0).toString();
        RDFMolecule rdfMolecule = new RDFMolecule(familyName);
        for (int k = 0; k < engFamilyVariants.size(); k++) {
            System.out.println(engFamilyVariants.get(k).toString());
            JSONObject jsonObject = JsonReciever.getJsonFromSpringer(apiKey, queryType, engFamilyVariants.get(k).toString());
            JSONArray records = jsonObject.getJSONArray("records");
            for (int i = 0; i < records.length(); i++) {
                RDFMolecule article = new RDFMolecule("Article");
                article.addPairsFromJson(records.getJSONObject(i));
                boolean hasQueryCreator = false;
                System.out.println("креаторы");
                for (int j = 0; j < records.getJSONObject(i).getJSONArray("creators").length(); j++) {
                    System.out.println(records.getJSONObject(i).getJSONArray("creators").getJSONObject(j).get("creator"));
                    if (isValidCreator(records.getJSONObject(i).getJSONArray("creators").getJSONObject(j).getString("creator"), engFamilyVariants.get(k).toString(), name, secondName)) {
                        hasQueryCreator = true;
                    }
                }
                if (hasQueryCreator) {
                    rdfMoleculesArticles.add(article);
                }
            }
        }

        for (RDFMolecule article : rdfMoleculesArticles) {
            rdfMolecule.addPair(new Pair<>("hasArticle", article));
        }

        return rdfMolecule;
    }*/

    public RDFMolecule createMoleculesByNameOnRus(String familyName, String name, String secondName) {
        Set<RDFMolecule> rdfMoleculesArticles = new HashSet<>();
       // ArrayList<StringBuilder> engNameVariants = Transliterator.transliterate(familyName+ " " + name);
        ArrayList<StringBuilder> engFamilyVariants = Transliterator.transliterate(familyName);
        name = Transliterator.transliterate(name).get(0).toString();
        secondName = Transliterator.transliterate(secondName).get(0).toString();
        RDFMolecule rdfMolecule = new RDFMolecule(familyName);
        for (int k = 0; k < engFamilyVariants.size(); k++) {
            System.out.println(engFamilyVariants.get(k).toString());
            int start = 1;
            while (true) {
                System.out.println("nomer + " + start);
                JSONObject jsonObject = JsonReciever.getJsonFromSpringer(apiKey, queryType, start, engFamilyVariants.get(k).toString());
                start = start + 50;
                if (!jsonObject.has("result")) {
                    continue;
                }
                int result = jsonObject.getJSONArray("result").getJSONObject(0).getInt("recordsDisplayed");
                if (result == 0) break;
                JSONArray records = jsonObject.getJSONArray("records");
                for (int i = 0; i < records.length(); i++) {
                    RDFMolecule article = new RDFMolecule("Article");
                    article.addPairsFromJson(records.getJSONObject(i));
                    boolean hasQueryCreator = false;
                    System.out.println("креаторы");
                    for (int j = 0; j < records.getJSONObject(i).getJSONArray("creators").length(); j++) {
                        System.out.println(records.getJSONObject(i).getJSONArray("creators").getJSONObject(j).get("creator"));
                        if (isValidCreator(records.getJSONObject(i).getJSONArray("creators").getJSONObject(j).getString("creator"), engFamilyVariants.get(k).toString(), name, secondName)) {
                            hasQueryCreator = true;
                        }
                    }
                    if (hasQueryCreator) {
                        rdfMoleculesArticles.add(article);
                    }
                }
            }
        }

        for (RDFMolecule article : rdfMoleculesArticles) {
            rdfMolecule.addPair(new Pair<>("hasArticle", article));
        }

        return rdfMolecule;
    }


    boolean isValidCreator(String creatorFromJSON, String familyName, String name, String secondName) {
        String[] FIO = creatorFromJSON.split(" ");

        if (FIO.length == 1) {
            if (FIO[0].contains(familyName)) return true;
        }

        if (FIO.length == 2) {
            if (FIO[0].contains(familyName) && FIO[1].contains(String.valueOf(name.charAt(0)))) return true;
        }

        if (FIO.length == 3) {
            if (FIO[0].contains(familyName) && FIO[1].contains(String.valueOf(name.charAt(0))) && FIO[2].contains(String.valueOf(secondName.charAt(0))))
                return true;
        }
        return false;
    }

    public static void test_creator() {
        SpringerCreatorFromJSON springerCreatorFromJSON = new SpringerCreatorFromJSON("1c79096220d1b24680d951e4474440d5", "name");
        RDFMolecule SpringerRdfMolecule = springerCreatorFromJSON.createMoleculesByNameOnRus("Апанович", "Зинаида", "Евгеньевич");
        // ArrayList<RDFMolecule> apanovichMolecules = springerCreatorFromJSON.createMoleculesByNameOnRus("Загорулько Юрий ");
        System.out.println(SpringerRdfMolecule.getObjects("hasArticle").size());
        ArrayList<Object> articles = SpringerRdfMolecule.getObjects("hasArticle");
        for (int i = 0; i < articles.size(); i++) {
            RDFMolecule article = (RDFMolecule) articles.get(i);
            System.out.println(article.getObjects("title").get(0));
            System.out.println(article.getObjects("creators"));
        }

    }
}
