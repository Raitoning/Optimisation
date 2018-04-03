package controllers;

import models.Optimisation;
import javax.swing.*;

public class ControleurGenetique extends Controleur {

    private SpinnerNumberModel mutationSpinnerNumberModel;

    public ControleurGenetique(Optimisation m) {

        super(m, "Algorithme Génétique");

        // On remplace le layout par défaut de Controleur par un plus adapté
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JSpinner spinnerTaches = new JSpinner(spinnerModel);

        spinnerTaches.addChangeListener(

                changeListener -> modele.setNbTaches((int)spinnerTaches.getValue())
        );

        add(new JLabel("Taches: "));
        add(spinnerTaches);

        // On crée un nouveau SpinnerModel séparé du premier pour les processeurs
        SpinnerModel spinnerModelProcesseur = new SpinnerNumberModel(

                10, //initial value also change in Optimisation
                0,  //min
                10, //max
                1   //step
        );

        JSpinner spinnerProcesseur = new JSpinner(spinnerModelProcesseur);

        spinnerProcesseur.addChangeListener(

                changeListener -> modele.setNbProcesseurs((int)spinnerProcesseur.getValue())
        );

        add(new JLabel("Processeurs: "));
        add(spinnerProcesseur);

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
