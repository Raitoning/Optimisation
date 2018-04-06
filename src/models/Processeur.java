package models;

import java.util.ArrayList;

public class Processeur {

    private ArrayList<Tache> taches;

    public Processeur() {

        taches = new ArrayList<>();
    }

    public void ajouterTache(Tache t) {

        taches.add(t);
    }

    public int getDureeTotale() {

        int res = 0;

        for(Tache t : taches) {

            res += t.getDuree();
        }

        return res;
    }
}
