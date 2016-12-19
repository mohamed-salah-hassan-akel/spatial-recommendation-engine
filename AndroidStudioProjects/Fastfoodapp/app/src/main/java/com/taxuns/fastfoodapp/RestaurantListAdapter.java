package com.taxuns.fastfoodapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class RestaurantListAdapter extends ArrayAdapter<String> {
    private ArrayList<String> data;
    private int resource;
    private LayoutInflater inflater;

    public RestaurantListAdapter(Context context, int resource, ArrayList<String>list) {
        super(context, resource, list);
        data = list;
        this.resource = resource;
        inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.restaurant_list_view_layout,null);
        }
        ImageView restLogo;
        TextView restaurantName;
        restLogo = (ImageView)convertView.findViewById(R.id.rest_icon_row_list_view);
        restaurantName = (TextView)convertView.findViewById(R.id.rest_text_row_list_view);
        restaurantName.setText(data.get(position));
        restLogo.setImageResource(R.drawable.elshbrawy_logo);

        return convertView;
    }
}
