package controllers;

import models.Optimisation;
import javax.swing.*;

public class ControleurTabou extends Controleur {

    private JSpinner spinnerTabou;

    public ControleurTabou(Optimisation m) {

        super(m, "Recherche Tabou");

        spinnerTabou = new JSpinner(spinnerModel);

        spinnerTabou.addChangeListener(

                changeListener -> modele.setSizeListTaboo((int)spinnerTabou.getValue())
        );

        add(new JLabel("Taboos: "));
        add(spinnerTabou);
    }
}
