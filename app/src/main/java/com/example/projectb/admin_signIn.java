package com.example.projectb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.time.temporal.IsoFields;

public class admin_signIn extends AppCompatActivity {
    private FireBase fb ;
    private DatabaseSQL db = new DatabaseSQL(this);
    private Cursor cur ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        fb = new FireBase(this);
        fb.getAdmin() ;

        findViewById(R.id.SigNin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password  = ((EditText)findViewById(R.id.PasSword)).getText().toString() ;
                String username  = ((EditText)findViewById(R.id.UseRName)).getText().toString() ;
                if(!checkEntity()){
                    if(fb.getAdminUNames().contains(username)){
                        int index = fb.getAdminUNames().indexOf(username) ;
                        if(password.equals(fb.getAdminPassword().get(index))){
                            if(IsFieldAreExist(fb.getAdminKeys().get(index))){
                                db.UpdateSinedIn(fb.getAdminKeys().get(index) , "true");
                            }else {
                                db.Insert(fb.getAdminUNames().get(index) , fb.getAdminPassword().get(index) , fb.getAdminKeys().get(index) , "admin" , "true" ) ;
                            }
                            startActivity(new Intent(getApplicationContext() , admin_containt.class));
                            overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
                        }
                    }
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
        return isEmpty(R.id.UseRName) && isEmpty(R.id.PasSword) ;
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