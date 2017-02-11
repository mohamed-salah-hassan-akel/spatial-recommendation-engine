package com.taxuns.fastfoodapp;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity{
    private ListView resList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResturantAsync task= (ResturantAsync) new ResturantAsync().execute();




    }
    private class ResturantAsync extends AsyncTask<Void,Void,String>{
        String url;
        String JSON_String;
        @Override
        protected void onPreExecute() {
            url ="http://10.0.3.2:8081/resturant";

        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL restUrl = new URL(url);
                HttpURLConnection urlConnection =(HttpURLConnection)restUrl.openConnection();
                InputStream instream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                StringBuilder stringBuilderJson = new StringBuilder();

                while ((JSON_String=reader.readLine())!=null){
                    stringBuilderJson.append(JSON_String+"\n");
                }
                reader.close();
                instream.close();
                urlConnection.disconnect();
                return stringBuilderJson.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray restArray = new JSONArray(s);
                final JSONObject json = new JSONObject(String.valueOf(restArray.getJSONObject(0)));
                ArrayList<String> restListArr = new ArrayList<>();
                final String restaurantName =json.getString("rest_name");
                restListArr.add(restaurantName);
                resList = (ListView)findViewById(R.id.restaurant_list);
                RestaurantListAdapter adapter = new RestaurantListAdapter(getApplicationContext(),
                        R.layout.restaurant_list_view_layout,restListArr);

                resList.setAdapter(adapter);
                resList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent restIntent = new Intent(getApplicationContext(),BranchesActivity.class);
                        restIntent.putExtra("rest_name",restaurantName);
                        startActivity(restIntent);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
