package models.algorithmes;

import models.Optimisation;

public abstract class Algorithme {

    private Optimisation modele;

    public Algorithme(Optimisation m) {

        modele = m;
    }

    public abstract void demarrer();
}
