package com.example.shoppinglist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DrinkModel extends ViewModel {
    private MutableLiveData<List<Drink>> drinks;
    public LiveData<List<Drink>> getDrinks() {
        if (drinks == null) {
            drinks = new MutableLiveData<List<Drink>>();
            loadDrinks();
        }
        return drinks;
    }

    private void loadDrinks() {

    }
}