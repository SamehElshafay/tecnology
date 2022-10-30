package com.example.projectb.seller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.projectb.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class Product extends Fragment {
    static String ProductKeys = Seller_Content.ProductKeys;
    String Sellerkey          = Seller_Content.Sellerkey;
    static String productName  ;
    static String productPrice ;
    static String productPhoto ;
    static String productSale  ;
    static String productType  ;
    static String productState ;
    static String productKEy   ;

    public Product(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        String KEYS[] = ProductKeys.split(" ");
        ArrayList<String> KEYSLIST = new ArrayList<String>(Arrays.asList(KEYS));
        FireBase fb = new FireBase(getActivity());
        fb.getSellerProduct(KEYSLIST);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GridView lv = view.findViewById(R.id.product_list);
                MyAdapterProduct SellerAdapter = new MyAdapterProduct(fb.getProductNames(), fb.getProductPrice(), fb.getProductPhotos(), fb.getProductSale(), fb.getProductType(), getActivity());
                lv.setAdapter(SellerAdapter);
                SellerAdapter.notifyDataSetChanged();


                view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity(), AddProductActivity.class));
                    }
                });

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        productName  = fb.getProductNames() .get(position) ;
                        productPrice = fb.getProductPrice() .get(position) ;
                        productPhoto = fb.getProductPhotos().get(position) ;
                        productSale  = fb.getProductSale()  .get(position) ;
                        productType  = fb.getProductType()  .get(position) ;
                        productState = fb.getProductState() .get(position) ;
                        productKEy   = KEYSLIST.get(position) ;
                        startActivity(new Intent(getActivity() , SellerProductInformation.class));
                    }
                });

                lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    public boolean onItemLongClick(AdapterView<?> arg0, View v, int index, long arg3) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()) ;
                        builder.setMessage("do you want to delete it ?");
                        builder.setPositiveButton("Delete" , new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fb.DELETECHILD(KEYSLIST.get(index) , Sellerkey , ProductKeys);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog ad = builder.create() ;
                        ad.show();
                        return true;
                    }
                });


            }
        }, 1000);
        return view;
    }
}