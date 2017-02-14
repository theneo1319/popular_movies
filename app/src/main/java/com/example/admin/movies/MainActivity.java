package com.example.admin.movies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.widget.Space;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//moviedb api key = 1401a8cfe672a592a1a72b023d9febd7
//for images https://image.tmdb.org/t/p/w185
//top_rated http://api.themoviedb.org/3/movie/top_rated?api_key=1401a8cfe672a592a1a72b023d9febd7
//videos  http://api.themoviedb.org/3/movie/id/videos?api_key=1401a8cfe672a592a1a72b023d9febd7
//reviews http://api.themoviedb.org/3/movie/id/reviews?api_key=1401a8cfe672a592a1a72b023d9febd7

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String top_rated = "http://api.themoviedb.org/3/movie/top_rated?api_key=1401a8cfe672a592a1a72b023d9febd7";

    public static final String popular = "http://api.themoviedb.org/3/movie/popular?api_key=1401a8cfe672a592a1a72b023d9febd7";

    public static final String baseUrl = "http://api.themoviedb.org/3/";

    public static final String apiKEy = "1401a8cfe672a592a1a72b023d9febd7";

    public static final String preUrl = "http://image.tmdb.org/t/p/w342";

    public static List<MovieData> movieDataList;

    private static SpaceDecorator spaceDecorator;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private  ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        recyclerView = (RecyclerView)findViewById(R.id.main_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);

        //use a layout manager, a grid layout manager with 2 columns in our case

        updateJson();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.action_settings){

          startActivity(new Intent(this, SettingsActivity.class));

            return true;

        }

      return super.onOptionsItemSelected(item);
    }

   //to get movies based on user's settings either top rated or popular
    private void updateJson(){
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        final RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sort_by = sharedPreferences.getString("sort_by","popular");

        Call<JsonMovieResponse> call = requestInterface.getTopRatedMovies(sort_by, apiKEy);
        call.enqueue(new Callback<JsonMovieResponse>() {

            @Override
            public void onResponse(Call<JsonMovieResponse> call, Response<JsonMovieResponse> response) {

                movieDataList = response.body().getResults();
                adapter = new MainRecyclerViewAdapter(MainActivity.this, movieDataList);
                recyclerView.setAdapter(adapter);
                if( spaceDecorator != null )
                    recyclerView.removeItemDecoration(spaceDecorator);
                spaceDecorator = new SpaceDecorator(4,adapter.getItemCount());
                recyclerView.addItemDecoration(spaceDecorator);

                if(mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<JsonMovieResponse> call, Throwable t) {
                Log.e("retrofit error", t.toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).
                unregisterOnSharedPreferenceChangeListener(this);
        Log.i("main","destroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(this).
                registerOnSharedPreferenceChangeListener(this);
        Log.i("activity","main resume");
    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.i("main settings", "changed");
        updateJson();
    }
}

