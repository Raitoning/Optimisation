package controllers;

import models.Optimisation;
import javax.swing.*;

public class Controleurs extends JPanel {

    private ControleurRecuit controleurRecuit;
    private ControleurTabou controleurTabou;
    private ControleurGenetique controleurGenetique;

    public Controleurs(Optimisation modele) {

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        controleurRecuit = new ControleurRecuit(modele);
        controleurTabou = new ControleurTabou(modele);
        controleurGenetique = new ControleurGenetique(modele);

        add(controleurRecuit);
        add(controleurTabou);
        add(controleurGenetique);
    }
}
