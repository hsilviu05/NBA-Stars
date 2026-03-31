package com.example.nbastarsapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastarsapp.data.DatabaseProvider;
import com.example.nbastarsapp.model.Player;

public class PlayerFullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_full);

        TextView txtName = findViewById(R.id.txtPlayerNameFull);
        TextView txtFull = findViewById(R.id.txtFullDescription);

        int playerId = getIntent().getIntExtra("player_id", -1);

        Player player = DatabaseProvider
                .getInstance(this)
                .playerDao()
                .getPlayerById(playerId);

        if (player != null) {
            txtName.setText(player.getName());
            txtFull.setText(player.getFullDescription());
        }
    }
}