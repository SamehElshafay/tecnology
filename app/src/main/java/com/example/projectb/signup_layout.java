package com.example.projectb;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class signup_layout extends AppCompatActivity {
    public static String UserName ;
    public static String FullName ;
    public static String Password ;
    public static String Phone ;
    public static String Type ;
    public static boolean State = true ;
    public FireBase fb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_layout);
        Spinner ACT = findViewById(R.id.ACT);
        ArrayList<String> Activty =  new ArrayList<String>();
        Activty.add("Customer");
        Activty.add("Seller");
        MyTools SpinnerS = new MyTools(ACT , Activty);
        SpinnerS.Spinner(this);

        fb = new FireBase(this);

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserName = ((EditText) findViewById(R.id.UserName)).getText().toString();
                FullName = ((EditText) findViewById(R.id.FullName)).getText().toString();
                Password = ((EditText) findViewById(R.id.password)).getText().toString();
                Phone = ((EditText) findViewById(R.id.phone)).getText().toString();
                Type = ((Spinner) findViewById(R.id.ACT)).getSelectedItem().toString();
                if (Type.equals("Seller")) {
                    fb.getSeller();
                    //if checkEntity() is equal to true . cont...
                    if (!checkEntity()) {
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                            if (fb.getSellerUNames().contains(UserName)) {
                                Toast.makeText(getApplicationContext(), "User Name is already Exist", Toast.LENGTH_SHORT).show();
                            }
                            else if (FullName.length() < 4) {
                                Toast.makeText(getApplicationContext(), "Keep Your Name more than 4", Toast.LENGTH_SHORT).show();
                            }
                            else if (Phone.length() != 11) {
                                Toast.makeText(getApplicationContext(), "Not Phone Number", Toast.LENGTH_SHORT).show();
                            }
                            else if (UserName.length() < 10) {
                                Toast.makeText(getApplicationContext(), "Keep UserName more than 10", Toast.LENGTH_SHORT).show();
                            }
                            else if (Password.length() < 8) {
                                Toast.makeText(getApplicationContext(), "Keep password more than 8", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (Phone.charAt(0) == '0' && Phone.charAt(1) == '1' && (Phone.charAt(1) == '0' || Phone.charAt(1) == '1' || Phone.charAt(1) == '2' || Phone.charAt(1) == '5')) {
                                    fb.setSeller(FullName, UserName, Password, Phone, "", "true");
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Not Phone Number", Toast.LENGTH_SHORT).show();
                                }
                            }

                            }
                        }, 500);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if (!checkEntity()) {
                        fb.getCustomer();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (fb.getCustomerUNames().contains(UserName)) {
                                    Toast.makeText(getApplicationContext(), "User Name is already Exist", Toast.LENGTH_SHORT).show();
                                } else if (FullName.length() < 4) {
                                    Toast.makeText(getApplicationContext(), "Keep Your Name more than 4", Toast.LENGTH_SHORT).show();
                                } else if (Phone.length() != 11) {
                                    Toast.makeText(getApplicationContext(), "Not Phone Number", Toast.LENGTH_SHORT).show();
                                } else if (UserName.length() < 10) {
                                    Toast.makeText(getApplicationContext(), "Keep UserName more than 10", Toast.LENGTH_SHORT).show();
                                } else if (Password.length() < 8) {
                                    Toast.makeText(getApplicationContext(), "Keep password more than 8", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (Phone.charAt(0) == '0' && Phone.charAt(1) == '1' && (Phone.charAt(1) == '0' || Phone.charAt(1) == '1' || Phone.charAt(1) == '2' || Phone.charAt(1) == '5')) {
                                        fb.setCustomer(FullName, UserName, Password, Phone, "true");
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Not Phone Number", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }, 10);
                    } else {
                        Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
    }

    boolean isEmpty(int key){
        return ((EditText) findViewById(key)).getText().toString().equals("") ;
    }
    boolean checkEntity(){
        return isEmpty(R.id.UserName) && isEmpty(R.id.FullName) && isEmpty(R.id.password) && isEmpty(R.id.Pasword) ;
    }
}