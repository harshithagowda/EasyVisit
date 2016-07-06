package com.wireless.cse5345.easyvisit;

/**
 * Created by Harshitha Gowda on 7/3/2016.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    Bundle extras;
    String points;
    private GoogleMap mMap;
    String[] coordinates;
    String latitude = null;
    String longitude = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        extras = getIntent().getExtras();
        if (extras != null) {
            points = extras.getString("coordinates");
            coordinates = points.split(",");

            longitude = coordinates[0].replace("[","");
            latitude = coordinates[1].replace("]","");
            System.out.println("the longitude value is:"+longitude);
            System.out.println("the latitude value is:"+latitude);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
       // Uri gmmIntentUri = Uri.parse("google.navigation:q=47.62078,-122.34375&mode=d");
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude+"&mode=d");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


}

