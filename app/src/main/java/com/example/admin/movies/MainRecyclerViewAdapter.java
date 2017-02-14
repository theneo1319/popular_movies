package com.example.admin.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.Space;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 20-12-2016.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    List<MovieData> data;
    Context context;

    public MainRecyclerViewAdapter(Context context, List<MovieData> data ){
         this.data = data;
         this.context = context;
    }
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        MainRecyclerViewAdapter.ViewHolder viewHolder = new MainRecyclerViewAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewAdapter.ViewHolder holder, int position) {

        Picasso.with(context).load( data.get(position).getPosterPath()).into(holder.movie_thumbnail);
        //holder.movie_thumbnail.setImageResource(R.mipmap.ic_launcher);
        holder.main_rating.setText( String.valueOf(data.get(position).getVoteAverage()));
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView movie_thumbnail;
        RecyclerView recyclerView;
        TextView main_rating;

        private Context context;

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context, DetailsActivity.class);

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("list", (ArrayList<MovieData>) data);
            Log.i("id",String.valueOf(data.get(getAdapterPosition()).getId()));
            intent.putExtras(bundle);
            intent.putExtra("index", getAdapterPosition());
            context.startActivity(intent);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.main_recyclerview);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
            movie_thumbnail = (ImageView) itemView.findViewById(R.id.movies_item_thumbnail);
            main_rating = (TextView) itemView.findViewById(R.id.main_rating);
        }
    }
}


