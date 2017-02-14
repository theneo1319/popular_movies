package com.example.admin.movies;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.DEVICE_POLICY_SERVICE;
import static com.example.admin.movies.DetailsActivity.index;
import static com.example.admin.movies.DetailsActivity.list;
import static com.example.admin.movies.DetailsActivity.movieReviewList;

/**
 * Created by admin on 26-01-2017.
 */

public class Tab3Fragment extends Fragment {

    private ImageView trailer_thumbnail;
    private static List<MovieTrailer> movieTrailerList;
    private static final String  preImgUrl = "http://img.youtube.com/vi/";
    private  ProgressDialog mProgressDialog;
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private static RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.tab3_fragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.trailer_recycleview);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        updateTrailer();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void updateTrailer(){
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.baseUrl).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        final RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<MovieTrailerResponse> call1 = requestInterface.getMovieTrailers(list.get(index).getId(), MainActivity.apiKEy);

        call1.enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {

                movieTrailerList = response.body().getResults();
                adapter = new TrailerRecyclerViewAdapter( movieTrailerList, getContext());
                recyclerView.setAdapter(adapter);
                Log.i("size", String.valueOf(movieTrailerList.size()));



                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });

    }



}
