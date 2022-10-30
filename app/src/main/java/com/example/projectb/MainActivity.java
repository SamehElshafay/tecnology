package com.example.projectb;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.BoringLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectb.seller.Seller_Content;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList <String> keys       = new ArrayList<>();
    ArrayList <String> name       = new ArrayList<>();
    ArrayList <String> password   = new ArrayList<>();
    ArrayList <String> type       = new ArrayList<>();
    ArrayList <String> signedIn   = new ArrayList<>();

    public static String Key ;
    public static String Boole  = "false" ;

    private static int TIMEOUT = 4000 ;
    private TextView tv ;
    private ImageView iv ;
    private LinearLayout ll ;
    private DatabaseSQL db ;
    private Cursor cur ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseSQL(getApplicationContext());

        cur = db.getAll();
        cur.moveToFirst();
        int counter = 0 ;
        while(cur.moveToNext()){
            keys.add(cur.getString(0)) ;
            name.add(cur.getString(1)) ;
            password.add(cur.getString(2)) ;
            signedIn.add(cur.getString(3)) ;
            type.add(cur.getString(4)) ;
//            db.UpdateSinedIn(cur.getString(0) , "false");
//            db.delete(cur.getString(0));
            System.out.println("================"+cur.getString(0));
            System.out.println("================"+cur.getString(1));
            System.out.println("================"+cur.getString(2));
            System.out.println("================"+cur.getString(3));
            System.out.println("================"+cur.getString(4));
            counter++;
            cur.moveToNext();
        }


        if(signedIn.contains("true")){
            int index = signedIn.indexOf("true") ;
            if(type.get(index).equals("admin")){
                Key = keys.get(index) ;
                startActivity(new Intent(getApplicationContext() , admin_containt.class));
                Boole = "true" ;
            }
//            else if(type.get(index).equals("seller")){
//                Key = keys.get(index) ;
//                startActivity(new Intent(getApplicationContext() , Seller_Content.class));
//                Boole = "true" ;
//            }
//            else if(type.get(index).equals("customer")){
//                Key = keys.get(index) ;
//                startActivity(new Intent(getApplicationContext() , ));
//                Boole = "true" ;
//            }
        }


        tv = (TextView) findViewById(R.id.NameText) ;
        iv = (ImageView) findViewById(R.id.LogoImage) ;
        ll = (LinearLayout) findViewById(R.id.form2) ;

        tv.animate().alpha(0f).setDuration(1);
        iv.animate().alpha(0f).setDuration(1);
        ll.animate().alpha(0f).setDuration(1);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                tv.animate().alpha(1f).setDuration(3000);
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        iv.animate().alpha(1f).setDuration(3000);
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                ll.animate().alpha(1f).setDuration(3000);
                            }
                        }, 500);
                    }
                }, 500);
            }
        }, 500);
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openActivity(0 , getApplication() , admin_signIn.class);
                return true;
            }
        });
        findViewById(R.id.signinButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(1 , getApplication() , signin_layout.class);
            }
        });

        findViewById(R.id.signupButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(0 , getApplication() , signup_layout.class);
            }
        });
    }
    public void openActivity(int x, Context con , Class CLass ) {
        startActivity(new Intent(MainActivity.this, CLass));
        if (x == 0) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        else {
            overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
        }
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}