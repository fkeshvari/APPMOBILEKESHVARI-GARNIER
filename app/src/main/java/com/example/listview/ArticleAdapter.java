package com.example.listview;

import android.content.Context;
import android.graphics.Paint;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleAdapter extends ArrayAdapter<Article> implements View.OnClickListener {


    private ArrayList<Article> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtQte;
        ImageView validateImg;
    }

    public ArticleAdapter(ArrayList<Article> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Article article = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtQte = (TextView) convertView.findViewById(R.id.txtQte);
            viewHolder.validateImg = (ImageView) convertView.findViewById(R.id.validateImg);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        viewHolder.txtName.setText(article.getName());
        viewHolder.txtQte.setText(String.valueOf(article.getQte())+" ");

        if(article.isChecked()){
            strikeArticle(viewHolder, true);
        }else{
            strikeArticle(viewHolder, false);
        }
        viewHolder.validateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Article article = getItem(position);
                if(!article.isChecked()) {
                    strikeArticle(viewHolder, true);
                    article.setChecked(true);
                    dataSet.set(position, article);
                }else{
                    strikeArticle(viewHolder, false);
                    article.setChecked(false);
                    dataSet.set(position, article);
                }
                Snackbar.make(v, "Nom de l'article " + article.getName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
        viewHolder.validateImg.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

    void strikeArticle(ViewHolder viewHolder, boolean strike){
        if(strike){
            viewHolder.txtName.setPaintFlags(viewHolder.txtName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.txtQte.setPaintFlags(viewHolder.txtQte.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            viewHolder.txtName.setPaintFlags(viewHolder.txtName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            viewHolder.txtQte.setPaintFlags(viewHolder.txtQte.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }
}