package RDFMolecule;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.javatuples.Pair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@EqualsAndHashCode
@ToString
public class RDFMolecule {

    private String subject; //субъект
    private List<Pair<String, String>> predAndObj; //множество пар предикат объект

    public RDFMolecule(String subject){
        this.subject = subject;
        predAndObj = new ArrayList<>();
    }

    public RDFMolecule(String subject, List<Pair<String, String>> predAndObj){
        this.subject = subject;
        this.predAndObj = predAndObj;
    }

    public void addPair(Pair<String, String> pair){
        predAndObj.add(pair);
    }

    public void remove(int index){
        predAndObj.remove(index);
    }

    public Pair<String, String> getPair(int index){
        return predAndObj.get(index);
    }

    public Object getPredicate(int index){
        return this.getPair(index).getValue0();
    }

    public Object getObject(int index){
        return this.getPair(index).getValue1();
    }
    public int getSize(){
        return predAndObj.size();
    }

    public void addPairsFromJson(JSONObject JSON){
        Iterator<String> keys = JSON.keys();
        while (keys.hasNext()){
            String key = keys.next();
            Pair<String, String> pair = new Pair<>(key, JSON.get(key).toString());
            this.addPair(pair);
        }
    }

}