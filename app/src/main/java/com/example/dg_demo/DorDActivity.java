package com.example.dg_demo;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class DorDActivity extends AppCompatActivity{
    private static PlayerDorD[] players;
    private static Random r;
    private static ArrayList<String[]> dodges;
    private static ArrayList<String[]> dares;

    private ImageView dareButton;
    private ImageView dodgeButton;

    private int odluka = 3;

    private int currentIteration = 0;

    private CountDownLatch clickLatch;


    private void doDodge(int n, String[] dodge) {
        int m = r.nextInt(players.length);
        while(m==n){
            m = r.nextInt(players.length);
        }
        //TODO: Prebacivanje na Dodge Stranicu
        setContentView(R.layout.activity_dodge);



    }

    private void doDare(int n, String[] dare){
        int m = r.nextInt(players.length);
        while(m==n){
            m = r.nextInt(players.length);
        }
        //TODO: Prebacivanje na Dare Stranicu
        setContentView(R.layout.activity_dare);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dord);

        dodgeButton = findViewById(R.id.chooseDodge);
        dareButton = findViewById(R.id.chooseDare);


        players = new PlayerDorD[MainActivity.names.size()];

        for(int i = 0; i < MainActivity.names.size(); i++){
            players[i] = new PlayerDorD(MainActivity.names.get(i));
        }

        try{
            InputStream dodgeInputStream = getAssets().open("dodge.txt");
            InputStream dareInputStream = getAssets().open("dare.txt");

            Scanner dodgeScanner = new Scanner(dodgeInputStream);
            Scanner dareScanner = new Scanner(dareInputStream);

            dodges = new ArrayList<>();
            dares = new ArrayList<>();

            while (dodgeScanner.hasNextLine()) {
                dodges.add(dodgeScanner.nextLine().split(";"));
            }

            while (dareScanner.hasNextLine()) {
                dares.add(dareScanner.nextLine().split(";"));
            }

            dodgeScanner.close();
            dareScanner.close();

            dodgeInputStream.close();
            dareInputStream.close();
            r = new Random();

            //insert loop here
            startGameLoop();

        }catch (Exception e){
            Toast.makeText(DorDActivity.this, "File not found.", Toast.LENGTH_SHORT).show();
        }

    }

    private void startGameLoop() {
        if (currentIteration < 5 * players.length) {
            System.out.println(players[currentIteration % players.length].getName() + " (" + players[currentIteration % players.length].getPoints() + "):");

            clickLatch = new CountDownLatch(1);

            // Prompt the user for choice in each iteration
            getUserChoice();
        } else {
            // The game has ended
            // You can add any final actions or messages here
            Toast.makeText(DorDActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
        }
    }

    private void getUserChoice() {
        dareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odluka = 1;
                performAction();
            }
        });

        dodgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odluka = 2;
                performAction();
            }
        });
    }

    private void performAction() {
        switch (odluka) {
            case 1:
                doDare(currentIteration % players.length, dares.get(r.nextInt(dares.size())));
                break;
            case 2:
                doDodge(currentIteration % players.length, dodges.get(r.nextInt(dodges.size())));
                break;
        }
        currentIteration++;
        clickLatch.countDown();
        startGameLoop();
    }



}
