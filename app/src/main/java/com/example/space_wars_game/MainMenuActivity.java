package com.example.space_wars_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    Button buttonStart;
    Button buttonResult;
    Button buttonExit;
    Button buttonPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        buttonStart=(Button)findViewById(R.id.buttonStart);
        buttonResult=(Button)findViewById(R.id.buttonResult);
        buttonExit=(Button)findViewById(R.id.buttonExit);
        buttonPlayerView=(Button)findViewById(R.id.buttonViewScore);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this,MainActivity.class);
                startActivity(intent);
                MainMenuActivity.this.finish();
            }
        });
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this,Result.class);
                startActivity(intent);
                MainMenuActivity.this.finish();

            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainMenuActivity.this.finish();
            }
        });

        buttonPlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this,PlayerDatabaseActivity.class);
                startActivity(intent);
                MainMenuActivity.this.finish();
            }
        });

    }
}

