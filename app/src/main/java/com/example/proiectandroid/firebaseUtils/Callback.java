package com.example.proiectandroid.firebaseUtils;

public interface Callback<R> {
    void runOnUI(R result);
}