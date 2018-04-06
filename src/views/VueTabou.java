package views;

import controllers.ControleurModele;
import models.Optimisation;
import javax.swing.*;

public class VueTabou extends VueAlgorithme {

    private JSpinner spinnerTabou;

    public VueTabou(Optimisation m, ControleurModele c) {

        super(m, c, "Recherche Tabou");

        spinnerTabou = new JSpinner(spinnerModel);

        spinnerTabou.addChangeListener(

                changeListener -> controleurModele.setSizeListTaboo((int)spinnerTabou.getValue())
        );

        add(new JLabel("Taboos: "));
        add(spinnerTabou);

        boutonAlgorithme.addActionListener(
                actionListener -> controleurModele.algorithmeGenetique()
        );
        add(Box.createVerticalGlue());
        add(boutonAlgorithme);
    }
}
