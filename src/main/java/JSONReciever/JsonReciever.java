package JSONReciever;

import RDFMolecule.RDFMolecule;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class JsonReciever {

    public static JSONObject getJsonFromSpringerNature(String apiKey, String queryType, String queryField) {

        final String URI = "http://api.springernature.com/meta/v2/jsonld?";
        queryField = queryField.replace(" ", "%20");
        HttpGet request = new HttpGet(URI + "q=" + queryType + ":" + queryField + "&" + "api_key=" + apiKey + "&" + "p=100"); //p = page length

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return new JSONObject(result);
            }
        } catch (IOException e) {
            System.out.println("Wrong http connection");
        }
        return new JSONObject();
    }

    public static JSONObject getJsonFromSpringer(String apiKey, String queryType, int record, String queryField) {

        final String URI = "http://api.springer.com/metadata/json?";
        queryField = queryField.replace(" ", "%20");
        HttpGet request = new HttpGet(URI + "s=" + record + "&q=" + queryType + ":" + queryField + "&" + "api_key=" + apiKey + "&" + "p=50"); //p = page length
        System.out.println(request);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject = new JSONObject(result);
                } catch (Exception e){
                }
                return jsonObject;
            }
        } catch (IOException e) {
            System.out.println("Wrong http connection");
        }
        return new JSONObject();
    }


    //http://scigraph.springernature.com/person.011355766010.27.json
    public static JSONObject getTripletsFropmSpringer(String id) {
        HttpGet request = new HttpGet("http://scigraph.springernature.com/" + id.substring(3) + ".json"); //p = page length
        // System.out.println("http://scigraph.springernature.com/" + id.substring(3, id.length())+".json");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return new JSONObject(result);
            }
        } catch (IOException e) {
            System.out.println("Wrong http connection");
        }
        return new JSONObject();
    }

   /* public static void main(String[] args) {
        JSONObject jsonObject = getJsonFromSpringer("1c79096220d1b24680d951e4474440d5",  "name", 1, "Apanovich Zinaida");
       // System.out.println(jsonObject.getJSONArray("records").getJSONObject(0));
        RDFMolecule article = new RDFMolecule("Article");
        JSONArray jsonArray = new JSONArray();
        article.addPairsFromJson(jsonObject.getJSONArray("records").getJSONObject(0));
        RDFMolecule Marchuk = new RDFMolecule("Marchuk");
        Marchuk.addPair(new Pair<>("hasArticle", article));
        RDFMolecule MarchuksArticle = (RDFMolecule) Marchuk.getObject(0);
        System.out.println(Marchuk);
        System.out.println(MarchuksArticle.getObjects("title").get(0));
    }

    */
}



