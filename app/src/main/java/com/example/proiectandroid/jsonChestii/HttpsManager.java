package com.example.proiectandroid.jsonChestii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsManager {
    // urlAddress, reader, connection
    private String urlAddress;
    private BufferedReader reader;
    private HttpsURLConnection connection;

    // constructor generat
    public HttpsManager(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    // preluare Json din Https aka citire
    private String preluareJsonDinHttps() throws IOException {
        connection = (HttpsURLConnection) new URL(urlAddress).openConnection();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder builder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line);
        }
        return builder.toString();
    }

    // inchidereConexiuni
    private void inchidereConexiuni(){
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection.disconnect();
    }

    // procesare
    public String procesare(){
        try {
            return preluareJsonDinHttps();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            inchidereConexiuni();
        }
    }

}

