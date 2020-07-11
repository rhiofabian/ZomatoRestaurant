package com.example.zomatorestaurant;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomatorestaurant.api.API;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.Restaurant;
import com.example.zomatorestaurant.pojo.Restaurants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RestaurantViewModel extends ViewModel {

    // Instance of class ViewModel
    private MutableLiveData<List<ObjRestaurant>> restaurantList;
    private MutableLiveData<Restaurant> restaurant;

    // Fetch data
    public LiveData<List<ObjRestaurant>> getRestaurant(){
        if(this.restaurantList == null){
            this.restaurantList = new MutableLiveData<List<ObjRestaurant>>();
            fetchDataRestaurant();
        }
        return this.restaurantList;
    }

    public LiveData<Restaurant> getRestaurantDetail(int restaurantId) {
        if(this.restaurant == null){
            this.restaurant = new MutableLiveData<Restaurant>();
            fetchDataRestaurantDetail(restaurantId);
        }
        return this.restaurant;
    }

    private void fetchDataRestaurant(){
//        Retrofit retrofit = new Retrofit.Builder()
//                                            .baseUrl(Config.BASE_URL)
//                                            .addConverterFactory(GsonConverterFactory.create())
//                                            .build();
//        API api = retrofit.create(API.class);
//        Call<List<Restaurants>> call = api.fetchData();
//
//        call.enqueue(new Callback<List<Restaurants>>() {
//            @Override
//            public void onResponse(Call<List<Restaurants>> call, Response<List<Restaurants>> response) {
//                restaurantList.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Restaurants>> call, Throwable t) {
//
//            }
//        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Restaurants> call = api.fetchData();

        call.enqueue(new Callback<Restaurants>() {
            @Override
            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                restaurantList.setValue(response.body().getRestaurant());
//                recyclerView.setAdapter(new RestaurantAdapter(getApplicationContext(), restaurantsList));
            }

            @Override
            public void onFailure(Call<Restaurants> call, Throwable t) {

            }
        });
    }

    private void fetchDataRestaurantDetail(int restaurantId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Restaurant> call = api.fetchDataDetail(restaurantId);

        call.enqueue(new Callback<Restaurant>() {

            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                restaurant.setValue(response.body());
                Log.e("", response.body().getName());
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
