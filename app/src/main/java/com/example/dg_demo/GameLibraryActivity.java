package com.example.dg_demo;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class GameLibraryActivity extends AppCompatActivity {
    private int chosenGame = 1;
    private ImageView imageView;
    private ImageView diff;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_library);
        imageView = findViewById(R.id.gameDisplay);
        diff = findViewById(R.id.difficulty);

        gestureDetector = new GestureDetector(this, new MyGestureListener());

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event); // Pass the touch event to the GestureDetector
                return true; // Consume the touch event
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(chosenGame){
                    case 1:
                        //Doge Or Dare
                        break;
                    case 2:
                        //Pijazbuka
                        break;
                }
            }
        });

    }

    private void updateImage() {
        switch (chosenGame) {
            case 1:
                imageView.setImageResource(R.mipmap.dord_logo);
                diff.setImageResource(R.mipmap.difficulty5);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.pijazbuka_logo);
                diff.setImageResource(R.mipmap.difficulty3);
                break;
            // Add cases for other games

            default:
                imageView.setImageResource(R.mipmap.dord_logo); // Set a default image
                break;
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 128; // Minimum swipe distance in pixels

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();

            if (Math.abs(diffX) > SWIPE_THRESHOLD) {
                if (diffX > 0) {
                    // Swiped from left to right (decrease chosenGame)
                    chosenGame--;
                    if (chosenGame < 1) {
                        chosenGame = 1;
                    }
                } else {
                    // Swiped from right to left (increase chosenGame)
                    chosenGame++;
                    // Update the maximum value based on the number of games
                    int maxGames = 2; // Update this to the actual number of games
                    if (chosenGame > maxGames) {
                        chosenGame = maxGames;
                    }
                }
                updateImage();
            }

            return true;
        }
    }
}
