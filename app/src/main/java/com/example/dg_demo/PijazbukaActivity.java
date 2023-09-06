package com.example.dg_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PijazbukaActivity extends AppCompatActivity {

    private Button playButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pijazbuka);

        playButton=findViewById(R.id.buttonPijazbukaPlay);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PijazbukaActivity.this, PijazbukaGameActivity.class);
                startActivity(intent);
            }
        });
    }

}