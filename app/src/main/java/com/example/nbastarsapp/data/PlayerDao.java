package com.example.nbastarsapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nbastarsapp.model.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert
    void insert(Player player);

    @Update
    void update(Player player);

    @Delete
    void delete(Player player);

    @Query("SELECT * FROM players ORDER BY name ASC")
    List<Player> getAllPlayers();

    @Query("SELECT * FROM players WHERE id = :id LIMIT 1")
    Player getPlayerById(int id);

    @Query("SELECT COUNT(*) FROM players")
    int getCount();
}