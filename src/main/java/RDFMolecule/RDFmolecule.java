package RDFMolecule;


import org.javatuples.Pair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RDFmolecule {

    private String subject; //субъект
    private List<Pair> predAndObj; //множество пар предикат объект

    public RDFmolecule(String subject){
        this.subject = subject;
        predAndObj = new ArrayList<Pair>();
    }

    public RDFmolecule(String subject, List<Pair> predAndObj){
        this.subject = subject;
        this.predAndObj = predAndObj;
    }

    public void addPair(Pair pair){
        predAndObj.add(pair);
    }

    public void remove(int index){
        predAndObj.remove(index);
    }

    public Pair getPair(int index){
        return predAndObj.get(index);
    }

    public void addPairsFromJson(JSONObject JSON){
        Iterator<String> keys = JSON.keys();
        while (keys.hasNext()){
            String key = keys.next();
            Pair pair = new Pair(key, JSON.get(key));
            this.addPair(pair);
        }
    }

    public void show(){
        System.out.println("Subject : " + subject);
        System.out.println(predAndObj);

    }

}
