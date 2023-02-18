package it.unimib.prima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.StrictMode;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Partita partita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        partita = Partita.getPartita();

    }
    @Override
    protected void onStart() {
        super.onStart();

        partita.cambiaFilm();
        TextView infoFilm = findViewById(R.id.dati_film);
        infoFilm.setText(partita.getInfoFilm());

        Button mSalutaButton = findViewById(R.id.button2);
        Button mGiocaButton= findViewById(R.id.bottone_gioco);
        Button mResaButton= findViewById(R.id.bottone_resa);
        mSalutaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editText);
                String titolo = editText.getText().toString();
                String esito = null;
                TextView risposta= findViewById(R.id.risposta);
                if (partita.checkFilm(titolo)) {
                    esito = "bravo";
                } else {
                    esito = "asino";
                }
                risposta.setText(esito);
                TextView punteggio=findViewById(R.id.Punteggio);
                punteggio.setText(partita.getPunteggio());
            }
        });
        mResaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView risposta= findViewById(R.id.risposta);
                risposta.setText(partita.resa());
            }
        });
        mGiocaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partita.cambiaFilm();
                TextView infoFilm = findViewById(R.id.dati_film);
                infoFilm.setText(partita.getInfoFilm());
                ((TextView) findViewById(R.id.Punteggio)).setText(partita.getPunteggio());
                ((TextView) findViewById(R.id.risposta)).setText("");
                ((TextView) findViewById(R.id.editText)).setText("");
            }
        });
    }

}