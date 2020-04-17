import JSONReciever.JsonReciever;
import RDFMolecule.RDFMolecule;

import JSONParser.JsonParser;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class test {

    public static void main(String[] args) {
    //    List<Pair> pairs = new ArrayList<Pair>(Arrays.asList(new Pair("girl","ann"),new Pair("home", "academic street")));

        //System.out.println(JsonReciever.getJsonFromSpringer("1c79096220d1b24680d951e4474440d5", "name", "A Marchuk"));
        JSONObject myObject = JsonReciever.getJsonFromSpringer( "1c79096220d1b24680d951e4474440d5", "name", "A Marchuk");
        //System.out.println(myObject);
        ArrayList <String> id = JsonParser.getSubjectId(myObject,"Alexander", "Marchuk");
        System.out.println(id);
        System.out.println(JsonReciever.getTripletsFropmSpringer(id.get(0)));
        RDFMolecule Alex = new RDFMolecule("Alexander Marchuk");
        Alex.addPairsFromJson(JsonReciever.getTripletsFropmSpringer(id.get(0)));
        System.out.println(Alex);
       /* RDFMolecule Zinaida = new RDFMolecule("Zinaida Apanovich");
        JSONObject myObject1 = JsonReciever.getJsonFromSpringer( "1c79096220d1b24680d951e4474440d5", "name", "Zinaida Apanovich");
        ArrayList <String> id1 = JsonParser.getSubjectId(myObject1,"Zinaida", "Apanovich");
        Zinaida.addPairsFromJson(JsonReciever.getTripletsFropmSpringer(id1.get(0)));
        System.out.println(Zinaida);
        System.out.println(Zinaida.hashCode());
        System.out.println(Zinaida.getPredicate(1));
        System.out.println(Zinaida.getPredicate(2));
*/

    }
}
