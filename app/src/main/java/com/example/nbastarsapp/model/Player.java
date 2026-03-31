package com.example.nbastarsapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class Player {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    private String team;
    private String position;
    private String shortDescription;
    private String fullDescription;
    private String imageName;
    private String webUrl;

    public Player(@NonNull String name, String team, String position,
                  String shortDescription, String fullDescription,
                  String imageName, String webUrl) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.imageName = imageName;
        this.webUrl = webUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}