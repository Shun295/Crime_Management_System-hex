package com.model;

public class Officer {
    private int id;
    private String name;

    private User user;
    private StationHead stationHead;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StationHead getStationHead() {
        return stationHead;
    }

    public void setStationHead(StationHead stationHead) {
        this.stationHead = stationHead;
    }

    @Override
    public String toString() {
        return "Officer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", stationHead=" + stationHead +
                '}';
    }
}
