package views;

import controllers.ControleurModele;
import models.Optimisation;
import javax.swing.*;

public class PanneauAlgorithmes extends JPanel {

    private VueModele vueModele;
    private VueRecuit vueRecuit;
    private VueTabou vueTabou;
    private VueGenetique vueGenetique;

    private ControleurModele controleurModele;

    public PanneauAlgorithmes(Optimisation modele) {

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        controleurModele = new ControleurModele(modele);

        vueModele = new VueModele(controleurModele);
        vueRecuit = new VueRecuit(modele, controleurModele);
        vueTabou = new VueTabou(modele, controleurModele);
        vueGenetique = new VueGenetique(modele, controleurModele);

        add(vueModele);
        add(vueRecuit);
        add(vueTabou);
        add(vueGenetique);
    }
}
