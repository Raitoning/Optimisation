package models;

import java.util.Observable;

public class Optimisation extends Observable {

    private int nbIteration;
    private int temperature;
    private int sizeListTaboo;
    private int sizePopulation;

    private int mutation; //a diviser par 100


    public Optimisation() {

        temperature = 10;
        mutation = 50;
        sizeListTaboo = 10;
        sizePopulation = 10;
    }

    public int getNbIteration() {

        return nbIteration;
    }

    public int getTemperature() {

        return temperature;
    }

    public int getSizeListTaboo() {

        return sizeListTaboo;
    }

    public int getSizePopulation() {

        return sizePopulation;
    }

    public int getMutation() {

        return mutation/100;
    }

    public void setTemperature(int temperature) {

        this.temperature = temperature;
        updated();
    }

    public void setSizeListTaboo(int sizeListTaboo) {

        this.sizeListTaboo = sizeListTaboo;
        updated();
    }

    public void setSizePopulation(int sizePopulation) {

        this.sizePopulation = sizePopulation;
        updated();
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
