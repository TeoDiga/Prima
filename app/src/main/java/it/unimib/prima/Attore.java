package it.unimib.prima;

import android.util.Log;

import com.google.android.material.snackbar.Snackbar;

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

public class Attore {
    private static final String TAG = Attore.class.getSimpleName();
    private String infoAttore;
    private String idAttore;
    public String getInfo(){
        return this.infoAttore;
    }
    public String getIdAttore(){
        return this.idAttore;
    }
    public Attore (String nome){
        try {
            URL url = new URL("https://imdb-api.com/en/API/SearchName/k_i9h6l2b5/"+nome);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                Log.d(TAG, "eseguita");
                try {
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();
                    while ((infoAttore = streamReader.readLine()) != null) {
                        responseStrBuilder.append(infoAttore);
                    }
                    infoAttore =responseStrBuilder.toString();
                    JSONObject jsonObject = new JSONObject(infoAttore);
                    JSONArray risultati =jsonObject.getJSONArray("results");
                    JSONObject risultato = risultati.getJSONObject(0);
                    idAttore=risultato.getString("id");
                    //returns the json object
                    //return jsonObject;

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                        e.printStackTrace();
                }


            }
            catch (Exception e){
                Log.d(TAG, e.toString());
            }
            finally {
                urlConnection.disconnect();
            }
        }catch (MalformedURLException meu){
            Log.d(TAG, meu.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
