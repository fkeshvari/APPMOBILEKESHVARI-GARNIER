package com.example.listview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class ShowShops extends AppCompatActivity {

    ShopAdapter shopAdapter;
    ArrayList<Shop> shopList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shops);
        getSupportActionBar().setTitle("Toutes mes listes de courses");
        listView = findViewById(R.id.listView);
        if (getIntent().getSerializableExtra("shopList") instanceof ArrayList){
            shopList = (ArrayList<Shop>) getIntent().getSerializableExtra("shopList");
        }
        Log.d("test", shopList.toString());
        if(shopList!=null){
            shopAdapter = new ShopAdapter(shopList, getApplicationContext());
            listView.setAdapter(shopAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(ShowShops.this, MainActivity.class);
                    i.putExtra("currentShop",shopList.get(position));
                    i.putExtra("position", position);
                    setResult(Activity.RESULT_OK, i);
                    finish();
                }
            });
        }
    }
}
