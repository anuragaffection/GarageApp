package com.example.garageapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView dashBoard;
    Button logout;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dashBoard = findViewById(R.id.dashboard);
        logout = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null){
            Intent intent = new Intent(MainActivity.this, LoginScreen.class);
            startActivity(intent);
            finish();
        } else {
            dashBoard.setText(user.getEmail());
        }

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginScreen.class);
            startActivity(intent);
            finish();
        });

        ArrayList<model> arrCars = new ArrayList<>();
        Spinner spinnerSelectMake = findViewById(R.id.selectMake);
        RecyclerView yourCarsList = findViewById(R.id.yourCarsList);



        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {

                    ArrayList<String> spinnerArray = new ArrayList<>();

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String makeName = jsonObject.getString("Make_Name");
                            spinnerArray.add(makeName);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            MainActivity.this, android.R.layout.simple_spinner_item, spinnerArray);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerSelectMake.setAdapter(adapter);

                }, Throwable::printStackTrace);

        queue.add(jsonArrayRequest);




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        yourCarsList.setLayoutManager(linearLayoutManager);


        model l1 = new model(R.drawable.img, "Tata ", "Nano", "Add Car Image ", "Delete Car");
        model l2 = new model(R.drawable.img, "Volkswagen ", "Rolls Royce", "Add Car Image ", "Delete Car");
        model l3 = new model(R.drawable.img, "Volkswagen ", "Rolls Royce", "Add Car Image ", "Delete Car");
        model l4 = new model(R.drawable.img, "TaTa ", "Nano", "Add Car Image ", "Delete Car");
        model l5 = new model(R.drawable.img, "Volkswagen ", "Rolls Royce", "Add Car Image ", "Delete Car");
        model l6 = new model(R.drawable.img, "Volkswagen ", "Rolls Royce", "Add Car Image ", "Delete Car");


        arrCars.add(l1);
        arrCars.add(l2);
        arrCars.add(l3);
        arrCars.add(l4);
        arrCars.add(l5);
        arrCars.add(l6);

        YourCarsAdapter yourCarsAdapter = new YourCarsAdapter(this, arrCars);
        yourCarsList.setAdapter(yourCarsAdapter);
        yourCarsList.setNestedScrollingEnabled(false);


    }
}