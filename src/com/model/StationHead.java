package com.model;

public class StationHead {
    private int id;
    private String name;

    private User User;

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
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    @Override
    public String toString() {
        return "StationHead{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", User=" + User +
                '}';
    }
}
