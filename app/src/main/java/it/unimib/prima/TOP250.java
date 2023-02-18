package it.unimib.prima;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TOP250 {
    private static TOP250 istanza=null;
    private static ArrayList<String> idFilm= new ArrayList<String>();
    private TOP250(){

    }
    public static TOP250 getLista(){
        if(istanza == null){
            istanza=new TOP250();
            IMDB imdb = new IMDB();
            JSONObject listone= imdb.accedi("Top250Movies");
            try {
                JSONArray elenco = listone.getJSONArray("items");
                JSONObject film = new JSONObject();
                for (int i = 0; i < 250; i++) {
                    film = elenco.getJSONObject(i);
                    idFilm.add(film.getString("id"));
                }
            }catch(JSONException je){ je.printStackTrace();}

        }
        return istanza;
    }
    public String getRandomId(){
        long time= System.currentTimeMillis();
        long num=  time%1000;
        int i= (int) num%250;
        return idFilm.get(i);
    }

}
