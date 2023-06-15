package com.example.dhaaramshalaown.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dhaaramshalaown.Adapters.ChildPlaceOffersAdapter;
import com.example.dhaaramshalaown.Adapters.PlaceDescriptionAdapter;
import com.example.dhaaramshalaown.Adapters.PlaceQualityAdaptor;
import com.example.dhaaramshalaown.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Toolbar extends Fragment {

    ImageView exit;
    Button next, back, help;
    FrameLayout layoutForFragments;
    PlaceOverview placeOverview;
    RelativeLayout bottomLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        initializeTheFields(view);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialogPrompt();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("HELP!");
                dialog.setCancelable(true);


                int count = fragmentManager.getBackStackEntryCount();

                if(count == 1) {
                    dialog.setMessage("Enter the name of your Dharmshala.");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                }

                else if(count == 2) {
                    dialog.setMessage("Select your highlights.");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }

                else if(count == 3) {
                    dialog.setMessage("Select the type of your place.");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }
                else if(count == 4) {
                    dialog.setMessage("Enter the correct location of your Dharmshala");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }
                else if(count == 5) {
                    dialog.setMessage("Select the faciltities you are providing.");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }
                else if(count == 6) {
                    dialog.setMessage("Give the description of you place");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }
                else if(count == 7) {
                    dialog.setMessage("Enter the price for the rooms.");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }
                else if(count == 8) {
                    dialog.setMessage("Select the option if any one of these your place have.");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }
                else if(count == 9) {
                    dialog.setMessage("Upload the respective images.");
                    dialog.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }
                else if(count == 10) {
                    dialog.setMessage("Confirm the details given by you");
                    dialog.setNegativeButton("ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                }
                else {
                    Toast.makeText(getActivity(), "Please wait.", Toast.LENGTH_SHORT).show();
                    placeOverview.saveTheData(bottomLayout);
                }
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = fragmentManager.getBackStackEntryCount();

                if(count == 0) {
                    inflateFragment(new Hotel_name());
                }

               else if(count == 1) {
                       inflateFragment(new PlaceQuality());
                }

                else if(count == 2) {
                    if (PlaceQuality.getPlacequalitylist().size() > 0) {
                        inflateFragment(new PlaceDescription());
                    }
                    else {
                        Toast.makeText(getContext(), "You have to select atleast 1 item from the given category", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(count == 3) {
                    if(PlaceDescriptionAdapter.getSelectedPlaceList().size() > 0) {
                        if(isMapServicesOK()) {
                            inflateFragment(new Map(getContext(), getChildFragmentManager()));
                        }
                        else {
                            Toast.makeText(getActivity(), "You can't go forward!", Toast.LENGTH_SHORT).show();
                            getFragmentManager().popBackStackImmediate();
                        }
                    }
                    else {
                        Toast.makeText(getContext(), "You have to select atleast 1 item from the list", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(count == 4) {
                    inflateFragment(new PlaceOffers());
                }
                else if(count == 5) {
                    if(ChildPlaceOffersAdapter.getPlaceOfferList().size() > 0) {
                        inflateFragment(new NumberOfGuests());
                    }
                    else {
                        Toast.makeText(getContext(), "You have to select atleast 1 item from the list", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(count == 6) {
                    inflateFragment(new PlaceRent());
                }
                else if(count == 7) {
                    inflateFragment(new PlaceSecurity());
                }
                else if(count == 8) {
                    if(PlaceSecurity.getPlaceSecurityList().size() > 0) {
//                        inflateFragment(new PlaceOverview());
                        inflateFragment(new Upload_image());
                    }
                    else {
                        Toast.makeText(getContext(), "You have to select atleast 1 item from the list", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(count == 9) {
                    placeOverview = new PlaceOverview();
                    inflateFragment(placeOverview);
                    next.setText("Save");
                }
                else {
                    Toast.makeText(getActivity(), "Please wait, Date Saving is in progress!", Toast.LENGTH_SHORT).show();
                    placeOverview.saveTheData(bottomLayout);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragmentManager.getBackStackEntryCount() == 10) {
                    next.setText("Next");
                }

                if(fragmentManager.getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStackImmediate();
                }
                else {
                    Toast.makeText(getContext(), "No More Fragments!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        inflateFragment(new Hotel_name());
//        inflateFragment(new PlaceDescription());
//        inflateFragment(new PlaceOffers());
//        inflateFragment(new NumberOfGuests());

        return view;
    }

    void initializeTheFields(View view) {
        exit = view.findViewById(R.id.id_exit);
        next = view.findViewById(R.id.id_next);
        back = view.findViewById(R.id.id_back);
        help = view.findViewById(R.id.id_help);
        layoutForFragments = view.findViewById(R.id.id_layoutForFragments);
        bottomLayout = view.findViewById(R.id.id_relativeLayout);
    }

    void inflateFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_layoutForFragments, fragment);
        transaction.addToBackStack(fragment.toString());
        transaction.commit();
    }

    void openAlertDialogPrompt() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("Are you sure to Exit?");
        dialog.setCancelable(true);

        dialog.setPositiveButton("Exit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Toast.makeText(getContext(), "Exit Successfully!!", Toast.LENGTH_SHORT).show();
                        getActivity().finishAffinity();
                    }
                });
        dialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    Boolean isMapServicesOK() {
        Toast.makeText(getContext(), "Services Detecting", Toast.LENGTH_SHORT).show();
        int availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());

        if(availability == ConnectionResult.SUCCESS) {
            //Everything is fine and User can place a MAP Request.
//            Toast.makeText(getActivity(), "All good, Ready to Go!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(availability)) {
            //an error occurred but we can resolve it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), availability, 101);
            dialog.show();
        }
        else {
            Toast.makeText(getContext(), "You can't make Map Request!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}