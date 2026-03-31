package com.example.nbastarsapp.data;

import android.content.Context;

import androidx.room.Room;

public class DatabaseProvider {

    private static AppDatabase db;

    public static AppDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "nba_stars_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}