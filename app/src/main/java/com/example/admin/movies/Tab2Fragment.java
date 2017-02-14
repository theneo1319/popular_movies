package com.example.admin.movies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.admin.movies.DetailsActivity.index;
import static com.example.admin.movies.DetailsActivity.list;

/**
 * Created by admin on 26-01-2017.
 */

public class Tab2Fragment extends Fragment {



    private static TextView reviews;
    private static RecyclerView recyclerView;
    private static ReviewRecyclerViewAdapter adapter;
    private static RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.tab2_fragment,container,false);
        reviews = (TextView) rootView.findViewById(R.id.review_heading);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.review_recycleview);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        updateJson();
        return rootView;
    }


    //to get reviews of the movie
    private void updateJson() {

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.baseUrl).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<MovieReviewResponse> call = requestInterface.getMovieReviews(list.get(index).getId(), MainActivity.apiKEy);

        call.enqueue(new Callback<MovieReviewResponse>() {
            @Override
            public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {
                List<MovieReview> movieReviewList = response.body().getResults();
                if( movieReviewList.size() > 0 ){
                    Log.i("review_size", String.valueOf(movieReviewList.size()));
                    adapter = new ReviewRecyclerViewAdapter(movieReviewList);
                    recyclerView.setAdapter(adapter);
                }

                else{
                    reviews.setText("No Reviews Yet for this movie");
                    reviews.setGravity(Gravity.CENTER);
                    reviews.setTextSize(10);
                }

            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {
                Log.i("review_error",t.toString());
            }
        });

    }


}
