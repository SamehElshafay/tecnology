package com.example.projectb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectb.seller.Seller_Content;

public class signin_layout extends AppCompatActivity {
    String userName ;
    String password ;
    public static String USERKEY ;
    public static String PRODUCTKEY ;
    public static String SELLERPHOTO ;
    public FireBase fb ;
    private DatabaseSQL db = new DatabaseSQL(this);
    private Cursor cur ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_layout);
        fb = new FireBase(this);

        findViewById(R.id.signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = ((EditText) findViewById(R.id.UserName)).getText().toString();
                password = ((EditText) findViewById(R.id.Pasword)).getText().toString();
                fb.getSeller() ;
                fb.getCustomer() ;
                if (!checkEntity()) {
                    if (fb.getCustomerUNames().contains(userName)) {
                        if (password.equals(fb.getCustomerPassword().get(fb.getCustomerUNames().indexOf(userName)))) {
                            if ("true".equals(fb.getCustomerState().get(fb.getCustomerUNames().indexOf(userName)))) {
                                Intent intent = new Intent(getApplicationContext() , com.example.projectb.customerview.MainActivity.class ) ;
                                intent.putExtra(fb.getCustomerUNames().get(fb.getCustomerUNames().indexOf(userName)) , "1" ) ;
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "You are blocked", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (fb.getSellerUNames().contains(userName)) {
                        if (password.equals(fb.getSellerPassword().get(fb.getSellerUNames().indexOf(userName)))) {
                            if ("true".equals(fb.getSellerState().get(fb.getSellerUNames().indexOf(userName)))) {
                                USERKEY     = fb.getSellerkeys().get(fb.getSellerUNames().indexOf(userName)) ;
                                PRODUCTKEY  = fb.getProductsKEYSFromSeller().get(fb.getSellerUNames().indexOf(userName)) ;
                                System.out.println("000000000000000000 "+ USERKEY + " " + PRODUCTKEY );
                                SELLERPHOTO = fb.getSellerPhotos().get(fb.getSellerUNames().indexOf(userName)) ;
//                                int index = fb.getSellerUNames().indexOf(userName) ;
//                                if(IsFieldAreExist(fb.getSellerkeys().get(index))){
//                                    db.UpdateSinedIn(fb.getSellerkeys().get(index) , "true");
//                                }else {
//                                    db.Insert(fb.getSellerUNames().get(index) , fb.getSellerPassword().get(index) , fb.getSellerkeys().get(index) ,  "seller" , "true" ) ;
//                                }
                                startActivity(new Intent(getApplicationContext() , Seller_Content.class));
                                overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "You are blocked", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "User Name not found", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
    }
    boolean isEmpty(int key){
        return ((EditText) findViewById(key)).getText().toString().equals("") ;
    }
    boolean checkEntity(){
        return isEmpty(R.id.UserName) && isEmpty(R.id.Pasword) ;
    }

    boolean IsFieldAreExist(String key){
        boolean s = false ;
        try {
            cur = db.getAll();
            cur.moveToFirst();
            int counter = 0;
            while (cur.moveToNext()) {
                if (key.equals(cur.getString(0))) {
                    s = true;
                }
                counter++;
            }
        }catch (Exception ex){

        }
        return s ;
    }
}