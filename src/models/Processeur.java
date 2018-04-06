package models;

import java.util.ArrayList;

public class Processeur {

    private ArrayList<Tache> taches;

    public Processeur() {

        taches = new ArrayList<>();
    }

    public Processeur(Processeur p){
        taches = (ArrayList<Tache>) p.taches.clone();

    }

    public void ajouterTache(Tache t) {

        taches.add(t);
    }

    public int getDureeTotale() {

        int res = 0;

        for(Tache t : taches) {

            res += t.getDuree();
        }

        return res;
    }

    public void retirerTache(Tache t){

        taches.remove(t);
    }

    public boolean contiensTache(Tache t){

        return taches.contains(t);
    }

    public Tache idealTache(int duree){
        Tache result = taches.get(0);
        for (Tache t:taches){
            if (t.getDuree()==duree){
                result = t;
            } else {
                if ((Math.abs(result.getDuree())-Math.abs(duree))>(Math.abs(t.getDuree())-Math.abs(duree))){
                    result = t;
                }
            }
        }
        return result;
    }

    @Override
    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Processeur de duree"+getDureeTotale()+":");
        for(Tache t:taches){
            sb.append("\t"+taches.indexOf(t)+": "+t);
        }
        return sb.toString();
    }


}