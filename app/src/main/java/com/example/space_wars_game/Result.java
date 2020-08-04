package com.example.space_wars_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;

public class Result extends AppCompatActivity {

    Button buttonReturnToMain;
    TextView timestampText;
    Realm realm;
    EditText playerName;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel =(TextView)findViewById(R.id.scoreLabel);
        TextView loseScore =(TextView)findViewById(R.id.scoreLose);
        TextView highScoreLabel =(TextView)findViewById(R.id.highScoreLabel);
        timestampText = (TextView) findViewById(R.id.timeStamp);
        playerName = (EditText)findViewById(R.id.playerNameEditText);

        buttonReturnToMain=(Button)findViewById(R.id.Wroc);


        buttonReturnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecordToDatabase();
                Intent intent = new Intent(Result.this,MainMenuActivity.class);
                startActivity(intent);
                Result.this.finish();
            }
        });

        score = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText("YOU SHOT: "+score+" UFOs");
        int losescore = getIntent().getIntExtra("SCORELOSE",0);
        loseScore.setText("YOU MISSED 5 UFOs");

        String timeStampvalue = getIntent().getStringExtra("TimeStamp");
        timestampText.setText(timeStampvalue);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore=settings.getInt("HIGH_SCORE",0);

        if(score>highScore)
        {
            highScoreLabel.setText("HIGH SCORE: "+score);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE",score);

            editor.commit();
        }
        else
        {
            highScoreLabel.setText("HIGH SCORE: "+highScore);
        }

        Realm.init(this);
        realm = Realm.getDefaultInstance();



    }

    public void addRecordToDatabase()
    {
//        if (playerName.getText().toString()!=null) {
        realm.beginTransaction();

        PlayerDetails playerDetails = realm.createObject(PlayerDetails.class);
        playerDetails.setPlayerName((playerName.getText().toString()));
        playerDetails.setScore(score);

        realm.commitTransaction();
//        }
    }



    public void TryAgain(View view)
    {
        addRecordToDatabase();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        addRecordToDatabase();
        Intent intent = new Intent(Result.this,MainMenuActivity.class);
        startActivity(intent);
        Result.this.finish();

    }
}

