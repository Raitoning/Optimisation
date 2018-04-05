package models.algorithmes;

import models.Optimisation;
import models.Processeur;
import models.Tache;

import java.util.ArrayList;
import java.util.Random;

public class RecruitSimule {

    private Optimisation opt;
    Random r;
    private int nbProc;
    private int nbTaches;
    private int initialTemperature;
    private int internalTemperature;
    private ArrayList<Processeur> alProc;
    private ArrayList<Tache> alTaches;



    private RecruitSimule(Optimisation opt) {
        this.opt = opt;
        initialTemperature = opt.getTemperature();
        r = new Random();
    }

    public RecruitSimule(Optimisation opt,ArrayList<Processeur> alProc, ArrayList<Tache> alTaches) {
        this(opt);
        this.alProc = alProc;
        this.alTaches = alTaches;
    }

    public RecruitSimule(Optimisation opt,int nbProc, int nbTaches) {
        this(opt);
        this.nbProc =nbProc;
        this.nbTaches =nbTaches;
        this.alProc = new ArrayList<>();
        this.alTaches = new ArrayList<>();
        initTaches();
    }

    public ArrayList<Processeur> algorithme(int iterMax){
        ArrayList<Processeur> solution, newSolution, bestSolution; // s, sn, g
        int coutMax, newCoutMax,bestCout, nbIter;

        initialAssignment(); //s0
        solution = cloneALProc();//s=s0
        bestSolution = solution; // g = s0;
        coutMax = E (solution); // e = E(s)
        bestCout = coutMax;      // = E(bestSolution); // m = E(g)

        nbIter = 0; //k = 0
        while ((nbIter<iterMax)){//eventually add (e>emax)
            newSolution = voisin();
            newCoutMax = E(newSolution);
            if ((newCoutMax<coutMax)||(r.nextFloat()<prob(newCoutMax-coutMax,temp(nbIter, iterMax)))){
                solution = newSolution;
                coutMax = newCoutMax;
            }
            if (coutMax<bestCout){
                bestSolution = solution;
                bestCout = coutMax;
            }
            nbIter++;
        }
        return bestSolution;
    }

    /*
        TODO:Ajouter le hill climbing aka trouver voisin optimal.
        Refaire les probablilités pour que plus la temperature est élevée
        plus les chances de prendre un voisin aleatoire est elevee;
        plus la temperature baisse, plus la probabilité de prendre un voisin
        optimal augmente.
     */


    private int temp(double k, double kmax){
        internalTemperature =(int) (internalTemperature*(1-k/kmax));
        return internalTemperature;
    }

    private double prob(int delta, int temperature){
        double d= temperature/delta;
        if (d>1d){
            return 1/d;
        }
        return d;
    }

    private int E(ArrayList<Processeur> s){
        int max=Integer.MIN_VALUE;
        for( Processeur e: s){
            if (e.getDureeTotale()>max){
                max = e.getDureeTotale();
            }
        }
        return max;
    }

    private void initTaches(){
        for(int i=0;i<nbProc;i++){
            this.alProc.add(new Processeur());
        }
        for(int i=0;i<nbProc;i++){
            this.alTaches.add(new Tache(r.nextInt(10)));
        }
    }

    private void initialAssignment(){
        internalTemperature = initialTemperature;
        for(Tache t:alTaches){
            alProc.get(r.nextInt(alProc.size())).ajouterTache(t);
            //Ajoute une tache a un processeur aleatoirement
        }
    }

    private ArrayList<Processeur> voisin(){
        ArrayList<Processeur> al= cloneALProc();
        Tache t = alTaches.get(r.nextInt(al.size()));//Random tache
        int proc = -1; //processeur a eviter
                        // WARNING: MAY CAUSE EXCEPTIONS.
        for (Processeur e: al) {
            if (e.contiensTache(t)){
                proc = al.indexOf(e);
            }
        }
        int random = r.nextInt(al.size());
        while (random == proc){
            random = r.nextInt(al.size());
        }
        al.get(random).ajouterTache(t);

        return al;
    }

    private ArrayList<Processeur> cloneALProc(){
        ArrayList<Processeur> al= new ArrayList<>();
        for(Processeur e:alProc){
            al.add(new Processeur(e));
        }
        return cloneALProc();
    }



}
