package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.listview.adapters.ArticleAdapter;
import com.example.listview.models.Article;
import com.example.listview.models.Shop;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArticleAdapter adapter;
    ArrayList<Article> articleList;
    ArrayList<Article> articleList2;
    ArrayList<Shop> shopList;
    Button nameListBtn;
    Shop currentShop;
    SharedPreferences sP;
    SharedPreferences.Editor sPEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ma liste de courses");
        toolbar.setLogo(R.drawable.baseline_shopping_cart_white_24);
        sP = getSharedPreferences("SP", Context.MODE_PRIVATE);

        setSupportActionBar(toolbar);

        listView =  findViewById(R.id.ListView);
        nameListBtn = findViewById(R.id.addBtn);

        Gson gson = new Gson();
        String json = sP.getString("shopList", "");
        if (json != null && !json.equals("")) {
            Type type = new TypeToken<ArrayList<Shop>>() {
            }.getType();
            shopList = gson.fromJson(json, type);
            currentShop = shopList.get(0);
        } else {
            shopList = new ArrayList<Shop>();
            articleList = new ArrayList<>();
            articleList2 = new ArrayList<>();
            articleList.add(new Article("Banane"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Fraise"));
            articleList.add(new Article("Ananas"));
            articleList2.add(new Article("Avion"));
            shopList.add(new Shop(nameListBtn.getText().toString(), articleList));
            shopList.add(new Shop("Lowl", articleList2));
            currentShop = shopList.get(0);
        }

        adapter = new ArticleAdapter(currentShop.getArticleList(), getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Test", currentShop.getArticleList().get(position).getName());
                //Snackbar.make(view, "Test  " + position, Snackbar.LENGTH_LONG).show();
                Article a = currentShop.getArticleList().get(position);
                if (!a.isChecked())
                    a.setChecked(true);
                else
                    a.setChecked(false);
                adapter.notifyDataSetChanged();

            }
        });

        nameListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShopListActivity.class);
                i.putExtra("shopList", shopList);
                i.putExtra("position", shopList.indexOf(currentShop));
                startActivityForResult(i, 1);
            }
        });

        FloatingActionButton editFAB = findViewById(R.id.editFAB);
        editFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Show other lists", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                i.putExtra("currentShop", currentShop);
                i.putExtra("position", shopList.indexOf(currentShop));
                startActivityForResult(i, 2);
            }
        });

        FloatingActionButton toPDFFAB = findViewById(R.id.toPDFFAB);
        toPDFFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Share as PDF", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Shop shop = (Shop) data.getSerializableExtra("currentShop");
                int position = data.getIntExtra("position", -1);
                adapter = new ArticleAdapter(shop.getArticleList(), getApplicationContext());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                currentShop = shop;
                shopList.set(position, shop);
                nameListBtn.setText(shop.getName());

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.d("Test", "RESUSLT_CANCELED");
            }
        }else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Shop shop = (Shop) data.getSerializableExtra("currentShop");
                int position = data.getIntExtra("position", -1);
                adapter = new ArticleAdapter(shop.getArticleList(), getApplicationContext());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                currentShop = shop;
                shopList.set(position, shop);
                nameListBtn.setText(shop.getName());

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.d("Test", "RESUSLT_CANCELED");
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Test", "onPause");
        if (sP == null)
            sP = getApplicationContext().getSharedPreferences("SP", Context.MODE_PRIVATE);
        sPEditor = sP.edit();
        Gson gson = new Gson();
        String json = gson.toJson(shopList);
        sPEditor.putString("shopList", json);
        sPEditor.apply();
        Log.d("Test", "saved");
    }
}
