package com.example.listview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.listview.adapters.EditArticleAdapter;
import com.example.listview.models.Shop;

public class EditActivity extends AppCompatActivity {

    ListView listView;
    EditArticleAdapter adapter;
    Button manualBtn;
    Button cameraBtn;
    Shop currentShop;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        listView = findViewById(R.id.listView);
        manualBtn = findViewById(R.id.manualBtn);
        getSupportActionBar().setTitle("Mode Édition");
        manualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        currentShop = (Shop) getIntent().getSerializableExtra("currentShop");
        position = getIntent().getIntExtra("position", -1);
        adapter = new EditArticleAdapter(currentShop.getArticleList(), getApplicationContext());
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            adapter.notifyDataSetChanged();
            Intent i = new Intent(EditActivity.this, MainActivity.class);
            i.putExtra("currentShop",currentShop);
            i.putExtra("position", position);
            setResult(Activity.RESULT_OK, i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
