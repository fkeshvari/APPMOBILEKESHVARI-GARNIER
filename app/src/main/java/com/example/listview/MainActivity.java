package com.example.listview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArticleAdapter adapter;
    ArrayList<Article> articleList;
    ArrayList<Article> articleList2;
    ArrayList<Shop> shopList;
    EditText nameListTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ma liste de courses");

        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.ListView);
        articleList = new ArrayList<>();
        articleList2 = new ArrayList<>();
        articleList.add(new Article("Banane"));
        articleList.add(new Article("Fraise"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList.add(new Article("Ananas"));
        articleList2.add(new Article("Avion"));
        shopList = new ArrayList<Shop>();
        nameListTxt = (EditText) findViewById(R.id.nameListTxt);

        shopList.add(new Shop(nameListTxt.getText().toString(), articleList));
        shopList.add(new Shop("Lowl", articleList2));
        adapter = new ArticleAdapter(articleList, getApplicationContext());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {/*
                ListView lv = (ListView) parent;

                TextView row = (TextView)lv.getItemAtPosition(position);
                row.setPaintFlags(row.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);*/
                Snackbar.make(view, "Test  " + position, Snackbar.LENGTH_LONG).show();

            }
        });

/*     editText = (EditText) findViewById(R.id.edittxt);
     Button btnAdd = (Button) findViewById(R.id.additems);
     btnAdd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String additem = editText.getText().toString();
             arrayList.add(additem);
             adapter.notifyDataSetChanged();
             Toast.makeText(getBaseContext(), "Item added in list", Toast.LENGTH_SHORT).show();

         }
     });*/


        FloatingActionButton otherListFAB = findViewById(R.id.otherListFAB);
        otherListFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ShowShops.class);
                i.putExtra("shopList", shopList);
                startActivityForResult(i, 1);
            }
        });

        FloatingActionButton editFAB = findViewById(R.id.editFAB);
        editFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Show other lists", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        FloatingActionButton toPDFFAB = findViewById(R.id.toPDFFAB);
        toPDFFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Share as PDF", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Shop shop = (Shop) data.getSerializableExtra("currentShop");
                ArticleAdapter articleAdapter = new ArticleAdapter(shop.getArticleList(), getApplicationContext());
                listView.setAdapter(articleAdapter);
                nameListTxt.setText(shop.getName());

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
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
}
