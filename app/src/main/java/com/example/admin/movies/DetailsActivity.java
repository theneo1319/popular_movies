package com.example.admin.movies;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailsActivity extends AppCompatActivity {

    public static List<MovieData> list;
    public static int index;
    private ViewPager viewPager;
    private Bundle bundle;
    public static List<MovieTrailer> movieTrailerList;
    public static List<MovieReview> movieReviewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);





        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        bundle = intent.getExtras();
        list = bundle.getParcelableArrayList("list");
        Log.i("index", Integer.toString(index));
        Log.i("string", list.get(index).getTitle());


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setTitle(list.get(index).getTitle());


        ImageView imgTlbar = (ImageView) findViewById(R.id.imgToolbar);
        Picasso.with(this).load(list.get(index).getBackdropPath()).into(imgTlbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);




    }

    private void setupViewPager(ViewPager viewPager){
       ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewPagerAdapter.addFrag(new Tab1Fragment(),"Details");
        viewPagerAdapter.addFrag(new Tab2Fragment(), "Reviews");
        viewPagerAdapter.addFrag(new Tab3Fragment(), "Trailers");

        viewPager.setAdapter(viewPagerAdapter);

    }

   private void updateReview(){

        Retrofit retrofit = new Retrofit.Builder().
                            baseUrl(MainActivity.baseUrl).
                            addConverterFactory(GsonConverterFactory.create()).
                            build();
        final RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<MovieReviewResponse> call = requestInterface.getMovieReviews(list.get(index).getId(), MainActivity.apiKEy );

        call.enqueue(new Callback<MovieReviewResponse>() {
            @Override
            public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {
                movieReviewList = response.body().getResults();
                Log.i("review","response received");
            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {

            }
        });



    }

    private void updateTrailer(){
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
                Log.i("trailer","response received");

            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if( item.getItemId() == android.R.id.home ){
            finish(); // close the activity and return to home
        }

        return super.onOptionsItemSelected(item);
    }
}
