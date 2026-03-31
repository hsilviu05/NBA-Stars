package com.example.nbastarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbastarsapp.adapter.PlayerAdapter;
import com.example.nbastarsapp.data.AppDatabase;
import com.example.nbastarsapp.data.DatabaseProvider;
import com.example.nbastarsapp.data.PlayerDao;
import com.example.nbastarsapp.model.Player;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPlayers;
    private PlayerAdapter adapter;
    private PlayerDao playerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerPlayers = findViewById(R.id.recyclerPlayers);
        Button btnCrud = findViewById(R.id.btnCrud);

        AppDatabase db = DatabaseProvider.getInstance(this);
        playerDao = db.playerDao();

        insertStarterDataIfNeeded();

        List<Player> players = playerDao.getAllPlayers();

        adapter = new PlayerAdapter(this, players, player -> {
            Intent intent = new Intent(MainActivity.this, PlayerBasicActivity.class);
            intent.putExtra("player_id", player.getId());
            startActivity(intent);
        });

        recyclerPlayers.setLayoutManager(new LinearLayoutManager(this));
        recyclerPlayers.setAdapter(adapter);

        btnCrud.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CrudActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateList(playerDao.getAllPlayers());
    }

    private void insertStarterDataIfNeeded() {
        if (playerDao.getCount() == 0) {
            playerDao.insert(new Player(
                    "LeBron James",
                    "Los Angeles Lakers",
                    "Forward",
                    "One of the greatest players in NBA history.",
                    "LeBron James is known for scoring, passing, leadership, and longevity.",
                    "lebron",
                    "https://en.wikipedia.org/wiki/LeBron_James"
            ));

            playerDao.insert(new Player(
                    "Stephen Curry",
                    "Golden State Warriors",
                    "Guard",
                    "Famous for three-point shooting.",
                    "Stephen Curry changed basketball with elite long-range shooting.",
                    "curry",
                    "https://en.wikipedia.org/wiki/Stephen_Curry"
            ));

            playerDao.insert(new Player(
                    "Nikola Jokic",
                    "Denver Nuggets",
                    "Center",
                    "Elite passer and scorer.",
                    "Nikola Jokic is known for vision, efficiency, and versatility.",
                    "jokic",
                    "https://en.wikipedia.org/wiki/Nikola_Joki%C4%87"
            ));

            playerDao.insert(new Player(
                    "Kevin Durant",
                    "Phoenix Suns",
                    "Forward",
                    "One of the best scorers ever.",
                    "Kevin Durant is an elite scorer with championship experience.",
                    "durant",
                    "https://en.wikipedia.org/wiki/Kevin_Durant"
            ));

            playerDao.insert(new Player(
                    "Luka Doncic",
                    "Los Angeles Lakers",
                    "Guard",
                    "Creative scorer and playmaker.",
                    "Luka Doncic is a superstar known for scoring and playmaking.",
                    "doncic",
                    "https://en.wikipedia.org/wiki/Luka_Don%C4%8Di%C4%87"
            ));
        }
    }
}