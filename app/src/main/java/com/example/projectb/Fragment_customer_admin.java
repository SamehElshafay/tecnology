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

public class Fragment_customer_admin extends Fragment {

    static String Name ;
    static String Phone ;
    static String photo ;
    static String Key ;
    static String state ;

    public Fragment_customer_admin() {
        // Required empty public constructor
    }

    ListView lv;
    public FireBase fb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_admin , container , false);
        fb = new FireBase(getActivity());
        fb.getCustomer();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lv = view.findViewById(R.id.list2);
                MyAdapterSeller SellerAdapter = new MyAdapterSeller(fb.getCustomerNames(), fb.getCustomerState() , fb.getCustomerPhotos(), getActivity());
                lv.setAdapter(SellerAdapter);
            }
        }, 10);

        ((ListView) view.findViewById(R.id.list2)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Name     = fb.getCustomerNames()  .get(position) ;
                state    = fb.getCustomerState()  .get(position) ;
                photo    = fb.getCustomerPhotos() .get(position) ;
                Phone    = fb.getCustomerPhones() .get(position) ;
                Key      = fb.getCustomerkeys()   .get(position) ;
                startActivity(new Intent(getActivity(), CustomerProfile.class));
            }
        });

        return view;
    }
}