package com.example.dhaaramshalaown.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dhaaramshalaown.Fragments.PlaceSecurity;
import com.example.dhaaramshalaown.R;

import java.util.ArrayList;

public class PlaceQualityAdaptor extends RecyclerView.Adapter<PlaceQualityAdaptor.PlaceQualityViewHolder>{
    Context context;
    ArrayList<String> placequalitylist;

    public PlaceQualityAdaptor(Context context, ArrayList<String> placequalitylist) {
        this.context = context;
        this.placequalitylist = placequalitylist;
    }

    @Override
    public PlaceQualityAdaptor.PlaceQualityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_quality_items_ui, parent, false);
        return new  PlaceQualityViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PlaceQualityAdaptor.PlaceQualityViewHolder holder, int position) {
        String data = placequalitylist.get(position);

        if(data.equals("Charming")) {
            holder.title.setText("Charming");
        }
        if(data.equals("Hip")) {
            holder.title.setText("Hip");
        }
        if(data.equals("Stylish")) {
            holder.title.setText("Stylish");
        }
        if(data.equals("Upscale")) {
            holder.title.setText("Upscale");
        }
        if(data.equals("Central")) {
            holder.title.setText("Central");
        }
        if(data.equals("Unique")) {
            holder.title.setText("Unique");
        }
    }

    @Override
    public int getItemCount() {
        return placequalitylist.size();
    }

    public class PlaceQualityViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public PlaceQualityViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
        }
    }
}
