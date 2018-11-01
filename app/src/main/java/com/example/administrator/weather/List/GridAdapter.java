package com.example.administrator.weather.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.weather.R;

import java.util.ArrayList;
import java.util.List;


public class GridAdapter extends ArrayAdapter<GridItem> {

    private  int resource;
    private Context context;
    private LayoutInflater inflater;

    private ArrayList<GridItem> gridItems = new ArrayList<GridItem>();

    //创建adapter
    public GridAdapter(Context context, int resource, ArrayList<GridItem> gridItems) {
        super(context,resource,gridItems);

        this.resource = resource;
        this.context = context;
        this.gridItems = gridItems;
        //inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView==null ) {
            inflater = LayoutInflater.from(context);
            itemView = inflater.inflate(resource, parent, false);
        }

        TextView itemTitle = itemView.findViewById( R.id.ItemTitle );
        TextView itemText = itemView.findViewById( R.id.ItemText );
        ImageView cond_code_d = (ImageView) itemView.findViewById( R.id.cond_code_d );

        itemTitle.setText( gridItems.get( position ).getTitle() );
        itemText.setText( gridItems.get( position ).getText() );

        String imageUrl = gridItems.get( position ).getImageUrl();
        Glide
                .with(context)
                .load(imageUrl)
                .into(cond_code_d);

        return itemView;
    }



}
