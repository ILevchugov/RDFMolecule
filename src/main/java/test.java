import JSONReciever.JsonReciever;
import RDFMolecule.RDFmolecule;

import JSONParser.JsonParser;
import org.javatuples.Pair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
    //    List<Pair> pairs = new ArrayList<Pair>(Arrays.asList(new Pair("girl","ann"),new Pair("home", "academic street")));

     //   System.out.println(JsonReciever.getJsonFrom("http://api.springernature.com/meta/v2/jsonld?", "1c79096220d1b24680d951e4474440d5", "name", "Zinaida Apanovich"));
        JSONObject myObject = JsonReciever.getJsonFromSpringer( "1c79096220d1b24680d951e4474440d5", "name", "Zinaida Apanovich");
        //System.out.println(myObject);
        ArrayList <String> id = new ArrayList<String>();
        id = JsonParser.getSubjectId(myObject,"Zinaida", "Apanovich");
        System.out.println(id);
        System.out.println(JsonReciever.getTripletsFropmSpringer(id.get(0)));


    }
}
