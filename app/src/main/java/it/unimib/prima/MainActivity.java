package it.unimib.prima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mSalutaButton= findViewById(R.id.button2);

        mSalutaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editText);
                String nome= editText.getText().toString();
                TextView saluto = findViewById(R.id.saluto);
                //saluto.setText(R.string.saluto2);
                saluto.setText("Ciao " +nome);
            }
        });
    }
}