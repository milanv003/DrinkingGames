package com.example.dg_demo;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class DorDActivity extends AppCompatActivity{
    private static Random r;
    private static ArrayList<String[]> dodges;
    private static ArrayList<String[]> dares;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dord);


    }



}
