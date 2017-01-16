package com.imperium.autobeacon;

/**
 * Created by sowmya on 7/15/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItems;

    CustomAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView restriction_image;
        TextView restriction_name;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.restriction_model, null);
            holder = new ViewHolder();

            holder.restriction_name = (TextView) convertView
                    .findViewById(R.id.restriction_name);
            holder.restriction_image = (ImageView) convertView
                    .findViewById(R.id.restriction_image);

            RowItem row_pos = rowItems.get(position);

            holder.restriction_image.setImageResource(row_pos.getRestriction_image());
            holder.restriction_name.setText(row_pos.getRestriction_name());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}