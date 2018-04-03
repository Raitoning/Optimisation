package models.genetique;

import models.Optimisation;

import java.util.ArrayList;
import java.util.Random;

public class Individu {

    private Optimisation modele;

    private int[] gene;
    private int fitness;
    private int tailleGene;
    private int nbProcesseurs;
    private float mutation;

    private ArrayList<Integer> idTaches;

    private Random random;

    public Individu(Optimisation m) {

        modele = m;

        random = new Random();

        tailleGene = modele.getNbTaches();
        nbProcesseurs = modele.getNbProcesseurs();

        idTaches = new ArrayList<>(tailleGene);

        for (int i = 0; i < tailleGene; i++) {

            idTaches.add(i);
        }

        fitness = 0;

        // TODO: Améliorer la création des gènes
        // Création du gène
        gene = new int[tailleGene + nbProcesseurs];

        int i = 0;

        // Génération aléatoire du gène
        System.out.print("Gene: [ ");
        while(!idTaches.isEmpty()) {

            // Changement de core du CPU
            if(random.nextFloat() < ((float)nbProcesseurs / 100)) {

                gene[i] = -1;
                i++;
            }
            int rand = random.nextInt(idTaches.size());
            gene[i] = idTaches.get(rand);
            idTaches.remove(rand);
            System.out.print(gene[i] + " ");
            i++;
        }
        System.out.print("]\n");

        calculFitness();
    }

    public void mutation() {

        // Fonction de mutation simple

        int pointDeMutation = random.nextInt(tailleGene - 1);
        System.out.println("Point de mutation: " + pointDeMutation);

        System.out.println("Gene au point de mutation: " + gene[pointDeMutation]);

        System.out.print("Avant mutation: [ ");
        for (int i = 0; i < tailleGene; i++) {

            System.out.print(gene[i] + " ");
        }
        System.out.println("]");

        System.out.println("Fitness avant mutation: " + fitness);

        if(pointDeMutation < tailleGene - 1) {

            int temp = gene[pointDeMutation];
            gene[pointDeMutation] = gene[pointDeMutation + 1];
            gene[pointDeMutation + 1] = temp;
        }

        calculFitness();

        System.out.print("Après mutation: [ ");
        for (int i = 0; i < tailleGene; i++) {

            System.out.print(gene[i] + " ");
        }
        System.out.println("]");

        System.out.println("Fitness Après mutation: " + fitness);
    }

    public void calculFitness() {

        int fitnesses[] = new int[nbProcesseurs];

        int cpuCore = 0;
        for (int i = 0; i < tailleGene; i++) {

            if(gene[i] == -1) {

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
}
