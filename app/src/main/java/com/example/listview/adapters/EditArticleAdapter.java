package com.example.listview.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.listview.R;
import com.example.listview.models.Article;
import com.example.listview.models.Measure;
import com.example.listview.models.Shop;

import java.util.ArrayList;

public class EditArticleAdapter extends ArrayAdapter<Article>{


    private ArrayList<Article> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        EditText editTxtQte;
        TextView txtMeasure;
        EditText editTxtName;
        ImageButton deleteBtn;
    }

    public EditArticleAdapter(ArrayList<Article> data, Context context) {
        super(context, R.layout.edit_article_item, data);
        this.dataSet = data;
        this.mContext = context;

    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Article article = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.edit_article_item, parent, false);
            viewHolder.editTxtQte = convertView.findViewById(R.id.editTxtQte);
            viewHolder.txtMeasure = convertView.findViewById(R.id.txtMeasure);
            viewHolder.editTxtName = convertView.findViewById(R.id.editTxtName);
            viewHolder.deleteBtn = convertView.findViewById(R.id.deleteBtn);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        Log.d("showSA",""+position);
        if(position == 0){
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.up_from_bottom_low);
            //result.startAnimation(animation);
        }else{
            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition ? R.anim.up_from_bottom : R.anim.down_from_top));
            result.startAnimation(animation);
        }

        viewHolder.editTxtQte.setText(String.valueOf(article.getQte()));
        viewHolder.editTxtQte.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()) {
                    article.setQte(Integer.valueOf(s.toString()));
                    Log.d("Test", String.valueOf(article.getQte()));
                }
            }
        });
        viewHolder.txtMeasure.setText(Measure.getString(article.getMeasure()));
        viewHolder.editTxtName.setText(article.getName());
        viewHolder.editTxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()) {
                    article.setName(s.toString());
                    Log.d("Test", article.getName());
                }
            }
        });
        viewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet.remove(article);
                notifyDataSetChanged();
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }

}
