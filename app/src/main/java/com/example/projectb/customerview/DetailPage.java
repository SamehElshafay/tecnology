package com.example.projectb.customerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailPage extends Fragment {

    ImageView productimg ;
    int position;
    ArrayList<Product> products;
    ArrayList<String> productskey;
    public DetailPage(FireBase fb,int position, ArrayList<Product> products, ArrayList<String> productskey) {
        // Required empty public constructor
        this.firebase = fb;
        this.position = position;
        this.products = products;
        this.productskey = productskey;
    }
    FireBase firebase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main3, container, false);
        productimg = view.findViewById(R.id.productimg);
        TextView productname = view.findViewById(R.id.productname);
        TextView productprice = view.findViewById(R.id.productprice);
        TextView productstate = view.findViewById(R.id.productstate);
        TextView producttype = view.findViewById(R.id.producttype);
        TextView productsale = view.findViewById(R.id.sale);
        if(products.get(position).getType().equals("Computers")) {
            productname.setText(products.get(position).getName());
            productprice.setText(products.get(position).getPrice());
            productstate.setText(products.get(position).getState());
            producttype.setText(products.get(position).getType());
            productsale.setText(products.get(position).getSale());
            Picasso.get().load(products.get(position).getPhoto()).into(productimg);
        }
        else if(products.get(position).getType().equals("Accessories")) {
            productname.setText(products.get(position).getName());
            productprice.setText(products.get(position).getPrice());
            productstate.setText(products.get(position).getState());
            producttype.setText(products.get(position).getType());
            productsale.setText(products.get(position).getSale());
            Picasso.get().load(products.get(position).getPhoto()).into(productimg);
        }
        else if(products.get(position).getType().equals("mobilePhone")) {
            productname.setText(products.get(position).getName());
            productprice.setText(products.get(position).getPrice());
            productstate.setText(products.get(position).getState());
            producttype.setText(products.get(position).getType());
            productsale.setText(products.get(position).getSale());
            Picasso.get().load(products.get(position).getPhoto()).into(productimg);
        }
        Button gts = view.findViewById(R.id.gotoseller);
        gts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent());
            }
        });
        Button back = view.findViewById(R.id.backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ComputerPage(firebase,products,productskey);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.view_pager, fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}