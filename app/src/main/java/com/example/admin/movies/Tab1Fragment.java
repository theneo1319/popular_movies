package com.example.admin.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.nio.FloatBuffer;
import java.util.List;

import retrofit2.http.Path;

import static com.example.admin.movies.DetailsActivity.index;
import static com.example.admin.movies.DetailsActivity.list;

/**
 * Created by admin on 26-01-2017.
 */

public class Tab1Fragment extends Fragment {



    public Tab1Fragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.tab1_fragment, container, false);


        ImageView details_image = (ImageView) rootview.findViewById(R.id.details_image);
        Picasso.with(getContext()).load(list.get(index).getPosterPath()).into(details_image);

        TextView release_date = (TextView) rootview.findViewById(R.id.release_date);
        release_date.setText(list.get(index).getReleaseDate());

        TextView votes = (TextView) rootview.findViewById(R.id.votes);
        votes.setText(Double.toString(list.get(index).getVoteAverage()));
        //rating bar
        RatingBar ratingBar = (RatingBar) rootview.findViewById(R.id.rating_bar);
        ratingBar.setRating(Float.parseFloat(Double.toString(list.get(index).getVoteAverage()/2)));
        Log.i("rating", String.valueOf(Float.parseFloat(Double.toString(list.get(index).getVoteAverage()/2))));

        TextView overview = (TextView) rootview.findViewById(R.id.overview_text);
        overview.setText(list.get(index).getOverview());


        return rootview;
    }


}
