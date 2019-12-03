package com.example.projectfitness.Model;

public class History {
    private int level;
    private int point;
    private String historyid;
    private String mission;
    private String count;
    private int time;

    public History(String historyid,String mission) {
        this.mission = mission;

    }

    public History() {
    }

    public String getHistoryid() {
        return historyid;
    }

    public void setHistoryid(String historyid) {
        this.historyid = historyid;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

}
