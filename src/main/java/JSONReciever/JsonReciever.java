package JSONReciever;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class JsonReciever {

    public static JSONObject getJsonFromSpringer(String apiKey, String queryType, String queryField){

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
            }
        catch (IOException e){
            System.out.println("Wrong http connection");
        }
        return new JSONObject();
    }


    //http://scigraph.springernature.com/person.011355766010.27.json
    public static JSONObject getTripletsFropmSpringer(String id) {
        HttpGet request = new HttpGet("http://scigraph.springernature.com/" + id.substring(3)+".json"); //p = page length
       // System.out.println("http://scigraph.springernature.com/" + id.substring(3, id.length())+".json");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request)){
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                        return new JSONObject(result);
                }
        }
        catch (IOException e){
            System.out.println("Wrong http connection");
        }
        return new JSONObject();
    }
}



