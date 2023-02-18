package it.unimib.prima;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class Film {
    private static final String TAG = Film.class.getSimpleName();
    private String _id;
    private String _regista;
    private String _paese;
    private String _anno;
    private String _attori;
    private String _titolo;
    private String _sommario;

    public String get_id() {
        return _id;
    }

    public String get_regista() {
        return _regista;
    }

    public String get_paese() {
        return _paese;
    }

    public String get_anno() {
        return _anno;
    }

    public String get_attori() {
        return _attori;
    }

    public String get_titolo() {
        return _titolo;
    }

    public String get_sommario() {
        return _sommario;
    }

    public Film (String id){
        this._id= id;

        IMDB dati= new IMDB();

        try {
            JSONObject jFilm = dati.accedi("Title", id);
            this._regista=((jFilm.getJSONArray("directorList")).getJSONObject(0)).getString("name");
            this._titolo=(jFilm.getString("title"));
            this._paese=((jFilm.getJSONArray("countryList")).getJSONObject(0)).getString("value");
            this._anno=(jFilm.getString("year"));
            this._sommario=(jFilm.getString("plot"));
            this._attori=(jFilm.getString("stars"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getInfo(){
        StringBuilder info= new StringBuilder();

        info.append("regista:\t" +this._regista+"\n");
        info.append("paese:\t" +this._paese+"\n");
        info.append("anno:\t" +this._anno+"\n");
        info.append("cast:\t"+this._attori+"\n");
        info.append("trama:\t" +this._sommario);



        return  info.toString();
    }
}
