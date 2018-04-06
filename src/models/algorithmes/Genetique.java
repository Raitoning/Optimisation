package models.algorithmes;

import models.Optimisation;
import models.genetique.Individu;

import java.util.ArrayList;
import java.util.Random;

public class Genetique extends Algorithme {

    private int taillePopulation;
    private int nbGenerationMax;
    private int nbSelection;
    private ArrayList<Individu> population;
    private Random random;

    public Genetique(Optimisation m) {

        super(m);

        taillePopulation = modele.getTaillePopulation();
        nbGenerationMax = modele.getNbIterationMax();
        population = new ArrayList<>(taillePopulation);

        int nbSelection = taillePopulation / 4;

        // Génération aléatoire de la population
        for (int i = 0; i < taillePopulation; i++) {

            population.add(new Individu(modele));
        }
    }

    public void demarrer() {

        for (int generation = 0; generation < nbGenerationMax; generation++) {

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

                population.add(new Individu(modele, pere, mere));
            }

            // Réinsertions des anciens meilleurs individus
            for (int i = 0; i < nbSelection; i++) {

                population.add(meilleurs.get(i));
            }

            int meilleur = Integer.MAX_VALUE;

            for (int i = 0; i < population.size(); i++) {

                if(population.get(i).getFitness() < meilleur) {

                    meilleur = population.get(i).getFitness();
                }
            }

            modele.setMeilleurValeur(meilleur);
        }
    }
}
