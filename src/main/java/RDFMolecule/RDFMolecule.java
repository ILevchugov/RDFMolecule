package RDFMolecule;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@EqualsAndHashCode
@ToString
public class RDFMolecule {

    private final String subject; //субъект
    private final List<Pair<String, String>> predAndObj; //множество пар предикат объект

    public RDFMolecule(String subject) {
        this.subject = subject;
        predAndObj = new ArrayList<>();
    }

    public RDFMolecule(String subject, List<Pair<String, String>> predAndObj) {
        this.subject = subject;
        this.predAndObj = predAndObj;
    }

    public void addPair(Pair<String, String> pair) {
        predAndObj.add(pair);
    }

    public void remove(int index) {
        predAndObj.remove(index);
    }

    public Pair<String, String> getPair(int index) {
        return predAndObj.get(index);
    }

    public String getSubject() {
        return subject;
    }

    public String getPredicate(int index) {
        return this.getPair(index).getValue0();
    }

    public String getObject(int index) {
        return this.getPair(index).getValue1();
    }

    public int getSize() {
        return predAndObj.size();
    }

    public void addPairsFromJson(@NotNull JSONObject JSON) {
        Iterator<String> keys = JSON.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Pair<String, String> pair = new Pair<>(key, JSON.get(key).toString());
            this.addPair(pair);
        }
    }
}
