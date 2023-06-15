package com.example.dhaaramshalaown.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Domain.DataClass;
import com.example.dhaaramshalaown.Fragments.NumberOfGuests;
import com.example.dhaaramshalaown.Fragments.PlaceDescription;
import com.example.dhaaramshalaown.Fragments.PlaceOverview;
import com.example.dhaaramshalaown.Fragments.PlaceQuality;
import com.example.dhaaramshalaown.Fragments.PlaceRent;
import com.example.dhaaramshalaown.Fragments.PlaceSecurity;
import com.example.dhaaramshalaown.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaceOverviewAdapter extends RecyclerView.Adapter<PlaceOverviewAdapter.PlaceOverviewViewHolder> {
    Context context;
    private ArrayList<String> fragmentNames;
    private ArrayList<DataClass> placeDescriptionList;
    private ArrayList<DataClass> placeOffersList;
    private Integer[] numberOfGuestsList;
    private String rent;
    private ArrayList<String> placeSecurityList;
    FirebaseDatabase database;
    DatabaseReference reference;

    private  ArrayList<String> placequalitylist;

    public PlaceOverviewAdapter(Context context, ArrayList<String> fragmentNames) {
        this.context = context;
        this.fragmentNames = fragmentNames;
    }

    @NonNull
    @Override
    public PlaceOverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_overview_main_recyclerview_items_ui, parent, false);
        return new PlaceOverviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceOverviewAdapter.PlaceOverviewViewHolder holder, int position) {
        String data = fragmentNames.get(position);

        holder.fragmentName.setText(data);

        if (position== 0){

            placequalitylist = PlaceQuality.getPlacequalitylist();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context, 3));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceQualityAdaptor(context, placequalitylist));
        }

        else if(position == 1) {
            placeDescriptionList = PlaceDescriptionAdapter.getSelectedPlaceList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceDescriptionAdapterInPlaceOverview(context, placeDescriptionList));
        }
        else if(position == 2) {
            placeOffersList = ChildPlaceOffersAdapter.getPlaceOfferList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context, 2));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceOfferAdapterInPlaceOverview(context, placeOffersList));
        }
        else if(position == 3) {
            numberOfGuestsList = NumberOfGuests.getNumberOfGuestsList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new NumberOfGuestsAdapterInPlaceOverview(context, numberOfGuestsList));
        }
        else if(position == 4) {
            rent = PlaceRent.getPlaceRent();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceRentAdapterInPlaceOverview(rent));
        }
        else if(position == 5) {
            placeSecurityList = PlaceSecurity.getPlaceSecurityList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceSecurityAdapterInPlaceOverview(context, placeSecurityList));
        }
        else {

        }
    }

    @Override
    public int getItemCount() {
        return fragmentNames.size();
    }

    public class PlaceOverviewViewHolder extends RecyclerView.ViewHolder {
        TextView fragmentName;
        RecyclerView childRecyclerViewPlaceOverview;

        public PlaceOverviewViewHolder(@NonNull View itemView) {
            super(itemView);

            fragmentName = itemView.findViewById(R.id.id_fragmentNamePlaceOverview);
            childRecyclerViewPlaceOverview = itemView.findViewById(R.id.id_childRecyclerViewPlaceOverview);
        }
    }

    public boolean saveTheData() {
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Hotel Data");

        HashMap<String, ArrayList<DataClass>> hashMapForDataClass = new HashMap<>();
        hashMapForDataClass.put("PlaceDescriptionList", placeDescriptionList);
        hashMapForDataClass.put("PlaceOffersList", placeOffersList);

        boolean result1 = reference.setValue(hashMapForDataClass).isSuccessful();
        boolean result2 = reference.child("PlaceSecurityList").setValue(placeSecurityList).isSuccessful();
        boolean result3 = reference.child("Rent").setValue(rent).isSuccessful();

        boolean result = true;
        if(!result1 && !result2 && !result3) result = false;

        if(!result) {
            Toast.makeText(context, "Data Saved Successfully!", Toast.LENGTH_SHORT).show();
             /* TODO: Write the code here for redirecting user to the home page after saving the data. */
            return true;
        }
        return false;
    }
}