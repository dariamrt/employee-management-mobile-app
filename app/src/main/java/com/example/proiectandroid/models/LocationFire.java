package com.example.proiectandroid.models;

import java.io.Serializable;

public class LocationFire implements Serializable {
    private int id;
    private String name;
    private String type;
    private String city;
    private static int contor = 0;

    public LocationFire() {} // e nev de acesta pt firebase!!


    public LocationFire(String name, String type, String city) {
        this.id = ++contor;
        this.name = name;
        this.type = type;
        this.city = city;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "LocationFire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

