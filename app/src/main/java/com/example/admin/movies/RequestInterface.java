package com.example.admin.movies;

/**
 * Created by admin on 30-12-2016.
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestInterface {
    @GET("movie/{sort_by}")
    Call<JsonMovieResponse> getTopRatedMovies(@Path("sort_by") String sort_by, @Query("api_key") String apiKey);
    @GET("movie/popular")
    Call<JsonMovieResponse> getPopularMovies(@Query("api_key") String apiKey);
    @GET("movie/{id}")
    Call<MovieData> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<MovieReviewResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<MovieTrailerResponse> getMovieTrailers(@Path("id") int id, @Query("api_key") String apiKey);

}
