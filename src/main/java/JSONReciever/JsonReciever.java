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

    public static JSONObject getJsonFromSpringer(String api_key, String query, String query_field) throws IOException {

        final String URI = "http://api.springernature.com/meta/v2/jsonld?";
        query_field = query_field.replace(" ", "%20");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {

            HttpGet request = new HttpGet(URI + "q=" + query + ":" + query_field + "&" + "api_key=" + api_key + "&" + "p=100"); //p = page length
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        return jsonObject;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return new JSONObject();
    }


    //http://scigraph.springernature.com/person.011355766010.27.json
    public static JSONObject getTripletsFropmSpringer(String id) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("http://scigraph.springernature.com/" + id.substring(3, id.length())+".json");
        try {
            HttpGet request = new HttpGet("http://scigraph.springernature.com/" + id.substring(3, id.length())+".json"); //p = page length
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        return jsonObject;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return new JSONObject();
    }
}



