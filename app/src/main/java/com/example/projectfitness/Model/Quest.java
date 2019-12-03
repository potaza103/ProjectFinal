package com.example.projectfitness.Model;

public class Quest {
    private int level;
    private int point;
    private String mission;
    private String count;
    private int time;

    public Quest(int level, int point, String mission, String count, int time) {
        this.level = level;
        this.mission = mission;
        this.count = count;
        this.time = time;
        this.point = point;
    }

    public Quest() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}