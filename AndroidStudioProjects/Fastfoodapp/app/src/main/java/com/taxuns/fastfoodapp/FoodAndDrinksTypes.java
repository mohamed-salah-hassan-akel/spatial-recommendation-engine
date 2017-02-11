package com.taxuns.fastfoodapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class FoodAndDrinksTypes extends AppCompatActivity {
    private ListView foodAndDrinkList;
    private Intent foodAndDrinkInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_drinks_types);
        FoodDrink foodDrink = (FoodDrink) new FoodDrink().execute();

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
                 final ArrayList<String> strOfFood = new ArrayList<>();

                JSONArray foodAndDrinkCat = new JSONArray(s);
                JSONObject foodJson = new JSONObject(String.valueOf(foodAndDrinkCat.getJSONObject(0)));
                JSONArray foodAndDrinks = new JSONArray(foodJson.getString("food_types"));
                for(int i=0; i < foodAndDrinks.length(); i++){

                        strOfFood.add(foodAndDrinks.getString(i));
                }

                    foodAndDrinkList = (ListView)findViewById(R.id.food_drinks_list);
                    FoodOthersAdapter foodOthersAdapter = new FoodOthersAdapter
                           (getApplicationContext(),R.layout.food_drink_others_layout,strOfFood);
                    foodAndDrinkList.setAdapter(foodOthersAdapter);
                foodAndDrinkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = foodAndDrinkList.getItemAtPosition(position).toString();
                        for(int i =0; i< strOfFood.size(); i++){
                            if(item.contentEquals(strOfFood.get(i))){
                                foodAndDrinkInt = new Intent(getApplicationContext(),FoodSelection.class);
                                foodAndDrinkInt.putExtra("food_type",item);
                                startActivity(foodAndDrinkInt);
                            }
                        }




                        }

                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
