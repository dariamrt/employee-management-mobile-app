package com.example.proiectandroid.models;

import java.io.Serializable;

public class InventoryItemFire implements Serializable {
    private int id;
    private String name;
    private int locationId;
    private static int contor = 0;

    public InventoryItemFire() {} // e nev de acesta pt firebase!!

    public InventoryItemFire(String name, int locationId) {
        this.id = ++contor;
        this.name = name;
        this.locationId = locationId;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "InventoryItemFire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locationId=" + locationId +
                '}';
    }
}

