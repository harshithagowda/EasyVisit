package com.wireless.cse5345.easyvisit;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by Harshitha Gowda on 6/26/2016.
 */
public class Visit_Tourist_Attractions extends AppCompatActivity {

    String url = null;
    String latlng[] = null;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_view);

        extras = getIntent().getExtras();
        if (extras != null) {
            latlng = extras.getStringArray("latlng");
            //System.out.println("The cordinates fetched are:..........."+latlng[0]+latlng[1]);

        }
        Call call = new Call();
        call.execute(latlng);


    }


    class Call extends AsyncTask<String, String, JSONArray> {

        JSONArray jsonArr;
        String[] lm;
        String hotelName;
        String Name=null;



        @Override
        protected JSONArray doInBackground(String... params) {

            url = "http://terminal2.expedia.com/x/geo/features?within=1km&lng="+latlng[1]+"&lat="+latlng[0]+"&type=point_of_interest&apikey=L2ZB8VtZrd2AKA8ZjvysemHTVJAWIrMC";

            jsonArr = RestParser.getResponseForUrl(url, "GET");
            System.out.println("Trying to print........." + params[0]);
            return jsonArr;
        }

        @Override
        protected void onPostExecute(final JSONArray result) {

            lm = JsonParser(result);
            ListView r_listView = (ListView) findViewById(R.id.listView);
            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,lm);
            r_listView.setAdapter(adapter);
            r_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("Inside onclick");
                    Name = lm[position];
                    System.out.println("trying to send the name of the place"+Name);

                    try{

                        for(int j=0;j<result.length();j++){
                            String coordinates;
                            String name;
                            JSONObject value = result.getJSONObject(j);
                            name = value.getString("name");
                            if(name.equals(lm[position])){

                                JSONObject pos = value.getJSONObject("position");
                                coordinates = pos.getString("coordinates");
                                System.out.println("the tourist place coordinates are :"+coordinates);

                                Intent navigate = new Intent(Visit_Tourist_Attractions.this,MapsActivity.class);
                                navigate.putExtra("coordinates",coordinates);
                                startActivity(navigate);
                            }
                            else
                                continue;
                        }
                    }
                    catch(Exception Ex){
                        Ex.printStackTrace();
                    }
                }
            });


        }

        private String[] JsonParser(JSONArray jsonobj) {

            String[] lm = new String[jsonobj.length()];
            try{

                for(int i=0;i<jsonobj.length();i++){
                    String name;
                    JSONObject item = jsonobj.getJSONObject(i);
                    name = item.getString("name");
                    System.out.println("print the name of the places....."+name);
                    lm[i]=name;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return lm;
        }
    }

}


