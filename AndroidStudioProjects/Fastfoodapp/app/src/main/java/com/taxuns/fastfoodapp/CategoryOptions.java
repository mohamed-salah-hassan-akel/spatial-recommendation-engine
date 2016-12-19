package com.taxuns.fastfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategoryOptions extends AppCompatActivity implements View.OnClickListener{
    private ImageView foodImgV, drinkImgV;
    private Intent foodAndDrinkIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_options);
        foodImgV = (ImageView)findViewById(R.id.food);
        drinkImgV = (ImageView)findViewById(R.id.drinks);
        foodImgV.setOnClickListener(this);
        drinkImgV.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.food:
                foodAndDrinkIntent = new Intent(this,FoodAndDrinksTypes.class);
                foodAndDrinkIntent.putExtra("food","food_category");
                break;
            case R.id.drinks:
                foodAndDrinkIntent = new Intent(this,FoodAndDrinksTypes.class);
                foodAndDrinkIntent.putExtra("drink","drink_category");
                break;
        }

    }
}
