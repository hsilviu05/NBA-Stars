package com.example.nbastarsapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbastarsapp.adapter.PlayerAdapter;
import com.example.nbastarsapp.data.DatabaseProvider;
import com.example.nbastarsapp.data.PlayerDao;
import com.example.nbastarsapp.model.Player;

import java.util.List;

public class CrudActivity extends AppCompatActivity {

    private EditText edtName, edtTeam, edtPosition, edtShortDescription,
            edtFullDescription, edtImageName, edtWebUrl;

    private PlayerDao playerDao;
    private PlayerAdapter adapter;
    private int selectedPlayerId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        edtName = findViewById(R.id.edtName);
        edtTeam = findViewById(R.id.edtTeam);
        edtPosition = findViewById(R.id.edtPosition);
        edtShortDescription = findViewById(R.id.edtShortDescription);
        edtFullDescription = findViewById(R.id.edtFullDescription);
        edtImageName = findViewById(R.id.edtImageName);
        edtWebUrl = findViewById(R.id.edtWebUrl);

        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        RecyclerView recyclerCrud = findViewById(R.id.recyclerCrud);

        playerDao = DatabaseProvider.getInstance(this).playerDao();

        adapter = new PlayerAdapter(this, playerDao.getAllPlayers(), player -> {
            selectedPlayerId = player.getId();

            edtName.setText(player.getName());
            edtTeam.setText(player.getTeam());
            edtPosition.setText(player.getPosition());
            edtShortDescription.setText(player.getShortDescription());
            edtFullDescription.setText(player.getFullDescription());
            edtImageName.setText(player.getImageName());
            edtWebUrl.setText(player.getWebUrl());

            Toast.makeText(this, "Player selected", Toast.LENGTH_SHORT).show();
        });

        recyclerCrud.setLayoutManager(new LinearLayoutManager(this));
        recyclerCrud.setAdapter(adapter);

        btnInsert.setOnClickListener(v -> insertPlayer());
        btnUpdate.setOnClickListener(v -> updatePlayer());
        btnDelete.setOnClickListener(v -> deletePlayer());
    }

    private void insertPlayer() {
        String name = edtName.getText().toString().trim();
        String team = edtTeam.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String shortDescription = edtShortDescription.getText().toString().trim();
        String fullDescription = edtFullDescription.getText().toString().trim();
        String imageName = edtImageName.getText().toString().trim();
        String webUrl = edtWebUrl.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = new Player(
                name,
                team,
                position,
                shortDescription,
                fullDescription,
                imageName,
                webUrl
        );

        playerDao.insert(player);
        refreshList();
        clearFields();
        selectedPlayerId = -1;

        Toast.makeText(this, "Player inserted", Toast.LENGTH_SHORT).show();
    }

    private void updatePlayer() {
        if (selectedPlayerId == -1) {
            Toast.makeText(this, "Select a player first", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = edtName.getText().toString().trim();
        String team = edtTeam.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String shortDescription = edtShortDescription.getText().toString().trim();
        String fullDescription = edtFullDescription.getText().toString().trim();
        String imageName = edtImageName.getText().toString().trim();
        String webUrl = edtWebUrl.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = new Player(
                name,
                team,
                position,
                shortDescription,
                fullDescription,
                imageName,
                webUrl
        );
        player.setId(selectedPlayerId);

        playerDao.update(player);
        refreshList();

        Toast.makeText(this, "Player updated", Toast.LENGTH_SHORT).show();
    }

    private void deletePlayer() {
        if (selectedPlayerId == -1) {
            Toast.makeText(this, "Select a player first", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = playerDao.getPlayerById(selectedPlayerId);

        if (player != null) {
            playerDao.delete(player);
            refreshList();
            clearFields();
            selectedPlayerId = -1;

            Toast.makeText(this, "Player deleted", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshList() {
        List<Player> players = playerDao.getAllPlayers();
        adapter.updateList(players);
    }

    private void clearFields() {
        edtName.setText("");
        edtTeam.setText("");
        edtPosition.setText("");
        edtShortDescription.setText("");
        edtFullDescription.setText("");
        edtImageName.setText("");
        edtWebUrl.setText("");
    }
}