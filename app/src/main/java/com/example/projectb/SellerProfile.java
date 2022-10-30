package com.example.projectb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class SellerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);

        String Name     = Fragment_Seller_Admin.Name ;
        String Location = Fragment_Seller_Admin.Location ;
        String Phone    = Fragment_Seller_Admin.Phone ;
        String Key      = Fragment_Seller_Admin.Key ;
        String state    = Fragment_Seller_Admin.state ;
        String Photo    = Fragment_Seller_Admin.photo ;
        String KEYS []  = Fragment_Seller_Admin.KES.split(" ") ;
        ArrayList<String> KEYSLIST = new ArrayList<String>(Arrays.asList(KEYS));

        ((TextView)findViewById(R.id.SellerNameProfile)).setText(Name);
        ((TextView)findViewById(R.id.SellerLocationProfile)).setText(Location);
        ((TextView)findViewById(R.id.SellerPhoneNumberProfile)).setText(Phone);
        Switch SwitchState = (Switch) findViewById(R.id.SswitchState) ;

        if(state.equals("true")) {
            ((TextView) findViewById(R.id.SellerStateProfile)).setText("active");
            SwitchState.setChecked(true);
        }
        else {
            ((TextView) findViewById(R.id.SellerStateProfile)).setText("Blocked");
            SwitchState.setChecked(false);
        }

        Picasso.get().load(Photo).into((ImageView) findViewById(R.id.SellerImageProfile));

        FireBase fb = new FireBase(this) ;

        fb.getSellerProduct(KEYSLIST) ;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListView lv = findViewById(R.id.productList);
                MyAdapterProduct SellerAdapter = new MyAdapterProduct(fb.getProductNames(), fb.getProductPrice(), fb.getProductPhotos(), fb.getProductSale(), fb.getProductType(), getApplicationContext());
                lv.setAdapter(SellerAdapter);
                SellerAdapter.notifyDataSetChanged();
            }
        },10);

        SwitchState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(isChecked == true){
                       ((TextView) findViewById(R.id.SellerStateProfile)).setText("active");
                       fb.setSellerState(Key , "true");
                   }
                   else {
                       ((TextView) findViewById(R.id.SellerStateProfile)).setText("Blocked");
                       fb.setSellerState(Key , "false");
                   }
               }
           }
        );

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext() , admin_containt.class));
        overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
    }
}