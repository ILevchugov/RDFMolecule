package RDFMoleculeCreator;

import RDFMolecule.RDFMolecule;
import org.javatuples.Pair;

import java.io.*;

/*создание RDF молекулы на основе данных из файла*/
public class ElibraryCreator {

    public static RDFMolecule createMoleculeFromFile(String filePath, String name) {
        RDFMolecule rdfMolecule = new RDFMolecule(name);
        try {
            File file = new File(filePath);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                RDFMolecule article = new RDFMolecule("Article");
                article.addPair(new Pair<>("title", line));
                rdfMolecule.addPair(new Pair<>("hasArticle", article));
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rdfMolecule;
    }

}
