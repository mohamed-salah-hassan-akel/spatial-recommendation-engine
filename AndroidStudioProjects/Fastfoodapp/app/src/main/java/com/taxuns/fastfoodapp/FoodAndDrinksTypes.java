package com.taxuns.fastfoodapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

public class FoodAndDrinksTypes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_drinks_types);
    }


    private class FoodDrink extends AsyncTask<Void,Void,String>{
        String url;
        String JSON_String;

        @Override
        protected void onPreExecute() {
             url ="http://10.0.3.2:8081/food_types";
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
                ArrayList<String> strOfDrinks,strOfFood = new ArrayList<>();
                strOfDrinks = new ArrayList<>();
                JSONArray foodAndDrinkCat = new JSONArray(s);
                JSONObject foodJson = new JSONObject(String.valueOf(foodAndDrinkCat.getJSONObject(0)));
                JSONArray foodAndDrinks = new JSONArray(foodJson.getString("food_types"));
                for(int i=0; i < foodAndDrinks.length(); i++){
                    if(foodAndDrinks.getString(i)!="مشروبات"){
                        strOfFood.add(foodAndDrinks.getString(i));

                    }
                    else{
                        strOfDrinks.add(foodAndDrinks.getString(i));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
