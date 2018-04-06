package controllers;

import models.Optimisation;
import javax.swing.*;

public class ControleurRecuit extends Controleur {

    private JSpinner spinnerTemperature;
    private SpinnerNumberModel recuitSpinnerModel;

    public ControleurRecuit(Optimisation m) {

        super(m, "Recuit Simulé");

        // Un nouveau SpinnerNumberModel plus adapté pour le recuit
        recuitSpinnerModel = new SpinnerNumberModel(

                100, //initial value also change in Optimisation
                1,  //min
                3000, //max
                1   //step
        );

        spinnerTemperature = new JSpinner(recuitSpinnerModel);

        spinnerTemperature.addChangeListener(

                changeListener -> modele.setTemperature((int) spinnerTemperature.getValue())
        );

        add(new JLabel("Température: "));
        add(spinnerTemperature);
    }
}
