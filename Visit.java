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
                category= selected_option.getText().toString();

                Intent intent = new Intent(getApplicationContext(),CategorySelection.class);
                intent.putExtra("category",category);
                System.out.println("............"+category);
                startActivity(intent);
            }
        });
    }

}
