package views;

import javafx.beans.Observable;
import models.Optimisation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Observer;
import java.util.Optional;

public class Gui extends JFrame implements Observer{

    private Optimisation mod;


    private JPanel pane;
    private JLabel nbIterations;
    private JLabel meilConfig;
    private JLabel evolValeur;

    public Gui(Optimisation o) {

        mod = o;

        paneInit();

        //2. Optional: What happens when the frame closes?
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //3. Create components and put them in the frame.
        //...create emptyLabel...
        nbIterations = new JLabel("Nombre itération: ");
        meilConfig = new JLabel("Meilleure configuration: ");
        evolValeur = new JLabel("Evolution de la valeur: ");



        getContentPane().add(nbIterations, BorderLayout.NORTH);
        getContentPane().add(meilConfig, BorderLayout.CENTER);
        getContentPane().add(evolValeur, BorderLayout.SOUTH);

        //4. Size the frame.
        // pack();

        setSize(640, 480);

        //5. Show it.
        setVisible(true);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }

    private void paneInit(){
        pane = new JPanel();
        pane.setLayout(new GridLayout(4,2));

        SpinnerModel spn = new SpinnerNumberModel(10, //initial value also change in Optimisation
                2, //min
                10, //max
                1); //step
        JSpinner temper = new JSpinner(spn);
        temper.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) e.getSource();
                int value = (int)spinner.getValue();
                mod.temperatureUpdate(value);
            }}
        );
        pane.add(new JLabel("Temperature: "));
        pane.add(temper);

        SpinnerModel spn2 = new SpinnerNumberModel(10, //initial value also change in Optimisation
                2, //min
                10, //max
                1); //step
        JSpinner taboo = new JSpinner(spn2);
        taboo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) e.getSource();
                int value = (int)spinner.getValue();
                mod.sizeListTabooUpdate(value);
            }}
        );
        pane.add(new JLabel("Taboos: "));
        pane.add(taboo);

        SpinnerModel spn3 = new SpinnerNumberModel(10, //initial value also change in Optimisation
                2, //min
                10, //max
                1); //step
        JSpinner popu = new JSpinner(spn3);
        popu.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) e.getSource();
                int value = (int)spinner.getValue();
                mod.SizePopulationUpdate(value);
            }}
        );
        pane.add(new JLabel("Population: "));
        pane.add(popu);



        SpinnerModel spn4 = new SpinnerNumberModel(50, //initial value also change in Optimisation
                0, //min
                100, //max
                10); //step
        JSpinner mute = new JSpinner(spn4);
        mute.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) e.getSource();
                int value = (int)spinner.getValue();
                mod.mutationUpdate(value);
            }}
        );
        pane.add(new JLabel("Mutation: "));
        pane.add(mute);

        getContentPane().add(pane, BorderLayout.EAST);
    }

    public static void main (String args[]) {

        Optimisation o = new Optimisation();
        new Gui(o);
    }

}
