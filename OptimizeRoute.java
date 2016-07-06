package com.wireless.cse5345.easyvisit;

import org.json.JSONObject;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.os.AsyncTask;
        import android.util.Log;

        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import com.google.gson.JsonElement;

        import org.json.JSONArray;
        import org.json.JSONObject;
        import org.json.JSONStringer;

        import java.util.*;

public class OptimizeRoute extends AppCompatActivity {
    Bundle extras;
    String location1;
    String location2;
    String location3;
    String location4;
    static LocationHelper locationJSON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimizedroute);
        // ArrayList<LocationHelper> locationJSONList = new ArrayList<LocationHelper>();
        // Bundle extra = getIntent().getBundleExtra("locationList");

//        for (String location : locationList) {
//            if(!location.equals("Enter address")) {
//                String[] splitLocation = location.split(",");
//                String tempStreet = splitLocation[0];
//                String tempCity = splitLocation[1];
//                String tempState = splitLocation[2];
//                String tempPostalCode = splitLocation[3];
//                locationJSON = createDummyObject(tempStreet, tempCity, tempState, tempPostalCode);
//                locationJSONList.add(locationJSON);
//               // locationJSON.setPostalCode(tempPostalCode);
//              //  locationJSONList.add(locationJSON);
//              //  locationJSON.setLocationListJson(locationJSONList);
//            }
//        }

        ArrayList<String> locationList = getIntent().getStringArrayListExtra("locationList");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LocationHelper locationJson = new LocationHelper();
        locationJson.setLocationListJson(locationList);
        String locations = gson.toJson(locationJson);
        //  JSONObject je = gson.toJson(locationList);

        Log.d("JSON", gson.toJson(locationJson));

        Call call = new Call();
        call.execute(locations);

    }
}

//    private static LocationHelper createDummyObject(String tempStreet, String tempCity, String tempState, String tempPostalCode) {
//        locationJSON = new LocationHelper();
//        locationJSON.setStreet(tempStreet);
//        locationJSON.setCity(tempCity);
//        locationJSON.setState(tempState);
//        PostalCode postalCode = new PostalCode();
//        postalCode.setPostalCode(tempPostalCode);
//        locationJSON.setPostalCode(postalCode);
//        return locationJSON;
//        }
//
//    }



class Call extends AsyncTask<String, String, JSONArray> {

    JSONArray jsonArr;
    String[] lm;
    String hotelName;
    String url = null;
    String location = null;

    @Override
    protected JSONArray doInBackground(String... params) {
        url = "http://www.mapquestapi.com/directions/v2/optimizedroute?key=74vspPQsSW8iU9QD9F24wlESxh5wgByS";
        //http://terminal2.expedia.com/x/geo/features?ln.op=cn&ln.value=seattle%20center&limit=5&apikey=L2ZB8VtZrd2AKA8ZjvysemHTVJAWIrMC";
        //"http://terminal2.expedia.com/x/geo/features?within=1km&lng=-122.453269&lat=37.777363&type=point_of_interest&apikey=L2ZB8VtZrd2AKA8ZjvysemHTVJAWIrMC";
        jsonArr = RestParser.getResponseForUrl(url, "GET");
        System.out.println("Trying to print........." + params[0]);
        return jsonArr;
    }

    @Override
    protected void onPostExecute(JSONArray result) {

        lm = JsonParser(result);
        //ListView r_listView = (ListView) findViewById(R.id.listView);
        //ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,lm);
        //r_listView.setAdapter(adapter);


    }

    private String[] JsonParser(JSONArray jsonobj) {

        String[] lm = new String[jsonobj.length()];
        try{

            for(int i=0;i<jsonobj.length();i++){
                String name;
                JSONObject item = jsonobj.getJSONObject(i);
                name = item.getString("name");
                System.out.println("print the name of the hotel....."+name);
                lm[i]=name;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lm;
    }

}

