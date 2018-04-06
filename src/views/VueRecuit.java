package views;

import controllers.ControleurModele;
import models.Optimisation;
import javax.swing.*;

public class VueRecuit extends VueAlgorithme {

    private JSpinner spinnerTemperature;

    public VueRecuit(Optimisation m, ControleurModele c) {

        super(m, c, "Recuit Simulé");

        SpinnerNumberModel recuitSpinnerModel = new SpinnerNumberModel(

                100, //initial value also change in Optimisation
                0,  //min
                3000, //max
                1   //step
        );

        spinnerTemperature = new JSpinner(recuitSpinnerModel);

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
