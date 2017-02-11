package com.taxuns.fastfoodapp;


import android.widget.Button;
import android.widget.TextView;

public class CartItem {
    private TextView mealNameItem, priceMealItem, mealQuntityItem;
    private Button increaseQuntityBtn, decreaseQuntityBtn, deleteItemCartButton;

    public TextView getMealNameItem() {
        return mealNameItem;
    }

    public void setMealNameItem(TextView mealNameItem) {
        this.mealNameItem = mealNameItem;
    }

    public TextView getPriceMealItem() {
        return priceMealItem;
    }

    public void setPriceMealItem(TextView priceMealItem) {
        this.priceMealItem = priceMealItem;
    }

    public TextView getMealQuntityItem() {
        return mealQuntityItem;
    }

    public void setMealQuntityItem(TextView mealQuntityItem) {
        this.mealQuntityItem = mealQuntityItem;
    }

    public Button getIncreaseQuntityBtn() {
        return increaseQuntityBtn;
    }

    public void setIncreaseQuntityBtn(Button increaseQuntityBtn) {
        this.increaseQuntityBtn = increaseQuntityBtn;
    }

    public Button getDecreaseQuntityBtn() {
        return decreaseQuntityBtn;
    }

    public void setDecreaseQuntityBtn(Button decreaseQuntityBtn) {
        this.decreaseQuntityBtn = decreaseQuntityBtn;
    }

    public Button getDeleteItemCartButton() {
        return deleteItemCartButton;
    }

    public void setDeleteItemCartButton(Button deleteItemCartButton) {
        this.deleteItemCartButton = deleteItemCartButton;
    }
}
