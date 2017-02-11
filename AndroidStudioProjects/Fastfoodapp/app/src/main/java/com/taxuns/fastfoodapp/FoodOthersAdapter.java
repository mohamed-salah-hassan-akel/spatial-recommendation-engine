package com.taxuns.fastfoodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by salah-lab
 */

public class FoodOthersAdapter extends ArrayAdapter<String>{
    private int resourceId;
    private ArrayList<String> data ;
    private LayoutInflater inflater;
    public FoodOthersAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        data = objects;
        this.resourceId = resource;

        inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.food_drink_others_layout,null);
        }
        TextView rowText = (TextView)convertView.findViewById(R.id.food_others_text_row_list_view);
        rowText.setText(data.get(position));
        return convertView;
    }
}
