package com.example.listview.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listview.R;
import com.example.listview.models.Article;

public class ArticleAdapter extends ArrayAdapter<Article>{


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
        Log.d("show",""+position);
        if(position == 0){
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.up_from_bottom_low);
            //result.startAnimation(animation);
        }else{
            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition ? R.anim.up_from_bottom : R.anim.down_from_top));
            result.startAnimation(animation);
        }

        lastPosition = position;
        viewHolder.txtName.setText(article.getName());
        viewHolder.txtQte.setText(String.valueOf(article.getQte())+" ");

        if(article.isChecked()){
            strikeArticle(viewHolder, true);
        }else{
            strikeArticle(viewHolder, false);
        }
        viewHolder.validateImg.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
    protected void removeAnimation(View rowView) {
        // TODO Auto-generated method stub

        final Animation animation = AnimationUtils.loadAnimation(rowView.getContext(), R.anim.splashfadeout);
        rowView.startAnimation(animation);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.cancel();
            }
        }, 1000);
    }

    private void strikeArticle(ViewHolder viewHolder, boolean strike){
        if(strike){
            viewHolder.txtName.setPaintFlags(viewHolder.txtName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.txtQte.setPaintFlags(viewHolder.txtQte.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.validateImg.setImageResource(R.drawable.baseline_undo_black_24);
        }else{
            viewHolder.txtName.setPaintFlags(viewHolder.txtName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            viewHolder.txtQte.setPaintFlags(viewHolder.txtQte.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            viewHolder.validateImg.setImageResource(R.drawable.baseline_done_black_24);
        }
    }
}
