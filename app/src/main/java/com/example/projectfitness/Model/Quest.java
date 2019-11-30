package com.example.projectfitness.Model;

public class Quest {
    private String level;
    private String point;
    private String mission;
    private String count;
    private String time;

    public Quest(String level, String point, String mission, String count, String time) {
        this.level = level;
        this.mission = mission;
        this.count = count;
        this.time = time;
        this.point = point;
    }

    public Quest() {
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}