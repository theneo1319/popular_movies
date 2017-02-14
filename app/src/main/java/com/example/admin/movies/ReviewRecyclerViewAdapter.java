package com.example.admin.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 05-02-2017.
 */

public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ViewHolder> {

    private  List<MovieReview> data;

    public ReviewRecyclerViewAdapter(List<MovieReview> data) {
        this.data = data;
    }

    @Override
    public ReviewRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewRecyclerViewAdapter.ViewHolder holder, int position) {
        if( data.size() > 0 ){
            holder.review_text.setText(data.get(position).getContent());
            holder.review_author.setText(data.get(position).getAuthor());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView review_text;
        TextView review_author;

        public ViewHolder(View itemView) {
            super(itemView);
            review_text = (TextView) itemView.findViewById(R.id.review_text);
            review_author = (TextView) itemView.findViewById(R.id.review_author);
        }
    }
}
