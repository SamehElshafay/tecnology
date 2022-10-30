package com.example.projectb;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class admin_containt extends AppCompatActivity {
    String Key = MainActivity.Key ;
    String boo = MainActivity.Boole ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_containt);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //FloatingActionButton fab = findViewById(R.id.fab);
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        final int orientation = display.getOrientation();
        // OR: orientation = getRequestedOrientation(); // inside an Activity
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext() , Messages.class));
//                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
//            }
//        });

        findViewById(R.id.REFRESH2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , admin_containt.class));
            }
        });
        findViewById(R.id.LogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseSQL db = new DatabaseSQL(getApplication());
                db.UpdateSinedIn(Key , "false") ;

                AlertDialog.Builder altdial = new AlertDialog.Builder(admin_containt.this) ;
                altdial.setMessage("Do You Want To LogOut ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseSQL db = new DatabaseSQL(getApplication());
                        db.UpdateSinedIn(Key , "false") ;
                        startActivity(new Intent(admin_containt.this , MainActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder altdial = new AlertDialog.Builder(admin_containt.this) ;
        altdial.setMessage("Do You Want To LogOut ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseSQL db = new DatabaseSQL(getApplication());
                db.UpdateSinedIn(Key , "false") ;
                startActivity(new Intent(getApplicationContext() , MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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