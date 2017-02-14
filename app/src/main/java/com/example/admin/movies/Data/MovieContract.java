package com.example.admin.movies.Data;

import android.provider.BaseColumns;

import java.security.PublicKey;

/**
 * Created by admin on 28-12-2016.
 */

public class MovieContract {

    private MovieContract(){}


    public static class MovieEntry implements BaseColumns{

        public static final String TABLE_NAME = "movies";
        public static final String OVER_VIEW = "over_view";
    }
}
