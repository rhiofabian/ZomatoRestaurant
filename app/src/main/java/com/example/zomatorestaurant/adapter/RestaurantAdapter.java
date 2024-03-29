// Author: 181511049 Ivan Eka Putra dan 181511064 Rhio Adjie Fabian

package com.example.zomatorestaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zomatorestaurant.R;
import com.example.zomatorestaurant.RestaurantDetailActivity;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private Context context;
    private List<ObjRestaurant> list;
    private Restaurant restaurant;

    public RestaurantAdapter(Context context){
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(this.context).inflate(R.layout.recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    public void setData(List<ObjRestaurant> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setDataDetail(Restaurant restaurant){
        this.restaurant = restaurant;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ObjRestaurant restaurant = list.get(position);
        String costForOne = (restaurant.getRestaurant().getAverage() / 2) + " per person";

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, RestaurantDetailActivity.class);
                intent.putExtra("restaurantId", restaurant.getRestaurant().getId());
                context.startActivity(intent);
            }
        });

        Glide.with(this.context)
                .load(restaurant.getRestaurant().getThumb())
                .into(holder.ivRestaurantImage);
        holder.tvRestaurantName.setText(restaurant.getRestaurant().getName());
        holder.tvCurrency.setText(restaurant.getRestaurant().getCurrency());
        holder.tvCostPerOne.setText(costForOne);
        if (restaurant.getRestaurant().getHasOnline() == 1) {
            holder.tvHasOnlineDelivery.setText(R.string.onlineOrderingAvailable);
        } else {
            holder.tvHasOnlineDelivery.setText(R.string.noOnlineOrderingAvailable);
        }
        holder.tvAggregateRating.setText(restaurant.getRestaurant().getUserRating().getRating().toString());
    }

    @Override
    public int getItemCount(){
        if(this.list == null){
            return 0;
        }
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRestaurantImage;
        TextView tvRestaurantName, tvCurrency, tvCostPerOne, tvHasOnlineDelivery, tvAggregateRating;

        ViewHolder(View view){
            super(view);
            ivRestaurantImage = view.findViewById(R.id.iv_restaurant);
            tvRestaurantName = view.findViewById(R.id.tv_user_name);
            tvCurrency = view.findViewById(R.id.tv_restaurant_currency);
            tvCostPerOne = view.findViewById(R.id.tv_restaurant_cost_for_one);
            tvHasOnlineDelivery = view.findViewById(R.id.tv_restaurant_has_online_delivery);
            tvAggregateRating = view.findViewById(R.id.tv_aggregate_rating);
        }
    }
}
