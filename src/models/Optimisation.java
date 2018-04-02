package models;

import java.util.Observable;

public class Optimisation extends Observable {

    private int temperature;
    private int sizeListTaboo;
    private int sizePopulation;
    private int mutation; //a diviser par 100


    public Optimisation() {
        this.temperature = 10;
        this.mutation = 50;
        this.sizeListTaboo = 10;
        this.sizePopulation = 10;
    }

    public void temperatureUpdate(int t){
        temperature = t;
        updated();
    }
    public void sizeListTabooUpdate(int t){
        sizeListTaboo = t;
        updated();
    }
    public void SizePopulationUpdate(int t){
        sizePopulation = t;
        updated();
    }
    public void mutationUpdate(int t){
        mutation = t;
        updated();
    }


    private double probaMutation(){
        return mutation/100d;
    }

    private void updated(){
        setChanged();
        notifyObservers();
    }
}
