package views;

import controllers.ControleurModele;
import models.Optimisation;
import javax.swing.*;

public class VueRecuit extends VueAlgorithme {

    private JSpinner spinnerTemperature;

    public VueRecuit(Optimisation m, ControleurModele c) {

        super(m, c, "Recuit Simulé");

        spinnerTemperature = new JSpinner(spinnerModel);

        spinnerTemperature.addChangeListener(

                changeListener -> controleurModele.setTemperature((int) spinnerTemperature.getValue())
        );

        add(new JLabel("Température: "));
        add(spinnerTemperature);

        boutonAlgorithme.addActionListener(
                actionListener -> controleurModele.recuitSimule()
        );

        add(Box.createVerticalGlue());
        add(boutonAlgorithme);
    }
}
