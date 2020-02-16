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

        System.out.println(JsonReciever.getJsonFromSpringer("1c79096220d1b24680d951e4474440d5", "name", "Alexander Marchuk"));
        JSONObject myObject = JsonReciever.getJsonFromSpringer( "1c79096220d1b24680d951e4474440d5", "name", "Alexander Marchuk");
        //System.out.println(myObject);
        ArrayList <String> id = JsonParser.getSubjectId(myObject,"Alexander", "Marchuk");
        System.out.println(id);
        System.out.println(JsonReciever.getTripletsFropmSpringer(id.get(1)));
        RDFmolecule Alex = new RDFmolecule("Alexander Marchuk");
        Alex.addPairsFromJson(JsonReciever.getTripletsFropmSpringer(id.get(1)));
        Alex.show();
        RDFmolecule Zinaida = new RDFmolecule("Zinaida");
        JSONObject myObject1 = JsonReciever.getJsonFromSpringer( "1c79096220d1b24680d951e4474440d5", "name", "Zinaida Apanovich");
        ArrayList <String> id1 = JsonParser.getSubjectId(myObject1,"Zinaida", "Apanovich");
        Zinaida.addPairsFromJson(JsonReciever.getTripletsFropmSpringer(id1.get(0)));
        Zinaida.show();
        System.out.println(Zinaida.getPair(2));


    }
}
