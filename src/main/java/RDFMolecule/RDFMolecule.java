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

    private final String subject; //субъект
    private final List<Pair<String, Object>> predAndObjects; //множество пар предикат объект

    public RDFMolecule(String subject) {
        this.subject = subject;
        predAndObjects = new ArrayList<>();
    }

    public RDFMolecule(String subject, List<Pair<String, Object>> predAndObj) {
        this.subject = subject;
        this.predAndObjects = predAndObj;
    }

    public void addPair(Pair<String, Object> pair) {
        predAndObjects.add(pair);
    }

    public void remove(int index) {
        predAndObjects.remove(index);
    }

    public Pair<String, Object> getPair(int index) {
        return predAndObjects.get(index);
    }

    public String getSubject() {
        return subject;
    }

    public String getPredicate(int index) {
        return this.getPair(index).getValue0();
    }

    public Object getObject(int index) {
        return this.getPair(index).getValue1();
    }

    public ArrayList<Object> getObjects(String predicate) {
        ArrayList<Object> objects = new ArrayList<>();
        for (Pair<String, Object> predAndObj : predAndObjects) {
            if (predAndObj.getValue0().equals(predicate)) {
                objects.add(predAndObj.getValue1());
            }
        }
        return objects;
    }

    public int getSize() {
        return predAndObjects.size();
    }

    public void addPairsFromJson(JSONObject JSON) {
        Iterator<String> keys = JSON.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Pair<String, Object> pair = new Pair<>(key, JSON.get(key).toString());
            this.addPair(pair);
        }
    }

    public void show() {
        System.out.println("RDF molecule: " + subject);
        for (Pair<String, Object> predAndObj : predAndObjects) {
            System.out.println("predicate: " + "<" + predAndObj.getValue0() + "> " + " object: " + "<" + predAndObj.getValue1() + ">");
        }
    }

    public String getDescription() {
        StringBuilder result = new StringBuilder();
        for (Pair<String, Object> predAndObj : predAndObjects) {
            result.append("predicate: " + "<" + predAndObj.getValue0() + "> " + " object: " + "<" + predAndObj.getValue1() + ">" + System.lineSeparator());
        }
        return "RDF molecule: " + subject + System.lineSeparator() + result;

    }
}

