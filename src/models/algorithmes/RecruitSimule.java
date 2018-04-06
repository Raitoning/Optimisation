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
    private double internalTemperature;
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
        this.nbTaches = alTaches.size();
        this.nbProc = alProc.size();
    }

    public RecruitSimule(Optimisation opt,int nbProc, int nbTaches) {
        this(opt);
        this.nbProc =nbProc;
        this.nbTaches =nbTaches;
        this.alProc = new ArrayList<>();
        this.alTaches = new ArrayList<>();
        initTaches();
    }

    public ArrayList<Processeur> algorithme() {
        return algorithme(Integer.MAX_VALUE);
    }

    public ArrayList<Processeur> algorithme(int iterMax){
        ArrayList<Processeur> solution, newSolution, bestSolution; // s, sn, g
        int coutMax, newCoutMax,bestCout, nbIter;
        double random;
        boolean prob;
        initialAssignment(); //s0
        solution = cloneALProc();//s=s0
        bestSolution = solution; // g = s0;
        coutMax = E (solution); // e = E(s)
        bestCout = coutMax;      // = E(bestSolution); // m = E(g

        System.out.println("-------INITIAL--------");
        printState(bestSolution);
        System.out.println("----------------------");


        nbIter = 0; //k = 0
        while ((nbIter<iterMax)&&(internalTemperature>0.1)){//eventually add (e>emax)
            random = r.nextDouble();
            if (false ){//random<prob(1,internalTemperature)) {
                newSolution = voisin(solution); //voisin aleatoire
                newCoutMax = E(newSolution);
            } else {
                newSolution = voisinO(solution); // voisin ideal

                newCoutMax = E(newSolution);
            }
            if ((newCoutMax<coutMax)||(random<prob(newCoutMax-coutMax,temp()))){
                solution = newSolution;
                coutMax = newCoutMax;
            }
            if (coutMax<bestCout){
                //System.out.println(nbIter+"---ENTRANCE-----");

                bestSolution = solution;
                bestCout = coutMax;
            }
            nbIter++;
        }
        return bestSolution;
    }

    /*
        Refaire les probablilités pour que plus la temperature est élevée
        plus les chances de prendre un voisin aleatoire est elevee;
        plus la temperature baisse, plus la probabilité de prendre un voisin
        optimal augmente.
     */


    private double temp(){
        internalTemperature = (internalTemperature*(0.99d));
        return internalTemperature;
    }

    private double prob(double delta, double temperature){

        return Math.exp(delta/temperature);
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
        for(int i=0;i<nbTaches;i++){
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

    private ArrayList<Processeur> voisin(ArrayList<Processeur> z){
        ArrayList<Processeur> al= cloneALProc(z);
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

    private ArrayList<Processeur> voisinO(ArrayList<Processeur> z){
        ArrayList<Processeur> al= cloneALProc(z);
        int proc=0; // Recherche du processeur au plus gros poids.

        for(Processeur e:al) {
            if (e.getDureeTotale() > al.get(proc).getDureeTotale()) {
                proc = al.indexOf(e);
            }
        }

        int procmin=0;
        for(Processeur e:al) {
            if (e.getDureeTotale() < al.get(procmin).getDureeTotale()) {
                procmin = al.indexOf(e);
            }
        }

        //System.out.println("min:"+procmin+" max: "+proc);

        int delta = al.get(proc).getDureeTotale()-al.get(procmin).getDureeTotale();
        //System.out.println("min:"+procmin+" max: "+proc+" delta:"+delta);
        Tache t = al.get(proc).idealTache(delta); //prendre la tache qui equilibre le mieux les durees

        al.get(proc).retirerTache(t);
        al.get(procmin).ajouterTache(t);

        return al;
    }


    private ArrayList<Processeur> cloneALProc(){
        ArrayList<Processeur> al= new ArrayList<>();
        for(Processeur e:alProc){
            al.add(new Processeur(e));
        }
        return al;
    }

    private ArrayList<Processeur> cloneALProc(ArrayList<Processeur> z){
        ArrayList<Processeur> al= new ArrayList<>();
        for(Processeur e:z){
            al.add(new Processeur(e));
        }
        return al;
    }


    public void printState(ArrayList<Processeur> al){
        for(Processeur p:al){
            System.out.println(al.indexOf(p)+":"+p);
        }
    }

    public static void main(String[] args){
        Optimisation modele=new Optimisation();
        modele.setTemperature(300);
        RecruitSimule rs =new RecruitSimule(modele, 8, 120);
        rs.printState(rs.algorithme(10000));

    }

}
