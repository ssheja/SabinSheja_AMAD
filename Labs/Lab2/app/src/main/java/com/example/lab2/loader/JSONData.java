package com.example.lab2.loader;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2.model.Bilionaire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONData {
    public static List<Bilionaire> billionaires;
    private static String api_URL=" https://forbes400.herokuapp.com/api/forbes400?limit=10";

    static {
        billionaires = new ArrayList<>();
    }


    public static void getJSON(Context context){
        String url2 = api_URL;

        // create Volley request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage(), error);
            }
        });
        queue.add(stringRequest);
    }

    private static List<Bilionaire> parseJSON(String response){
        if (response != null) {
            try {
                //create JSONArray
                JSONArray resultsArray = new JSONArray(response);


                for (int i =0; i < resultsArray.length(); i++) {
                    JSONObject charObject = resultsArray.getJSONObject(i);

                    //get values
                    int rank = charObject.getInt("rank");
                    String names = charObject.getString("uri");
                    String netWorth = Integer.toString(charObject.getInt("finalWorth"));
                    String country = charObject.getString("countryOfCitizenship");

                    Bilionaire bilionaire = new Bilionaire(rank, names, country, netWorth);
                    billionaires.add(bilionaire);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return billionaires;
    }
}
