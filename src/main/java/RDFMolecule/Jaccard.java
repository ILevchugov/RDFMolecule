package RDFMolecule;

import lombok.Getter;
import org.javatuples.Pair;

import java.util.HashSet;
import java.util.Set;

public class Jaccard {
    private final RDFMolecule A;
    private final RDFMolecule B;

    @Getter
    private double similarityMeasure;
    @Getter
    private Set<Pair<String, Object>> intersectionSet;
    @Getter
    private Set<Pair<String, Object>> unionSet;

    public Jaccard(RDFMolecule A, RDFMolecule B) {
        this.A = A;
        this.B = B;

        this.intersectionSet = new HashSet<>();
        this.unionSet = new HashSet<>();

        initIntersectionSet();
        initUnionSet();

        this.similarityMeasure = intersectionSet.size() / (double) unionSet.size();
    }


    private void initIntersectionSet() {
        for (int i = 0; i < A.getSize(); i++) {
            for (int j = 0; j < B.getSize(); j++) {
                if (A.getPair(i).equals(B.getPair(j))) {
                    intersectionSet.add(A.getPair(i));
                }
            }
        }
    }

    private void initUnionSet() {
        for (int i = 0; i < A.getSize(); i++) {
            unionSet.add(A.getPair(i));
        }

        for (int j = 0; j < B.getSize(); j++) {
            unionSet.add(B.getPair(j));
        }
    }

}
