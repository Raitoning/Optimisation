package models;

import views.PanneauAlgorithmes;
import views.VueInformations;
import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private Optimisation modele;

    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem jMenuItem;

    private VueInformations vueInformations;
    private PanneauAlgorithmes panneauAlgorithmes;

    public Gui(Optimisation m) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

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

        panneauAlgorithmes = new PanneauAlgorithmes(modele);

        //2. Optional: What happens when the frame closes?
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Optimisation");
        setJMenuBar(jMenuBar);

        //3. Create components and put them in the frame.
        add(vueInformations, BorderLayout.CENTER);
        add(panneauAlgorithmes, BorderLayout.SOUTH);


        //4. Size the frame.
//        setSize(854, 480);
        pack();

        //5. Show it.
        setVisible(true);
    }

    public static void main (String args[]) {

        Optimisation modele = new Optimisation();
        new Gui(modele);
    }
}
