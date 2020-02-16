package JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonParser {

    public static ArrayList<String> getSubjectId(JSONObject jsonObject, String givenName, String familyName) { //получатель id по фамилии и имени автора
        ArrayList <String> id = new ArrayList<String>();
        JSONArray records = jsonObject.getJSONArray("records");
        ArrayList<JSONObject> jsonLD = new ArrayList<JSONObject>();
        ArrayList<JSONArray> authors = new ArrayList<JSONArray>();

        for (int i =0; i < records.length(); i++){
            if(records.getJSONObject(i).get("jsonld") instanceof JSONObject ) {
                jsonLD.add(records.getJSONObject(i).getJSONObject("jsonld"));
                authors.add(records.getJSONObject(i).getJSONObject("jsonld").getJSONArray("author"));
           }
        }
       for (int i = 0; i<authors.size(); i++){
           for (int j = 0; j < authors.get(i).length(); j++){

               if ((authors.get(i).getJSONObject(j).get("givenName").toString().equals(givenName))&& (authors.get(i).getJSONObject(j).get("familyName").toString().equals(familyName))){
                   try {
                       id.add(authors.get(i).getJSONObject(j).get("id").toString());
                   } catch (Exception e){

                   }
               }
           }
       }
        return id;
    }
}
