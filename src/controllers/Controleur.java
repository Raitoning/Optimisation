package controllers;

import models.Optimisation;

import javax.swing.*;

public abstract class Controleur extends JPanel {

    protected Optimisation modele;

    protected SpinnerModel spinnerModel;

    protected Controleur(Optimisation m, String nom) {

        modele = m;
        setBorder(BorderFactory.createTitledBorder(nom));

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

         spinnerModel = new SpinnerNumberModel(

                10, //initial value also change in Optimisation
                0,  //min
                10, //max
                1   //step
        );
    }
}
