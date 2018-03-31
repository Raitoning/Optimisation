package views;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private JLabel nbIterations;
    private JLabel meilConfig;
    private JLabel evolValeur;

    public Gui() {

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
//        pack();

        setSize(640, 480);

        //5. Show it.
        setVisible(true);
    }

    public static void main (String args[]) {


        new Gui();
    }
}
