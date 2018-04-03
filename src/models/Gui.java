package models;

import controllers.Controleurs;
import views.VueInformations;
import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private Optimisation modele;

    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem jMenuItem;

    private VueInformations vueInformations;
    private Controleurs controleurs;

    public Gui(Optimisation m) {

        modele = m;

        jMenuBar = new JMenuBar();
        jMenu = new JMenu("Fichier");
        jMenuItem = new JMenuItem("Quitter");

        // Lambda expression
        jMenuItem.addActionListener(actionListener -> System.exit(0));

        jMenu.add(jMenuItem);
        jMenuBar.add(jMenu);

        vueInformations = new VueInformations(modele);

        modele.addObserver(vueInformations);

        controleurs = new Controleurs(modele);

        //2. Optional: What happens when the frame closes?
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Optimisation");
        setJMenuBar(jMenuBar);

        //3. Create components and put them in the frame.
        add(vueInformations, BorderLayout.EAST);
        add(controleurs, BorderLayout.SOUTH);


        //4. Size the frame.
        setSize(640, 480);

        //5. Show it.
        setVisible(true);
    }

    public static void main (String args[]) {

        Optimisation modele = new Optimisation();
        new Gui(modele);
    }
}
