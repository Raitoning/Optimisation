package controllers;

import models.Optimisation;
import javax.swing.*;

public class ControleurGenetique extends Controleur {

    private SpinnerNumberModel mutationSpinnerNumberModel;

    public ControleurGenetique(Optimisation m) {

        super(m, "Algorithme Génétique");

        // On remplace le layout par défaut de Controleur par un plus adapté
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JSpinner spinnerPopulation = new JSpinner(spinnerModel);

        spinnerPopulation.addChangeListener(

                changeListener -> modele.setSizePopulation((int)spinnerPopulation.getValue())
        );

        add(new JLabel("Population: "));
        add(spinnerPopulation);

        // Un nouveau SpinnerNumberModel plus adapté pour la mutation
        mutationSpinnerNumberModel = new SpinnerNumberModel(

                50, //initial value also change in Optimisation
                0,  //min
                100, //max
                1   //step
        );

        JSpinner spinnerMutation = new JSpinner(mutationSpinnerNumberModel);

        spinnerMutation.addChangeListener(

                changeListener -> modele.setMutation((int)spinnerMutation.getValue())
        );

        add(new JLabel("Mutation: "));
        add(spinnerMutation);
    }
}
