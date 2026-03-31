package com.example.nbastarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastarsapp.data.DatabaseProvider;
import com.example.nbastarsapp.data.PlayerDao;
import com.example.nbastarsapp.model.Player;

public class PlayerBasicActivity extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_basic);

        ImageView imgPlayer = findViewById(R.id.imgPlayerBasic);
        TextView txtName = findViewById(R.id.txtPlayerNameBasic);
        TextView txtShort = findViewById(R.id.txtShortDescription);
        Button btnMore = findViewById(R.id.btnMoreDetails);
        Button btnWeb = findViewById(R.id.btnWebInfo);

        int playerId = getIntent().getIntExtra("player_id", -1);

        PlayerDao playerDao = DatabaseProvider.getInstance(this).playerDao();
        player = playerDao.getPlayerById(playerId);

        if (player != null) {
            txtName.setText(player.getName());
            txtShort.setText(player.getShortDescription());

            int imageResId = getResources().getIdentifier(
                    player.getImageName(),
                    "drawable",
                    getPackageName()
            );

            imgPlayer.setImageResource(imageResId);
        }

        btnMore.setOnClickListener(v -> {
            Intent intent = new Intent(PlayerBasicActivity.this, PlayerFullActivity.class);
            intent.putExtra("player_id", player.getId());
            startActivity(intent);
        });

        btnWeb.setOnClickListener(v -> {
            Intent intent = new Intent(PlayerBasicActivity.this, PlayerWebActivity.class);
            intent.putExtra("player_id", player.getId());
            startActivity(intent);
        });

    }
}