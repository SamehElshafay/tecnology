package com.example.projectb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.firebase.storage.*;

public class Fragment_Seller_Admin extends Fragment {
    static String Name ;
    static String Location ;
    static String Phone ;
    static String photo ;
    static String Key ;
    static String state ;
    static String KES ;
    public Fragment_Seller_Admin() {
        // Required empty public constructor
    }
    ListView lv;
    public FireBase fb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__seller__admin, container, false);
        fb = new FireBase(getActivity());
        fb.getSeller() ;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lv = view.findViewById(R.id.list);
                MyAdapterSeller SellerAdapter = new MyAdapterSeller( fb.getSellerNames() , fb.getSellerState() , fb.getSellerPhotos() , getActivity() );
                lv.setAdapter(SellerAdapter);
            }
        },10);

        ((ListView)view.findViewById(R.id.list)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Name     = fb.getSellerNames()    .get(position) ;
                Location = fb.getSellerLocation() .get(position) ;
                Phone    = fb.getSellerPhones()   .get(position) ;
                Key      = fb.getSellerkeys()     .get(position) ;
                state    = fb.getSellerState()    .get(position) ;
                photo    = fb.getSellerPhotos()   .get(position) ;
                KES      = fb.getProductsKEYS()   .get(position) ;
                startActivity(new Intent(getActivity() , SellerProfile.class));
            }
        });

        return view;
    }
}