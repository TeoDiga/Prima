package it.unimib.prima;

import java.util.HashMap;
import java.util.Map;

public class listaIdVisitati{
    private static final String TAG = listaIdVisitati.class.getSimpleName();
    HashMap<String, Integer> film= new HashMap<String, Integer>(100);
    private static int contatore;
    private static listaIdVisitati istanza;
    private listaIdVisitati(){}

    public static listaIdVisitati getLista(){
        if(istanza== null){
            istanza = new listaIdVisitati();
            contatore=0;
        }
        return istanza;
    }
    public boolean controlloVisita(String id){
        boolean controllo=false;
        if(film.containsKey(id)){
            controllo=true;
        }
        return controllo;
    }
    public void aggiornaListaVisita(String nuovoId){
        for(Map.Entry<String, Integer> pellicola:
        film.entrySet()){

            if(pellicola.getValue()==contatore){
                film.remove(pellicola.getKey());
            }
        }
        film.put(nuovoId, contatore);

        contatore= (contatore++)%100;
    }
}
