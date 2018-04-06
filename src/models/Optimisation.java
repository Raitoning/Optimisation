package models;

import models.algorithmes.Algorithme;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Optimisation extends Observable {

    private int nbIteration;
    private int temperature;
    private int tailleListeTaboue;
    private int nbTaches;
    private int nbProcesseurs;
    private int taillePopulation;
    private int nbIterationMax;
    private int meilleurValeur;

    private ArrayList<Processeur> processeurs;
    private ArrayList<Tache> taches;

    private Random random;

    private Algorithme algorithme;

    private int mutation;

    public Optimisation() {

        nbIteration = 0;
        nbIterationMax = 10;
        temperature = 10;
        mutation = 10;
        tailleListeTaboue = 10;
        nbProcesseurs = 4;
        nbTaches = 16;
        taillePopulation = 100;
        meilleurValeur = 0;

        random = new Random();

        processeurs = new ArrayList<>(nbProcesseurs);
        taches = new ArrayList<>(nbTaches);

        // Génération aléatoire des tâches
        for (int i = 0; i < nbTaches; i++) {

            taches.add(new Tache(random.nextInt(100)));
        }

        for (int i = 0; i < nbProcesseurs; i++) {

            processeurs.add(new Processeur());
        }
    }

    public int getNbIteration() {

        return nbIteration;
    }

    public int getTemperature() {

        return temperature;
    }

    public int getTailleListeTaboue() {

        return tailleListeTaboue;
    }

    public int getNbTaches() {

        return nbTaches;
    }

    public Tache getTache(int index) {

        return taches.get(index);
    }

    public int getNbProcesseurs() {

        return nbProcesseurs;
    }

    public int getMutation() {

        return mutation;
    }

    public int getTaillePopulation() {

        return taillePopulation;
    }

    public int getNbIterationMax() {

        return nbIterationMax;
    }

    public int getMeilleurValeur() {

        return meilleurValeur;
    }

    public void setTemperature(int temperature) {

        this.temperature = temperature;
        updated();
    }

    public void setTailleListeTaboue(int tailleListeTaboue) {

        this.tailleListeTaboue = tailleListeTaboue;
        updated();
    }

    public void setMutation(int mutation) {

        this.mutation = mutation;
        updated();
    }

    public void setTaillePopulation(int taille) {

        this.taillePopulation = taille;
        updated();
    }

    public void setAlgorithme(Algorithme algo) {

        algorithme = algo;
        algo.demarrer();
        updated();
    }

    public void setNbIterationMax(int max) {

        nbIterationMax = max;
        updated();
    }

    public void setNbIteration(int iterations) {

        nbIteration = iterations;
        updated();
    }

    public void setMeilleurValeur(int valeur) {

        meilleurValeur = valeur;
        updated();
    }

    private void demarrerAlgo() {

        algorithme.demarrer();
    }

    public void setNbTaches(int taches) {

        nbTaches = taches;
        updated();
    }

    public void setNbProcesseurs(int processeurs) {

        nbProcesseurs = processeurs;
        updated();
    }

    public ArrayList<Tache> getTaches(){
        return taches;
    }

    public ArrayList<Processeur> getProcessseurs(){
        return processeurs;
    }

    private void updated(){

        setChanged();
        notifyObservers();
    }
}
