package models;

public class Tache {

    private int duree;

    public Tache(int d) {

        duree = d;
    }

    public int getDuree() {

        return duree;
    }

    @Override
    public String toString(){
        return "Tache de duree: "+duree;
    }
}
