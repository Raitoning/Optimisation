package controllers;

import models.Optimisation;
import javax.swing.*;

public class ControleurRecuit extends Controleur {

    private JSpinner spinnerTemperature;

    public ControleurRecuit(Optimisation m) {

        super(m, "Recuit Simulé");

        spinnerTemperature = new JSpinner(spinnerModel);

        spinnerTemperature.addChangeListener(

                changeListener -> modele.setTemperature((int) spinnerTemperature.getValue())
        );

        add(new JLabel("Température: "));
        add(spinnerTemperature);
    }
}
