package com.example.proiectandroid.firebaseUtils;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.proiectandroid.models.InventoryItemFire;
import com.example.proiectandroid.models.LocationFire;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {
    private final DatabaseReference reference;
    private static FirebaseService firebaseService;

    private FirebaseService() {
        reference = FirebaseDatabase.getInstance("https://proiectandroidgestiunefirma-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    }

    public static FirebaseService getInstance() {
        if (firebaseService == null) {
            synchronized (FirebaseService.class) {
                if (firebaseService == null) {
                    firebaseService = new FirebaseService();
                }
            }
        }
        return firebaseService;
    }

    public void insertLocation(LocationFire location) {
        if (location == null) return;
        String id = String.valueOf(location.getId());
        reference.child("locations").child(id).setValue(location)
                .addOnSuccessListener(aVoid -> Log.d("FirebaseService", "Location added successfully!!!"))
                .addOnFailureListener(e -> Log.e("FirebaseService", "Failed to add location:(", e));
    }

    public void insertInventoryItem(InventoryItemFire item) {
        if (item == null) return;
        String id = String.valueOf(item.getId());
        reference.child("inventoryItems").child(id).setValue(item)
                .addOnSuccessListener(aVoid -> Log.d("FirebaseService", "Inventory item added successfully!!"))
                .addOnFailureListener(e -> Log.e("FirebaseService", "Failed to add inventory item:(", e));
    }


    public void addLocationsListener(Callback<List<LocationFire>> callback) {
        reference.child("locations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<LocationFire> locations = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    LocationFire location = data.getValue(LocationFire.class);
                    if (location != null) {
                        location.setId(Integer.parseInt(data.getKey()));
                        locations.add(location);
                    }
                }
                callback.runOnUI(locations);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService", "Error reading locations!!!!", error.toException());
            }
        });
    }

    public void addInventoryItemsListener(Callback<List<InventoryItemFire>> callback) {
        reference.child("inventoryItems").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<InventoryItemFire> items = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    InventoryItemFire item = data.getValue(InventoryItemFire.class);
                    if (item != null) {
                        item.setId(Integer.parseInt(data.getKey()));
                        items.add(item);
                    }
                }
                callback.runOnUI(items);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseService", "Error reading the items", error.toException());
            }
        });
    }
}
