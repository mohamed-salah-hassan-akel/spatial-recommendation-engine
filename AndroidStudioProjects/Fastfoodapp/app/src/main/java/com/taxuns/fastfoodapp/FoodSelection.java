package com.taxuns.fastfoodapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
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
import java.util.HashMap;
import java.util.Map;

public class FoodSelection extends AppCompatActivity {
    private ListView foodListViewSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);
        FoodSelectionAsync foodAsync  = (FoodSelectionAsync) new FoodSelectionAsync().execute();
    }

    private class FoodSelectionAsync extends AsyncTask<Void, Void, String> {
        String url;
        String jsonMealString;

        @Override
        protected void onPreExecute() {
            url = "http://10.0.3.2:8081/food_category";
        }


        @Override
        protected String doInBackground(Void... params) {
            try {
                URL urlJson = new URL(url);
                HttpURLConnection urlConnJson = (HttpURLConnection) urlJson.openConnection();
                InputStream instream = urlConnJson.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                StringBuilder dataString = new StringBuilder();
                while ((jsonMealString = reader.readLine()) != null) {
                    dataString.append(jsonMealString + "\n");
                }
                reader.close();
                instream.close();
                urlConnJson.disconnect();
                return dataString.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONArray foodMenu = new JSONArray(s);
                JSONObject food = new JSONObject(String.valueOf(foodMenu.getJSONObject(0)));
                JSONArray foodMenu2 = new JSONArray(food.getString("food_menu"));
                JSONObject food2 = new JSONObject(String.valueOf(foodMenu2.getJSONObject(0)));
                JSONArray foodVarities = new JSONArray(food2.getString("varities"));
                ArrayList<JSONObject> foodVaritiesJsonArr = new ArrayList<>();
                for(int i=0; i<foodVarities.length(); i++){
                    foodVaritiesJsonArr.add(foodVarities.getJSONObject(i));
                }

                ArrayList<String> names,prices;
                names = new ArrayList<>();
                prices = new ArrayList<>();
                names.add(foodVaritiesJsonArr.get(0).getString("v1"));
                prices.add(foodVaritiesJsonArr.get(0).getString("price"));
                names.add(foodVaritiesJsonArr.get(1).getString("v2"));
                prices.add(foodVaritiesJsonArr.get(1).getString("price"));
                names.add(foodVaritiesJsonArr.get(2).getString("v3"));
                prices.add(foodVaritiesJsonArr.get(2).getString("price"));
                names.add(foodVaritiesJsonArr.get(3).getString("v4"));
                prices.add(foodVaritiesJsonArr.get(3).getString("price"));
                names.add(foodVaritiesJsonArr.get(4).getString("v5"));
                prices.add(foodVaritiesJsonArr.get(4).getString("price"));
                names.add(foodVaritiesJsonArr.get(5).getString("v6"));
                prices.add(foodVaritiesJsonArr.get(5).getString("price"));
                names.add(foodVaritiesJsonArr.get(6).getString("v7"));
                prices.add(foodVaritiesJsonArr.get(6).getString("price"));
                names.add(foodVaritiesJsonArr.get(7).getString("v8"));
                prices.add(foodVaritiesJsonArr.get(7).getString("price"));
                MealAndPrice mealPrice = new MealAndPrice(names,prices);
                ArrayList<MealAndPrice> mealAndPrices = new ArrayList<>();
                Intent foodCatInt = getIntent();
                String foodType =foodCatInt.getStringExtra("food_type");
                foodListViewSort = (ListView)findViewById(R.id.food_item_list);
                MealsAndPriceAdapter adapter ;

                if (foodType.contentEquals("اصناف متنوعه")) {
                    adapter = new MealsAndPriceAdapter(getApplicationContext(), R.layout.
                            selected_food_layout, mealAndPrices);
                    foodListViewSort.setAdapter(adapter);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

                /*





                }*/


        }
    }
}

