package views;

import controllers.ControleurModele;

import javax.swing.*;
import java.awt.*;

public class VueModele extends JPanel {

    private ControleurModele controleurModele;

    private JSpinner jSpinnerTaches;
    private JSpinner jSpinnerProcesseurs;

    public VueModele(ControleurModele c) {

        controleurModele = c;

        setBorder(BorderFactory.createTitledBorder("Paramètres: "));

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        SpinnerModel spinnerModel = new SpinnerNumberModel(10, 1, 10000, 1);

        jSpinnerTaches = new JSpinner(spinnerModel);

        jSpinnerTaches.addChangeListener(
                changeListener -> controleurModele.setNbTaches((int)jSpinnerTaches.getValue())
        );

        add(new Label("Nombre taches: "));
        add(jSpinnerTaches);

        spinnerModel = new SpinnerNumberModel(10, 1, 10000, 1);

        jSpinnerProcesseurs = new JSpinner(spinnerModel);

        jSpinnerProcesseurs.addChangeListener(
                changeListener -> controleurModele.setNbProcesseurs((int)jSpinnerProcesseurs.getValue())
        );

        add(new Label("Nombre processeurs: "));
        add(jSpinnerProcesseurs);
    }
}
