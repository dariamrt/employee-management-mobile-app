package com.example.proiectandroid.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectandroid.R;
import com.example.proiectandroid.firebaseUtils.Callback;
import com.example.proiectandroid.firebaseUtils.FirebaseService;
import com.example.proiectandroid.models.InventoryItemFire;
import com.example.proiectandroid.models.LocationFire;

import java.util.ArrayList;
import java.util.List;

public class FirebaseCrudActivity extends AppCompatActivity {
    private EditText etName, etType, etCity, etItemName, etLocationId;
    private Button btnAddLocation, btnAddItem;
    private ListView lvItems;
    private ListView lvLocations;
    private FirebaseService firebaseService;
    private List<LocationFire> locations = new ArrayList<>();
    private List<InventoryItemFire> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_crud);

        initViews();
        firebaseService = FirebaseService.getInstance();
        firebaseService.addLocationsListener(locationsCallback());
        firebaseService.addInventoryItemsListener(itemsCallback());
    }

    private void initViews() {
        etName = findViewById(R.id.etLocationName);
        etType = findViewById(R.id.etLocationType);
        etCity = findViewById(R.id.etLocationCity);
        etItemName = findViewById(R.id.etItemName);
        etLocationId = findViewById(R.id.etItemLocationId);

        btnAddLocation = findViewById(R.id.btnAddLocation);
        btnAddItem = findViewById(R.id.btnAddItem);

        lvItems = findViewById(R.id.lvItems);
        lvLocations = findViewById(R.id.lvLocations);

        btnAddLocation.setOnClickListener(view -> addLocation());
        btnAddItem.setOnClickListener(view -> addItem());
    }

    private Callback<List<LocationFire>> locationsCallback() {
        return rezultat -> {
            locations.clear();
            locations.addAll(rezultat);
            ArrayAdapter<LocationFire> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);
            lvLocations.setAdapter(adapter);
        };
    }

    private Callback<List<InventoryItemFire>> itemsCallback() {
        return rezultat -> {
            items.clear();
            items.addAll(rezultat);
            ArrayAdapter<InventoryItemFire> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            lvItems.setAdapter(adapter);
        };
    }

    private void addLocation() {
        String name = etName.getText().toString().trim();
        String type = etType.getText().toString().trim();
        String city = etCity.getText().toString().trim();

        if (name.isEmpty() || type.isEmpty() || city.isEmpty()) {
            Toast.makeText(this, "Complete all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseService.insertLocation(new LocationFire(name, type, city));

        etName.setText("");
        etType.setText("");
        etCity.setText("");
    }

    private void addItem() {
        String name = etItemName.getText().toString().trim();
        String locationIdText = etLocationId.getText().toString().trim();

        if (name.isEmpty() || locationIdText.isEmpty()) {
            Toast.makeText(this, "Complete all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Integer locationId = Integer.parseInt(locationIdText);
            firebaseService.insertInventoryItem(new InventoryItemFire(name, locationId));

            etItemName.setText("");
            etLocationId.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Location ID must be a number", Toast.LENGTH_SHORT).show();
        }
    }
}