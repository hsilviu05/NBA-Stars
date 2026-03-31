package com.example.nbastarsapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.nbastarsapp.model.Player;

@Database(entities = {Player.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
}