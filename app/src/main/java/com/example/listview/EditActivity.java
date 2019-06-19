package com.example.listview;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.listview.adapters.EditArticleAdapter;
import com.example.listview.models.Article;
import com.example.listview.models.Shop;

public class EditActivity extends AppCompatActivity {

    ListView listView;
    EditArticleAdapter adapter;
    Button manualBtn;
    Button cameraBtn;
    Shop currentShop;
    EditText editTxtnameList;
    int position;
    boolean canSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        listView = findViewById(R.id.listView);
        manualBtn = findViewById(R.id.manualBtn);
        getSupportActionBar().setTitle("Mode Édition");
        editTxtnameList = findViewById(R.id.editTxtnameList);
        canSave = true;
        manualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditActivity.this, ManualNewArticle.class);
                startActivityForResult(i, 1);
            }
        });
        currentShop = (Shop) getIntent().getSerializableExtra("currentShop");
        position = getIntent().getIntExtra("position", -1);
        editTxtnameList.setText(currentShop.getName());
        adapter = new EditArticleAdapter(currentShop.getArticleList(), getApplicationContext());
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Article article = (Article) data.getSerializableExtra("newArticle");
                if(article!=null){
                    currentShop.getArticleList().add(article);
                    adapter.notifyDataSetChanged();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.d("Test", "RESULT_CANCELED_EDIT");
            }
        }
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
        if (id == R.id.action_save && canSave) {
            canSave = false;
            adapter.notifyDataSetChanged();
            Intent i = new Intent(EditActivity.this, MainActivity.class);
            currentShop.setName(editTxtnameList.getText().toString());
            i.putExtra("currentShop",currentShop);
            i.putExtra("position", position);
            setResult(Activity.RESULT_OK, i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
