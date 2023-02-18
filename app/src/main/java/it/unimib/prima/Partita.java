package it.unimib.prima;

import java.util.ArrayList;
import java.util.Locale;

public class Partita {
    private static Partita partita= null;
    private int tentativi=0;
    private int successi=0;
    private boolean azzeccato=false;
    private Film filmDaIndovinare;
    private listaIdVisitati idVisitati;
    private static final String TAG = Partita.class.getSimpleName();
    private Partita(){}
    public static Partita getPartita(){

        if(partita== null){
            partita= new Partita();

        }
        return partita;
    }
    public void cambiaFilm() {

        String idFilm= null;
        TOP250 listone = TOP250.getLista();
        do{

            idFilm= listone.getRandomId();
        }
        while (idVisitati.controlloVisita(idFilm));
        filmDaIndovinare = new Film(idFilm);
        azzeccato=false;
        idVisitati.aggiornaListaVisita(idFilm);
        tentativi++;
    }
    public String getInfoFilm(){
        return filmDaIndovinare.getInfo();
    }
    public String getTitoloFilm(){
        return filmDaIndovinare.get_titolo();
    }
    public boolean checkFilm(String titolo){
        if (titolo.toUpperCase().equals(filmDaIndovinare.get_titolo().toUpperCase())){
            if(!azzeccato){
                successi++;
            }
            azzeccato=true;
            return true;
        }
        else{
            return false;
        }
    }
    public String getPunteggio(){
        return String.format("pts: %d/%d", successi, tentativi);
    }
    public String resa(){
        azzeccato = true;
        return filmDaIndovinare.get_titolo();
    }
}
