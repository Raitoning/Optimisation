package models;

import models.genetique.Individu;

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

    private ArrayList<Processeur> processeurs;
    private ArrayList<Tache> taches;

    private ArrayList<Individu> population;

    private Random random;

    private int mutation; //a diviser par 100

    public Optimisation() {

        nbIteration = 0;

        temperature = 10;
        mutation = 10;
        tailleListeTaboue = 10;
        taillePopulation = 10;
        nbProcesseurs = 16;
        nbTaches = 10;

//        Individu test = new Individu(this);

        random = new Random();

        processeurs = new ArrayList<>(nbProcesseurs);
        taches = new ArrayList<>(nbTaches);

        // Génération aléatoire des tâches
        for (int i = 0; i < nbTaches; i++) {

            taches.add(new Tache(random.nextInt(100)));
        }

        algorithmeGenetique();
    }

    private void algorithmeGenetique() {

        population = new ArrayList<>(taillePopulation);

        // Génération aléatoire de la population
        for (int i = 0; i < taillePopulation; i++) {

            population.add(new Individu(this));
            System.out.println("Fitness: " + population.get(i).getFitness());
        }

        for (int i = 0; i < taillePopulation; i++) {

            if(random.nextFloat() < ((float)mutation/100)) {

                population.get(i).mutation();
            }
        }

        Individu meilleur = population.get(0);

        for (int i = 0; i < taillePopulation; i++) {

            if(population.get(i).getFitness() < meilleur.getFitness()) {

                meilleur = population.get(i);
            }
        }

        System.out.println("Meilleur: " + meilleur + " avec une fitness de " +
                meilleur.getFitness());

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

        return mutation/100;
    }

    public void setTemperature(int temperature) {

        this.temperature = temperature;
        updated();
    }

    public void setTailleListeTaboue(int tailleListeTaboue) {

        this.tailleListeTaboue = tailleListeTaboue;
        updated();
    }

    public void setNbTaches(int nbTaches) {

        this.nbTaches = nbTaches;
        updated();
    }

    public void setNbProcesseurs(int nbProcesseurs) {

        this.nbProcesseurs = nbProcesseurs;
    }

    public void setMutation(int mutation) {

        this.mutation = mutation;
        updated();
    }

    private void updated(){

        setChanged();
        notifyObservers();
    }
}
