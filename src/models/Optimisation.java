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
        mutation = 100;
        tailleListeTaboue = 10;
        taillePopulation = 64;
        nbProcesseurs = 4;
        nbTaches = 16;

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

        int nbSelection = taillePopulation / 4;

        // Génération aléatoire de la population
        for (int i = 0; i < taillePopulation; i++) {

            population.add(new Individu(this));
        }


        for (int generation = 0; generation < 10; generation++) {

            System.out.println("Generation " + (generation + 1) + ": ");

            // Séléction des meilleurs individus pour croisement
            ArrayList<Individu> meilleurs = new ArrayList<>(nbSelection);

            Individu selectionne = null;

            for (int i = 0; i < nbSelection; i++) {

                int fitness = Integer.MAX_VALUE;

                for (int j = 0; j < population.size(); j++) {

                    if(population.get(j).getFitness() < fitness) {

                        selectionne = population.get(j);
                        fitness = selectionne.getFitness();
                    }
                }

                if(selectionne != null) {

                    meilleurs.add(selectionne);
                    population.remove(selectionne);
                }
            }

            // Disparaition des pires individus
            selectionne = null;

            for (int i = 0; i < nbSelection; i++) {

                int fitness = 0;

                for (int j = 0; j < population.size(); j++) {

                    if (population.get(j).getFitness() > fitness) {

                        selectionne = population.get(j);
                        fitness = selectionne.getFitness();
                    }

                    if (selectionne != null) {

                        population.remove(selectionne);
                    }
                }
            }

            // Croisement des meilleurs individus
            for (int i = 0; i < nbSelection; i++) {

                Individu pere = meilleurs.get(random.nextInt(nbSelection));
                Individu mere = meilleurs.get(random.nextInt(nbSelection));

                population.add(new Individu(this, pere, mere));
            }

            // Réinsertions des anciens meilleurs individus
            for (int i = 0; i < nbSelection; i++) {

                population.add(meilleurs.get(i));
            }

            for (int i = 0; i < population.size(); i++) {

                System.out.println(population.get(i).toString());
                System.out.println("Fitness: " +
                        population.get(i).getFitness());
            }
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
