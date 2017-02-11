package com.taxuns.fastfoodapp;


import java.util.ArrayList;

public class MealAndPrice {
    ArrayList<String> names,prices;



    public MealAndPrice(ArrayList<String> names, ArrayList<String> prices){
        names = new ArrayList<>();
        prices = new ArrayList<>();
        this.names = names;
        this.prices = prices;

    }
    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getPrices() {
        return prices;
    }

}
