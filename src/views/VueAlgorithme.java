package views;

import controllers.ControleurModele;
import models.Optimisation;

import javax.swing.*;

public abstract class VueAlgorithme extends JPanel {

    protected Optimisation modele;
    protected ControleurModele controleurModele;
    protected JButton boutonAlgorithme;

    protected SpinnerModel spinnerModel;

    protected VueAlgorithme(Optimisation m, ControleurModele controleur, String nom) {

        modele = m;
        controleurModele = controleur;
        setBorder(BorderFactory.createTitledBorder(nom));

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

         spinnerModel = new SpinnerNumberModel(

                10, //initial value also change in Optimisation
                0,  //min
                10, //max
                1   //step
        );

        boutonAlgorithme = new JButton("Démarrer");
    }
}
