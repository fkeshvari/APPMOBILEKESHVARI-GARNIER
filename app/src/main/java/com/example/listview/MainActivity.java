package com.example.listview;

import android.graphics.Paint;
import android.os.Bundle;
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
    EditText nameListTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ma liste de courses");

        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.ListView);
        articleList = new ArrayList<>();
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
        adapter = new ArticleAdapter(articleList, getApplicationContext());
        listView.setAdapter(adapter);
        nameListTxt = (EditText) findViewById(R.id.nameListTxt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {/*
                ListView lv = (ListView) parent;

                TextView row = (TextView)lv.getItemAtPosition(position);
                row.setPaintFlags(row.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);*/
                Snackbar.make(view, "Test  " +position, Snackbar.LENGTH_LONG).show();
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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
