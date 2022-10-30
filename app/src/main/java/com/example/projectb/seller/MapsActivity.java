package com.example.projectb.seller;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.projectb.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {

            //  address + point
            String pointsPlusAdress = SellerProfileFragment.splocation.getText().toString() ;
            mMap = googleMap;
            String points = pointsPlusAdress.split("&")[1] ;
            points = points.replace("(", "" ) ;
            points = points.replace(")", "" ) ;
            points = points.replace(",", " ") ;
            Double x = Double.parseDouble(points.split(" ")[0]) ;
            Double y = Double.parseDouble(points.split(" ")[1]) ;
            LatLng position3 = new LatLng(x,y);
            Geocoder myLocation = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> myList = myLocation.getFromLocation( x , y , 1);
            Address address = (Address) myList.get(0);
            String addressStr = "";
            addressStr += address.getAddressLine(0) + ", ";
            addressStr += address.getAddressLine(1) + ", ";
            addressStr += address.getAddressLine(2);
            mMap.addMarker(new MarkerOptions().position(position3).title(addressStr));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
    }
}