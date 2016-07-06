package com.wireless.cse5345.easyvisit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Harshitha Gowda on 6/26/2016.
 */
public class TravelPlan extends AppCompatActivity {
    private EditText searchLocation1;
    private EditText searchLocation2;
    private EditText searchLocation3;
    private EditText searchLocation4;
    Button optimizeRoute;
    String location1 = null;
    String location2 = null;
    String location3 = null;
    String location4 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_view);
        planMyTravel();

    }

    public void planMyTravel() {
        optimizeRoute = (Button)findViewById(R.id.optimizeRoute);
        optimizeRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLocation1 = (EditText)findViewById(R.id.location1);
                searchLocation2 = (EditText)findViewById(R.id.location2);
                searchLocation3 = (EditText)findViewById(R.id.location3);
                searchLocation4 = (EditText)findViewById(R.id.location4);
                location1 = searchLocation1.getText().toString();
                location2 = searchLocation2.getText().toString();
                location3 = searchLocation3.getText().toString();
                location4 = searchLocation4.getText().toString();
                System.out.println(location1);
                System.out.println(location2);
                System.out.println(location3);
                System.out.println(location4);
                Intent intent = new Intent(getApplicationContext(),CategorySelection.class);
                intent.putExtra("location1",location1);
                intent.putExtra("location2",location2);
                intent.putExtra("location3",location3);
                intent.putExtra("location4",location4);
                startActivity(intent);
            }
        });
    }
}

