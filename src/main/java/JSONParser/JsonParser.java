package JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;


public class JsonParser {

    public static ArrayList<String> getSubjectId(JSONObject jsonObject, String givenName, String familyName) { //получатель id по фамилии и имени автора
        ArrayList<String> id = new ArrayList<>();
        JSONArray records = jsonObject.getJSONArray("records");
        ArrayList<JSONObject> jsonLD = new ArrayList<>();
        ArrayList<JSONArray> authors = new ArrayList<>();

        for (int i = 0; i < records.length(); i++) {
            if (records.getJSONObject(i).get("jsonld") instanceof JSONObject) {
                jsonLD.add(records.getJSONObject(i).getJSONObject("jsonld"));
                authors.add(records.getJSONObject(i).getJSONObject("jsonld").getJSONArray("author"));
            }
        }
        for (JSONArray author : authors) {
            for (int j = 0; j < author.length(); j++) {
                if ((author.getJSONObject(j).get("givenName").toString().equals(givenName)) && (author.getJSONObject(j).get("familyName").toString().equals(familyName))) {
                    try {
                        id.add(author.getJSONObject(j).get("id").toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return id;
    }

    public static JSONArray getAtricles(JSONObject jsonObject) {
        JSONArray records = jsonObject.getJSONArray("records");
        JSONArray articles = new JSONArray();
        for (int i = 0; i < records.length(); i++) {
            JSONObject record = records.getJSONObject(i);
            JSONArray creators = record.getJSONArray("creators");
            for (int j = 0; j < creators.length(); j++) {
                if (creators.getString(j).contains("name")) {
                    articles.put(record);
                    break;
                }
            }
        }
        return articles;
    }
}
