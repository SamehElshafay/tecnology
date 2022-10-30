package com.example.projectb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        String Name = Fragment_customer_admin.Name ;
        String Phone = Fragment_customer_admin.Phone ;
        String state = Fragment_customer_admin.state ;
        String Photo = Fragment_customer_admin.photo ;
        String Key = Fragment_customer_admin.Key ;


        ((TextView)findViewById(R.id.CustomerNameProfile)).setText(Name);
        ((TextView)findViewById(R.id.CustomerPhoneNumberProfile)).setText(Phone);
        Switch SwitchState = (Switch) findViewById(R.id.switchState) ;

        FireBase fb = new FireBase(this);
        if(state.equals("true")) {
            ((TextView) findViewById(R.id.CustomerStateProfile)).setText("active");
            SwitchState.setChecked(true);
        }
        else {
            ((TextView) findViewById(R.id.CustomerStateProfile)).setText("Blocked");
            SwitchState.setChecked(false);
        }

        Picasso.get().load(Photo).into((ImageView) findViewById(R.id.CustomerImageProfile));

        SwitchState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(isChecked == true){
                       ((TextView) findViewById(R.id.CustomerStateProfile)).setText("active");
                       fb.setCustomerState(Key , "true");
                   }
                   else {
                       ((TextView) findViewById(R.id.CustomerStateProfile)).setText("Blocked");
                       fb.setCustomerState(Key , "false");
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