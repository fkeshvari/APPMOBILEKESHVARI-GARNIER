package com.example.listview;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopAdapter extends ArrayAdapter<Shop>{


    private ArrayList<Shop> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        boolean isSelected;
    }

    public ShopAdapter(ArrayList<Shop> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;

    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Shop shop = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.shop_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);

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

        viewHolder.txtName.setText(shop.getName());
        // Return the completed view to render on screen
        return convertView;
    }

}
