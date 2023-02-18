package it.unimib.prima;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class IMDB {
    private static final String TAG = IMDB.class.getSimpleName();
    private String linguaggio= "it";
    private String chiaveImdb= "k_i9h6l2b5";
    public IMDB(){}
    public JSONObject accedi(String chiamata){

        String url_da_chiamare = "https://imdb-api.com/"+linguaggio+"/API/"+chiamata+"/"+chiaveImdb;
        return chiamata(url_da_chiamare);
    }
    public JSONObject accedi(String chiamata, String parametro){
        String url_da_chiamare = "https://imdb-api.com/"+linguaggio+"/API/"+chiamata+"/"+chiaveImdb+"/"+parametro;
        return chiamata(url_da_chiamare);
    }
    private JSONObject chiamata(String url_da_chiedere){
        String info= "";

        try {
            URL url = new URL(url_da_chiedere);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                Log.d(TAG, "eseguita input stream"+url_da_chiedere);
                try {
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();
                    while ((info = streamReader.readLine()) != null) {
                        responseStrBuilder.append(info);
                    }
                    info =responseStrBuilder.toString();
                    JSONObject jsonObject = new JSONObject(info);
                    return jsonObject;
                }
                catch (IOException e) {e.printStackTrace();}
                catch (JSONException e) {e.printStackTrace();}
            }
            catch (Exception e){Log.d(TAG, e.toString());}
            finally {
                urlConnection.disconnect();
            }
        }
        catch (MalformedURLException meu){Log.d(TAG, meu.toString());}
        catch (IOException e) {e.printStackTrace();}
        return new JSONObject();
    }
}
