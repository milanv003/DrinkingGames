package com.example.dg_demo;

public class PlayerDorD {
    private String name;
    private int points;
    private PlayerDorD buddy;

    public PlayerDorD(String name) {
        this.name = name;
        points = 0;
        buddy = null;
    }

    public void setBuddy(PlayerDorD buddy) {
        this.buddy = buddy;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public PlayerDorD getBuddy() {
        return buddy;
    }

    public void addPoints(int p){
        points += p;
    }
}
