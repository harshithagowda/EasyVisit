package com.wireless.cse5345.easyvisit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Harshitha Gowda on 6/26/2016.
 */
public class Visit extends AppCompatActivity {

    private RadioButton selected_option;
    private RadioGroup selected_group;
    String category = null;
    Button search;
    Bundle extras;
    String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        addListenerOnButtonSearch();

    }

    public void addListenerOnButtonSearch(){

        search = (Button)findViewById(R.id.search);
        selected_group = (RadioGroup)findViewById(R.id.searchCategory);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedID = selected_group.getCheckedRadioButtonId();
                selected_option = (RadioButton)findViewById(selectedID);
                extras = getIntent().getExtras();
                location = extras.getString("location");
                category= selected_option.getText().toString();

                System.out.println("............"+category);
                System.out.println("..........."+location);


                if(category.equals("Tourist Attractions")){
                    Intent intent = new Intent(getApplicationContext(),Visit_Tourist_Attractions.class);
                    intent.putExtra("category",category);
                    intent.putExtra("location",location);
                    startActivity(intent);
                }
                else if(category.equals("Restaurants")){
                    Intent intent = new Intent(getApplicationContext(),Visit_Restaurants.class);
                    intent.putExtra("category",category);
                    intent.putExtra("location",location);
                    startActivity(intent);
                }
                else if(category.equals("Hotels")){
                    Intent intent = new Intent(getApplicationContext(),Visit_Hotels.class);
                    intent.putExtra("category",category);
                    intent.putExtra("location",location);
                    startActivity(intent);
                }

            }
        });
    }

}
