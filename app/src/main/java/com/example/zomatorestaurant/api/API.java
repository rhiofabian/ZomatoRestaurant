// Author: 181511049 Ivan Eka Putra dan 181511064 Rhio Adjie Fabian

package com.example.zomatorestaurant.api;

import com.example.zomatorestaurant.pojo.Restaurant;
import com.example.zomatorestaurant.pojo.Restaurants;
import com.example.zomatorestaurant.pojo.Reviews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface API {
    // Fetch all restaurant
    @Headers({
            "Accept: application/json",
            "user-key: a64754bb91447efbe864016d75dee292"
    })
    @GET("search")
    Call<Restaurants> fetchData(@Query("entity_id") int entity_id, @Query("entity_type") String entity_type);

    @Headers({
            "Accept: application/json",
            "user-key: a64754bb91447efbe864016d75dee292"
    })
    @GET("restaurant")
    Call<Restaurant> fetchDataDetail(@Query("res_id") int res_id);

    @Headers({
            "Accept: application/json",
            "user-key: a64754bb91447efbe864016d75dee292"
    })
    @GET("reviews")
    Call<Reviews> fetchReviews(@Query("res_id") int res_id);

}
