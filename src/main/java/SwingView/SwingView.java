package SwingView;

import Comparator.MoleculesComparator;
import RDFMolecule.RDFMolecule;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Set;

public class SwingView {
    private final JFrame mainFrame;
    private final JScrollPane RDFMoleculeInfoPane;
    private final JTextArea RDFMoleculeInfo;

    private final GridBagConstraints constraints;
    private final JTextField FIOfield;

    private final MoleculesComparator moleculesComparator;
    private final JButton find;

    public SwingView() {
        this.mainFrame = new JFrame();
        this.constraints = new GridBagConstraints();
        this.moleculesComparator = new MoleculesComparator();
        this.find = new JButton("Enter");
        RDFMoleculeInfoPane = new JScrollPane();
        RDFMoleculeInfo = new JTextArea("Распозные RDF-молекулы статьи:" + System.lineSeparator());
        FIOfield = new JTextField("Введите полное фио");

        RDFMoleculeInfoPane.setPreferredSize(new Dimension(600,600));


        mainFrame.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;

        FIOfield.setPreferredSize(new Dimension(600, 40));

        find.setPreferredSize(new Dimension(300, 30));

        RDFMoleculeInfoPane.setViewportView(RDFMoleculeInfo);
        RDFMoleculeInfo.setEditable(false);

        setGridItem(FIOfield, 0,0);
        setGridItem(find, 0, 1 );
        setGridItem(RDFMoleculeInfoPane,0,2);
        setButtonListener();
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setGridItem(Component component, int horizontalPositionOrder, int verticalPositionOrder) {
        constraints.gridx = horizontalPositionOrder;
        constraints.gridy = verticalPositionOrder;
        mainFrame.add(component, constraints);
    }

    private void setButtonListener() {
        find.addActionListener(actionEvent -> {
            RDFMoleculeInfo.setText("Идет агрегация и расознвание RDF молекул.....");
            String text = FIOfield.getText();
            try {
                Set<RDFMolecule> articles = moleculesComparator.findAndCompare(text);
                RDFMoleculeInfo.setText(null);
                RDFMoleculeInfo.append("Распознано " + articles.size() + "статей");
                for (RDFMolecule article : articles) {
                    RDFMoleculeInfo.append(article.getDescription());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
