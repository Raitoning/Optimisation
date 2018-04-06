package controllers;

import models.Optimisation;
import models.algorithmes.Genetique;
import models.algorithmes.RecruitSimule;
import models.algorithmes.Tabou;

public class ControleurModele {

    private Optimisation modele;

    public ControleurModele(Optimisation m) {

        modele = m;
    }

    public void setSizePopulation(int valeur) {

        modele.setTaillePopulation(valeur);
    }

    public void setMutation(int valeur) {

        modele.setMutation(valeur);
    }

    public void setTemperature(int valeur) {

        modele.setTemperature(valeur);
    }

    public void setSizeListTaboo(int valeur) {

        modele.setTailleListeTaboue(valeur);
    }

    public void setNbTaches(int valeur) {

        modele.setNbTaches(valeur);
    }

    public void setNbProcesseurs(int valeur) {

        modele.setNbProcesseurs(valeur);
    }

    public void algorithmeGenetique() {

        modele.setAlgorithme(new Genetique(modele));
    }

    public void recuitSimule() {

        modele.setAlgorithme(new RecruitSimule(modele));
    }

    public void rechercheTabou() {

        modele.setAlgorithme(new Tabou(modele));
    }
}
