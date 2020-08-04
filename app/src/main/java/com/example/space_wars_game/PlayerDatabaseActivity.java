package com.example.space_wars_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class PlayerDatabaseActivity extends AppCompatActivity {


    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_database);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        ArrayList userList = getListData();
        final ListView lv = (ListView) findViewById(R.id.user_list);
        lv.setAdapter(new CustomListAdapter(this, userList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);
                Toast.makeText(PlayerDatabaseActivity.this, "Selected :" + " " + user.getName()+", "+ user.getScore(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private ArrayList getListData() {

        RealmResults<PlayerDetails> results = realm.where(PlayerDetails.class).findAll();
        ArrayList<ListItem> result = new ArrayList<>();

        for(PlayerDetails player : results){

            ListItem user1 = new ListItem();
            user1.setName(player.getPlayerName());
            user1.setScore(String.valueOf(player.getScore()));
            result.add(user1);
        }
        return result;
    }

    public void returnToMainMenu(View view) {

        Intent intent = new Intent(PlayerDatabaseActivity.this,MainMenuActivity.class);
        startActivity(intent);
    }
}

