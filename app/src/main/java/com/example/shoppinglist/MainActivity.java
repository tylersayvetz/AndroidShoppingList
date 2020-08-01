package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        LinearLayout layout = findViewById(R.id.linearLayout);

        ViewModelProvider modelProvider = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory());
        DrinkModel model = modelProvider.get(DrinkModel.class);

        if (intent != null) {
            String newItem = intent.getStringExtra("itemName");
            if ( newItem != null) {
                TextView newView = new TextView(this);
                newView.setText(newItem);

                layout.addView(newView);
            }
        }

        model.getDrinks().observe(this, new Observer<List<Drink>>() {
            @Override
            public void onChanged(List<Drink> drinks) {
                Log.d("IN", "got here");
                LinearLayout layout = findViewById(R.id.linearLayout);
                if (drinks != null) {
                    for (int i = 0; i < drinks.size(); i++) {
                        TextView tv = new TextView(MainActivity.this);
                        Drink drink = drinks.get(i);
                        tv.setText(drink.name);

                        layout.addView(tv);
                    }
                }
            }
        });



        //if this is not the first start of the app.
//        if (savedInstanceState != null) {
//            String name = savedInstanceState.getString("name");
//            String[] prevItems = savedInstanceState.getStringArray("listItems");
//            if (prevItems == null) Log.d("FAILURE", "prevItems is null");
//            if ( prevItems != null) {
//                Log.d("MAIN_ADDER", "adding..");
//                for (int i = 0; i < prevItems.length; i++) {
//                    Log.d("MAIN_ADDER", prevItems[i]);
//                    addNewListItem(layout, prevItems[i]);
//                }
//            }
//        }
    }

    private void addNewListItem(LinearLayout layout, String itemName) {
        TextView textView = new TextView(this);
        textView.setText(itemName);
        layout.addView(textView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        LinearLayout container = findViewById(R.id.linearLayout);
        int numChildren = container.getChildCount();

        if (numChildren != 0) {
            String[] items = new String[numChildren];

            for (int i = 0; i < numChildren; i++ ) {
                TextView v = (TextView) container.getChildAt(i);
                items[i] = v.getText().toString();
                Log.d("MAIN_ADDING_TO_INSTANCE", v.getText().toString());
            }

            outState.putStringArray("listItems", items);
            outState.putString("name", "Tyler");
        }
    }

    public void addItemToList(View view) {
        Intent intent = new Intent(this, ItemsList.class);
        startActivity(intent);
    }
}