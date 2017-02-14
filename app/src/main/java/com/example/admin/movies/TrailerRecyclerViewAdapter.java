package com.example.admin.movies;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by admin on 01-02-2017.
 */

public class TrailerRecyclerViewAdapter extends RecyclerView.Adapter<TrailerRecyclerViewAdapter.ViewHolder> {


    private List<MovieTrailer> data;
    private Context context;
    private static final String  preImgUrl = "http://img.youtube.com/vi/";


    public TrailerRecyclerViewAdapter(List<MovieTrailer> list, Context context) {
        this.data = list;
        this.context = context;
    }

    @Override
    public TrailerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerRecyclerViewAdapter.ViewHolder holder, int position) {
        if( data.size() > 0 ) {
            Picasso.with(context).load(preImgUrl + data.get(position).getKey() + "/0.jpg").
                    placeholder(R.mipmap.ic_launcher).
                    error(R.mipmap.ic_launcher).
                    into(holder.trailer_thumbnail);
            holder.trailer_text.setText(data.get(position).getName());
            holder.trailer_size.setText(String.valueOf(data.get(position).getSize()));
        }

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView trailer_thumbnail;
        TextView trailer_text;
        TextView trailer_size;
        RatingBar ratingBar;

        public ViewHolder(View itemview){
            super(itemview);
            trailer_thumbnail = (ImageView) itemview.findViewById(R.id.trailer_thumbnail);
            trailer_text = (TextView) itemview.findViewById(R.id.trailer_text);
            trailer_size = (TextView) itemview.findViewById(R.id.trailer_size);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("key", data.get(getAdapterPosition()).getKey());
                    watchVideo(data.get(getAdapterPosition()).getKey());
                }
            });
        }
    }
    public void watchVideo(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        }
        catch (ActivityNotFoundException e){
            context.startActivity(webIntent);
        }
    }
}
