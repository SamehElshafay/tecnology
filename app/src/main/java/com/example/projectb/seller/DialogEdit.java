package com.example.projectb.seller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.projectb.R;
import com.example.projectb.seller.GPSTracker;
import com.example.projectb.seller.MapsActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DialogEdit {
    private String txt ;
    private String title ;
    private Context con ;
    private EditText input ;
    private Button ShowButton ;
    private FirebaseDatabase db ;
    private DatabaseReference x ;

    DialogEdit(Context con , String parent){
        this.con = con ;
        db = FirebaseDatabase.getInstance() ;
        x = db.getReference("contact") ;
    }

    AlertDialog EditFeild(String sellerKey , String attribute , String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(con) ;
        builder.setMessage(message);
        input = new EditText(con) ;
        input.setBackgroundResource(R.drawable.edit_text_design);
        input.setPadding(50 , 50 ,50 , 50 );
        builder.setView(input);

        builder.setPositiveButton("Change" , new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!input.getText().toString().equals("")) {
                    String NewValue = input.getText().toString();
                    x.child("Seller").child(sellerKey).child(attribute).setValue(NewValue) ;
                    Toast.makeText(con , "Information Updated" , Toast.LENGTH_SHORT).show() ;
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = builder.create() ;
        return ad ;
    }

    AlertDialog EditFeildForProduct(String productKey , String attribute , String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(con) ;
        builder.setMessage(message);
        input = new EditText(con) ;
        input.setBackgroundResource(R.drawable.edit_text_design);
        input.setPadding(50 , 50 ,50 , 50 );
        builder.setView(input);
        builder.setPositiveButton("Change" , new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!input.getText().toString().equals("")) {
                    String NewValue = input.getText().toString();
                    System.out.println("+++++++++++++++++++++++++++++++"+productKey);
                    x.child("Product").child(productKey).child(attribute).setValue(NewValue) ;
                    Toast.makeText(con , "Information Updated" , Toast.LENGTH_SHORT).show() ;
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = builder.create() ;
        return ad ;
    }


    AlertDialog EditFeildAndMap(String sellerKey , String attribute , String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(con) ;
        builder.setMessage(message);
        Button btn = new Button(con) ;
        btn.setText("open map");
        builder.setView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.startActivity(new Intent(con , MapsActivity.class));
            }
        });
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    GPSTracker GPS = new GPSTracker(con);
                    GPS.getLocation();
                    String points = "(" + GPS.getLatitude() + "," + GPS.getLongitude() + ")";
                    Geocoder myLocation = new Geocoder(con, Locale.getDefault());
                    List<Address> myList = myLocation.getFromLocation(GPS.getLatitude(), GPS.getLongitude(), 1);
                    try {
                        Address address = (Address) myList.get(0);
                        String addressStr = "";
                        addressStr += address.getAddressLine(0) + ", ";
                        addressStr += address.getAddressLine(1) + ", ";
                        addressStr += address.getAddressLine(2);
                        points = addressStr + "&" + points;
                        x.child("Seller").child(sellerKey).child(attribute).setValue(points);
                        Toast.makeText(con, "Location Is Updated Successfully", Toast.LENGTH_SHORT).show();
                    } catch (Exception ex){
                        Toast.makeText(con, "permission needed", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(con, "permission needed", Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = builder.create() ;
        return ad ;
    }

}
