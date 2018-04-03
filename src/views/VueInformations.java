package views;

import models.Optimisation;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class VueInformations extends JPanel implements Observer {

    private Optimisation modele;

    private JLabel nbIterations;
    private JLabel meilConfig;
    private JLabel evolValeur;

    public VueInformations(Optimisation m) {

        modele = m;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createTitledBorder("Informations: "));

        nbIterations = new JLabel("Nombre itération: ");
        meilConfig = new JLabel("Meilleure configuration: ");
        evolValeur = new JLabel("Evolution de la valeur: ");

        add(nbIterations);
        add(meilConfig);
        add(evolValeur);
    }

    @Override
    public void update(Observable o, Object arg) {

        nbIterations.setText("Nombre itération: " + modele.getNbIteration());
//        meilConfig.setText("Meilleure configuration: ");
//        evolValeur.setText("Evolution de la valeur: ");
    }
}
