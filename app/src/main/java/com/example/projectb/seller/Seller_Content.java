package com.example.projectb.seller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projectb.DatabaseSQL;
import com.example.projectb.MainActivity;
import com.example.projectb.R;
import com.example.projectb.admin_containt;
import com.example.projectb.seller.SectionsPagerAdapter;
import com.example.projectb.signin_layout;
import com.google.android.material.tabs.TabLayout;

public class Seller_Content extends AppCompatActivity {
    static String ProductKeys  = signin_layout.PRODUCTKEY ;
    static String Sellerkey    = signin_layout.USERKEY    ;
    FireBase fb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller__content);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        findViewById(R.id.REFRESH).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() ,Seller_Content.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder altdial = new AlertDialog.Builder(Seller_Content.this) ;
        altdial.setMessage("Do You Want To LogOut ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                DatabaseSQL db = new DatabaseSQL(getApplication());
//                db.UpdateSinedIn(Sellerkey , "false") ;
                System.exit(0);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel() ;
            }
        }) ;
        AlertDialog alert = altdial.create() ;
        alert.show() ;
    }
}