package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ItemsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
    }

    public void onClickItem(View view) {
        Button b = (Button)view;
        launchMainActivityAddItem(b.getText().toString());
    }

    public void launchMainActivityAddItem(String itemName) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("itemName", itemName);
        startActivity(intent);
    }

}