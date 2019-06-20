package com.example.listview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.listview.adapters.EditArticleAdapter;
import com.example.listview.models.Article;
import com.example.listview.models.Measure;
import com.example.listview.models.Shop;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
        cameraBtn = findViewById(R.id.cameraBtn);
        getSupportActionBar().setTitle("Mode Édition");
        editTxtnameList = findViewById(R.id.editTxtnameList);
        Gson gson = new Gson();
        canSave = true;
        manualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditActivity.this, ManualNewArticle.class);
                startActivityForResult(i, 1);
            }
        });
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 200);
        }
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditActivity.this, CameraNewArticle.class);
                startActivityForResult(i, 2);
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
        final Gson gson = new Gson();
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK &&  data!=null) {
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
        }else if (requestCode == 2){
            Log.d("test", "requesttcode2");
            if (resultCode == Activity.RESULT_OK &&  data!=null) {
                Barcode barcode = data.getParcelableExtra("barcode");
                Log.d("barcode", barcode.displayValue);
                RequestQueue queue = Volley.newRequestQueue(this);
                String url = "https://fr.openfoodfacts.org/api/v0/produit/"+barcode.displayValue+".json";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
                                String name = jsonObject.getAsJsonObject("product").get("generic_name_fr").toString();
                                String quantity = jsonObject.getAsJsonObject("product").get("quantity").toString();
                                String measure = Measure.parseMeasureAPI(quantity);
                                int product_quantity = Integer.valueOf(jsonObject.getAsJsonObject("product").get("product_quantity").toString());
                                Log.d("Response is: ", name);
                                Log.d("Response is: ", measure);
                                Log.d("Response is: ", product_quantity+"");
                                currentShop.getArticleList().add(new Article(name, measure, product_quantity));
                                adapter.notifyDataSetChanged();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR ", "That didn't work !");
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);

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
