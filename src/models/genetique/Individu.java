package models.genetique;

import models.Optimisation;

import java.util.ArrayList;
import java.util.Random;

public class Individu {

    private Optimisation modele;

    private int[] gene;
    private int fitness;
    private int nbTaches;
    private int nbProcesseurs;
    private int tailleGene;
    private int charge;

    private ArrayList<Integer> idTaches;

    private Random random;

    // Constructeur de création d'un individu aléatoire
    public Individu(Optimisation m) {

        modele = m;

        random = new Random();

        nbTaches = modele.getNbTaches();
        nbProcesseurs = modele.getNbProcesseurs();

        idTaches = new ArrayList<>(nbTaches);

        for (int i = 0; i < nbTaches; i++) {

            idTaches.add(i);
        }

        fitness = 0;
        charge = nbTaches / nbProcesseurs;

        tailleGene = nbTaches + nbProcesseurs - 1;

        // Création du gène
        gene = new int[tailleGene];

        int i = 0;
        int j = 0;

        // Génération aléatoire du gène
        while(!idTaches.isEmpty()) {

            if(j == charge) {

                gene[i] = -1;
                i++;
                j = 0;
            }

            int rand = random.nextInt(idTaches.size());
            gene[i] = idTaches.get(rand);
            idTaches.remove(Integer.valueOf(gene[i]));
            i++;
            j++;
        }

        calculFitness();
    }

    // Constructeur de génération d'un individu à partir d'un croisement
    // FIXME: Corriger la fonction de fusion du père et de la mère
    public Individu(Optimisation m, Individu pere, Individu mere) {

        modele = m;

        random = new Random();

        nbTaches = modele.getNbTaches();
        nbProcesseurs = modele.getNbProcesseurs();

        fitness = 0;
        charge = nbTaches / nbProcesseurs;

        tailleGene = nbTaches + nbProcesseurs - 1;

        idTaches = new ArrayList<>(nbTaches);

        for (int i = 0; i < nbTaches; i++) {

            idTaches.add(i);
        }

        int pointDeCoupure = random.nextInt(tailleGene);

        gene = new int[tailleGene];

        for (int i = 0; i < pointDeCoupure; i++) {

            gene[i] = pere.getBit(i);
            idTaches.remove(Integer.valueOf(gene[i]));
        }

        for (int i = pointDeCoupure; i < gene.length; i++) {

            if(idTaches.contains(mere.getBit(i)) || mere.getBit(i) == -1) {

                gene[i] = mere.getBit(i);
                idTaches.remove(Integer.valueOf(gene[i]));
            } else {

                if(!idTaches.isEmpty()) {

                    gene[i] = idTaches.get(0);
                    idTaches.remove(0);
                }
            }
        }

        if(random.nextFloat() < (float)modele.getMutation()/100) {

//            mutation();
        }

        calculFitness();
    }

    // FIXME: Crash lors du calcul de la fitness
    private void mutation() {

        // Fonction de mutation simple
        System.out.println("Mutation");
        int pointDeMutation = random.nextInt(tailleGene - 1);
        int pointDeMutation2 = random.nextInt(tailleGene - 1);

        int temp = gene[pointDeMutation];
        gene[pointDeMutation] = gene[pointDeMutation2];
        gene[pointDeMutation2] = temp;

        calculFitness();
    }

    public void calculFitness() {

        int fitnesses[] = new int[nbProcesseurs];

        int cpuCore = 0;
        for (int i = 0; i < nbTaches; i++) {

            while (gene[i] == -1) {

                cpuCore++;
                i++;
            }

            fitnesses[cpuCore] += modele.getTache(gene[i]).getDuree();
        }

        fitness = 0;

        for (int i = 0; i < nbProcesseurs; i++) {

            if(fitnesses[i] > fitness) {

                fitness = fitnesses[i];
            }
        }
    }

    public int getFitness() {

        return fitness;
    }

    public int getBit(int index) {

        return gene[index];
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("[ ");

        for (int i = 0; i < gene.length; i++) {

            stringBuilder.append(gene[i] + " ");
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
