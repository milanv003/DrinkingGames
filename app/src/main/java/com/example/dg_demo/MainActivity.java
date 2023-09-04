package com.example.dg_demo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private Button startButton;
    private Button addButton;
    private Button[] nameButtons = new Button[16];
    public static ArrayList<String> names = new ArrayList<String>();
    private EditText textField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameLibraryActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textField.getText().toString().trim().length() > 0 && names.size()<16){
                    String temp = textField.getText().toString().trim();
                    boolean noCopy = true;
                    for(int i = 0; i<names.size();i++){
                        if(temp.equals(names.get(i))){
                            noCopy = false;
                            break;
                        }
                    }
                    if(noCopy){
                        names.add(temp);
                        updateButtons();
                    }else{
                        Toast.makeText(MainActivity.this, "Name Already Exists", Toast.LENGTH_SHORT).show();
                    }
                    textField.setText("");
                }
                if(names.size() == 16){
                    //TODO: Mora da se refreshuje stranica da bi se dugme deaktiviralo
                    addButton.setActivated(false);
                }
            }
        });

        try{
            for(int i = 0; i<16;i++){
                final int index = i;
                nameButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        names.remove(nameButtons[index].getText().toString());
                        updateButtons();
                    }
                });
            }
        }catch (Exception e){

        }
    }

    private void initViews(){
        startButton = findViewById(R.id.startButton);
        addButton = findViewById(R.id.addButton);
        textField = findViewById(R.id.inputField);

        for(int i = 0; i<16; i++){
            int buttonId = getResources().getIdentifier("name" + (i + 1), "id", getPackageName());

            nameButtons[i] = findViewById(buttonId);
            nameButtons[i].setVisibility(View.GONE);
        }

    }

    private void updateButtons(){
        for(int i = 0; i<names.size(); i++){
            nameButtons[i].setText(names.get(i));
            nameButtons[i].setVisibility(View.VISIBLE);
        }
        for(int i = names.size(); i<16; i++){
            nameButtons[i].setVisibility(View.GONE);
        }
        if(names.size()<16){
            addButton.setActivated(true);
        }
    }

}
