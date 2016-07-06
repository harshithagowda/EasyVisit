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
public class Visit_Restaurants extends AppCompatActivity {

    String url = null;
    String location = null;
    Bundle extras;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_view);

        extras = getIntent().getExtras();
        if (extras != null) {
            location = extras.getString("location");
            location = location.replaceAll(" ", "%20");

        }
        Call restaurants = new Call();
        restaurants.execute(location);


    }


    class Call extends AsyncTask<String, String, JSONArray> {

        JSONArray jsonA;
        String[] arr;
        String Name=null;


        @Override
        protected JSONArray doInBackground(String... params) {
            url = "http://terminal2.expedia.com/x/geo/features?ln.op=cn&ln.value="+location+"&limit=10&apikey=L2ZB8VtZrd2AKA8ZjvysemHTVJAWIrMC";
            //http://terminal2.expedia.com/x/geo/features?ln.op=cn&ln.value=seattle%20center&limit=5&apikey=L2ZB8VtZrd2AKA8ZjvysemHTVJAWIrMC";
            //"http://terminal2.expedia.com/x/geo/features?within=1km&lng=-122.453269&lat=37.777363&type=point_of_interest&apikey=L2ZB8VtZrd2AKA8ZjvysemHTVJAWIrMC";
            jsonA = RestParser.getResponseForUrl(url, "GET");
            System.out.println("Trying to print........." + params[0]);
            return jsonA;
        }

        @Override
        protected void onPostExecute(final JSONArray result) {

            arr = JsonParser(result);
            ListView r_listView = (ListView) findViewById(R.id.listView);
            r_listView.setClickable(true);
            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
            r_listView.setAdapter(adapter);
            r_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("Inside onclick");
                    Name = arr[position];
                    System.out.println("trying to send the name of the restaurant"+Name);

                    try{

                        for(int j=0;j<result.length();j++){
                            String coordinates;
                            String name;
                            JSONObject value = result.getJSONObject(j);
                            name = value.getString("name");
                            if(name.equals(arr[position])){

                                JSONObject pos = value.getJSONObject("position");
                                coordinates = pos.getString("coordinates");
                                System.out.println("the restaurant coordinates are :"+coordinates);

                                Intent navigate = new Intent(Visit_Restaurants.this,MapsActivity.class);
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

            String[] localcopy = new String[jsonobj.length()];
            try{

                for(int i=0;i<jsonobj.length();i++){
                    String name;
                    JSONObject item = jsonobj.getJSONObject(i);
                    name = item.getString("name");
                    System.out.println("print the name of the restaurants....."+name);
                    localcopy[i]=name;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return localcopy;
        }
    }

}


