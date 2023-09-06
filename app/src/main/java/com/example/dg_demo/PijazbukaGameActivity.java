package com.example.dg_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PijazbukaGameActivity extends AppCompatActivity {

    private TextView textTema;
    private TextView[] textSlovo;

    private String[] slova={"A","B","C","Č","Ć","D","Đ","E","F","G","H","I","J","K","L","LJ","M","N"
            ,"NJ","O","P","R","S","Š","T","U","V","Z","Ž"};

    private String[] teme={"SPORT","ŽIVOTINJA","ZEMLJA","GRAD","FILM","JEZERO","PLANINA","REKA"
            ,"BILJKA","PREDMET","IME","POZNATA LIČNOST"};

    private int brojIgraca = MainActivity.names.size();

    private String trenSlovo,trenTema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pijazbuka_game);

        textSlovo= new TextView[]{findViewById(R.id.textSlovoGlavno),
                findViewById(R.id.textSlovo1), findViewById(R.id.textSlovo2), findViewById(R.id.textSlovo3)
                , findViewById(R.id.textSlovo4), findViewById(R.id.textSlovo5), findViewById(R.id.textSlovo6)
                , findViewById(R.id.textSlovo7)};
        textTema=findViewById(R.id.textTema);
        textSlovo[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dodelaSlovaITeme(false);


    }

    private void dodelaSlovaITeme(boolean megarandom)
    {
        Random rand=new Random();

        trenSlovo=slova[rand.nextInt(slova.length)];
        trenTema=teme[rand.nextInt(teme.length)];

        for (TextView textView : textSlovo) {
            textView.setText(trenSlovo);
            if(megarandom)
                trenSlovo=slova[rand.nextInt(slova.length)];
        }
        textTema.setText(trenTema);
    }



}