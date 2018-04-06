package models.algorithmes;

import models.Optimisation;

public class Tabou extends Algorithme{

    private int nbiteration;
    private EtatTabou meilleur;
    private static EtatTabou[] listeTabou;
    private int nbCPU;
    private int[] valeurTaches;

    public Tabou(Optimisation m){
        super(m);

        listeTabou = new EtatTabou[modele.getTailleListeTaboue()];

        for(int i =0;i < modele.getTailleListeTaboue();i++){
            listeTabou[i] = null;
        }

        nbCPU = modele.getNbProcesseurs();
        nbiteration = 0;

        valeurTaches = new int[modele.getNbTaches()];
        for(int i =0;i < valeurTaches.length;i++){
            valeurTaches[i] = modele.getTache(i).getDuree();
        }

        meilleur = new EtatTabou(nbCPU,valeurTaches);
    }

    public void demarrer(){

        EtatTabou tmp = meilleur.meilleurVoisin();
        int change = 0;

        nbiteration++;

        while(tmp != null && change < listeTabou.length*listeTabou.length && nbiteration < modele.getNbIterationMax()){
            //ajout liste tabou
            listeTabou[nbiteration%listeTabou.length] = tmp;


            //si on a le meme etat que le meilleur deja trouvé alors on boucle, on peut arreter le programme
            if(meilleur.signatureEtat().equals(tmp.signatureEtat()) || meilleur.estFinal()) {
                break;
            }
            //sinon on garde en memoire le meilleur des 2
            if(tmp.coutMax() < meilleur.coutMax()) {
                meilleur = tmp;
                change = 0;
            }

            tmp = tmp.meilleurVoisin();

            change++;
            nbiteration++;
        }

        modele.setNbIteration(nbiteration);
        modele.setMeilleurValeur(meilleur.coutMax());

    }

    public static boolean dansListe(EtatTabou e){
        boolean res = false;
        for(int k =0;k < listeTabou.length;k++){
            if(listeTabou[k] != null){
                if(listeTabou[k].signatureEtat().equals(e))
                    res = true;
            }
        }
        return res;
    }

}
