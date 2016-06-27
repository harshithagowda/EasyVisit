package com.wireless.cse5345.easyvisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Home extends AppCompatActivity {

    private EditText searchLocation;
    Button visit;
    Button plan_travel;
    String location = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchLocation = (EditText)findViewById(R.id.searchLocation);

        addListenerOnButtonVisit();
        addListenerOnButtonPlan();
    }

    public void addListenerOnButtonVisit(){
        searchLocation = (EditText)findViewById(R.id.searchLocation);
        visit = (Button)findViewById(R.id.visit_button);


        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                location = searchLocation.getText().toString();
                System.out.println("............."+location);
                Intent goto_view = new Intent(getApplicationContext(),Visit.class);
                goto_view.putExtra("location",location);
                startActivity(goto_view);

            }
        });

    }

    public void addListenerOnButtonPlan(){
        plan_travel = (Button)findViewById(R.id.plan_button);


        plan_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                location = searchLocation.getText().toString();
                System.out.println("............."+location);
                Intent goto_plan = new Intent(getApplicationContext(),TravelPlan.class);
                goto_plan.putExtra("location",location);
                startActivity(goto_plan);
            }
        });


    }

}
