package com.taxuns.fastfoodapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MealsAndPriceAdapter extends ArrayAdapter<MealAndPrice> {
    private int resource;
    private ArrayList<MealAndPrice> data;
    private LayoutInflater inflater;
    public MealsAndPriceAdapter(Context context, int resource, ArrayList<MealAndPrice> objects) {
        super(context, resource, objects);
        data = objects;

        this.resource = resource;
        inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.selected_food_layout,null);
        TextView mealName, mealPrice;
        mealName = (TextView)convertView.findViewById(R.id.meal_name);
        mealPrice = (TextView)convertView.findViewById(R.id.meal_price);
        mealName.setText(data.get(position).getNames().toString());
        mealPrice.setText(data.get(position).getPrices().toString());

        return convertView;

    }
}
