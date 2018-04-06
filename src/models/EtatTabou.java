package models;

import java.util.ArrayList;
import java.util.Random;

public class EtatTabou {

    private ArrayList<Integer>[] tableauCPU;
    private ArrayList<Integer> val;
    private int min;
    private int max;

    /**
     * creer un
     * @param nombreCPU
     * @param valeurTache
     */
    public EtatTabou(int nombreCPU,int[] valeurTache){
        tableauCPU = new ArrayList[nombreCPU];
        for(int i =0;i < tableauCPU.length;i++)
            tableauCPU[i] = new ArrayList<Integer>();

        //initialisation etat initial

        for(int j =0; j < valeurTache.length;j++){
            tableauCPU[(int)(Math.random()*nombreCPU)].add(valeurTache[j]);
        }

        //cout des CPU
        val = new ArrayList<Integer>();
        int tmp = 0;
        for(int k =0; k < tableauCPU.length;k++){
            for(int h = 0;h < tableauCPU[k].size();h++){
                tmp += tableauCPU[k].get(h);
            }
            val.add(tmp);
            tmp = 0;
        }

        min = indicePetit();
        max = indiceGros();

    }

    /**
     *
     * @param cpu
     * @param pval
     */
    public EtatTabou(ArrayList<Integer>[] cpu,ArrayList<Integer> pval){

        tableauCPU = new ArrayList[cpu.length];
        for(int i =0;i < cpu.length;i++)
            tableauCPU[i] = (ArrayList<Integer>) cpu[i].clone();

        val = (ArrayList<Integer>) pval.clone();

        min = indicePetit();
        max = indiceGros();
    }


    /**
     * identifie un etat
     * @return
     */
    public String signatureEtat(){
        StringBuilder sb = new StringBuilder();

        for(int i =0; i < tableauCPU.length;i++){
            sb.append("{");
            for(int j = 0;j < tableauCPU[i].size()-1;j++){
                sb.append(tableauCPU[i].get(j)+",");
            }
            sb.append(tableauCPU[i].get(tableauCPU[i].size()-1));
            sb.append("}");
        }

        return sb.toString();
    }

    /**
     * renvoi le voisin autorisé par la liste tabou de coup minimal
     * @return
     */
    public EtatTabou meilleurVoisin(){

        EtatTabou res = null;
        EtatTabou tmp;
        int transition;

        //On clone les valeurs de départ
        ArrayList<Integer> v = (ArrayList<Integer>) val.clone();
        ArrayList<Integer>[] cpuTemp = new ArrayList[tableauCPU.length];

        for(int p =0;p < tableauCPU.length;p++){
            cpuTemp[p] = (ArrayList<Integer>) tableauCPU[p].clone();
        }


        //Pour chaque élément dans le plus gros CPU
        for(int i =0;i < tableauCPU[max].size();i++){

            //on prend la valeur dans le plus gros CPU
            transition = cpuTemp[max].get(i);
            //on la supprime du cpu
            cpuTemp[max].remove(i);
            //on l'ajout au cpu de plus petit coup
            cpuTemp[min].add(transition);
            //on met a jour les valeur
            v.set(min,v.get(min)+transition);
            v.set(max,v.get(max)-transition);

            //on cree le nouvel etat
            tmp = new EtatTabou(cpuTemp,v);

            //on regarde si l'etat est tabou
            if(!Tabou.dansListe(tmp)) {
                //on compare le meilleur voisin
                if (res == null)
                    res = tmp;
                else {
                    if (res.coutMax() > tmp.coutMax())
                        res = tmp;
                }
            }

            //on remet les valeurs de base pour la prochaine iteration
            cpuTemp[max].add(i,transition);
            cpuTemp[min].remove(cpuTemp[min].size()-1);
            v.set(min,v.get(min)-transition);
            v.set(max,v.get(max)+transition);

        }
        //On retourne le voisins de coutMax minimum accepté par la liste
        return res;
    }

    /**
     * retourne le coup de l'etat courant
     * @return
     */
    public int coutMax(){
        int res;
        res = val.get(0);
        for(int i =1; i < tableauCPU.length;i++)
            if(res < val.get(i)){
                res = val.get(i);
            }
        return res;
    }

    /**
     * retourne l'indice du plus gros processeur du tableauCPU
     * @return
     */
    public int indiceGros(){
        int res = 0;
        for(int i =1;i < val.size();i++){
            if(val.get(res) < val.get(i))
                res = i;
        }

        return res;
    }

    /**
     * retourne l'indice du plus petit processeur du tableau tableauCPU
     * @return
     */
    public int indicePetit(){
        int res = 0;
        for(int i =1;i < val.size();i++){
            if(val.get(res) > val.get(i))
                res = i;
        }

        return res;
    }

    public boolean estFinal(){
        boolean res = true;
        int tmp = val.get(0);
        for(int i =1;i < val.size();i++)
            res = res && tmp == val.get(i);

        return res;
    }


}
