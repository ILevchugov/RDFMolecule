package RDFMolecule;


import org.javatuples.Pair;

import java.util.ArrayList;
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

    public void show(){
        System.out.println("Subject : " + subject);
        System.out.println(predAndObj);

    }

}
