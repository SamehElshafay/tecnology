package com.example.projectb.customerview;

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
import java.util.Random;

public class HomePage extends Fragment {
    static int po = 0 ;
    public HomePage(FireBase fireBase) {
        // Required empty public constructor
        this.fireBase = fireBase;
    }
    FireBase fireBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        Button phonebtn = view.findViewById(R.id.phonebtn);
        Button compubtn = view.findViewById(R.id.compbtn);
        Button accbtn = view.findViewById(R.id.accbtn);
        ArrayList<Product> computer = new ArrayList<>();
        ArrayList<Product> phone = new ArrayList<>();
        ArrayList<Product> access = new ArrayList<>();
        ArrayList<String> computerkeys = new ArrayList<>();
        ArrayList<String> phonekeys = new ArrayList<>();
        ArrayList<String> accesskeys = new ArrayList<>();
        int pos = 0;
        for(Product product : fireBase.getProducts()){
            if(product.getType().equals("Computers")){
                computer.add(product);
                pos = fireBase.getProducts().indexOf(product);
                computerkeys.add(fireBase.getProductskeys().get(pos));
            }
            else if(product.getType().equals("Accessories")){
                access.add(product);
                pos = fireBase.getProducts().indexOf(product);
                accesskeys.add(fireBase.getProductskeys().get(pos));
            }
            else if(product.getType().equals("mobilePhone")){
                phone.add(product);
                pos = fireBase.getProducts().indexOf(product);
                phonekeys.add(fireBase.getProductskeys().get(pos));
            }
        }
        phonebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ComputerPage(fireBase,phone,phonekeys);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.view_pager, fragment);
                fragmentTransaction.addToBackStack("1");
                fragmentTransaction.commit();
                po = 1 ;
            }
        });
        compubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ComputerPage(fireBase,computer,computerkeys);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.view_pager, fragment);
                fragmentTransaction.addToBackStack(null);
                po = 1 ;
                fragmentTransaction.commit();
            }
        });
        accbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ComputerPage(fireBase,access,accesskeys);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.view_pager, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                po = 1 ;
            }
        });
        Random random = new Random();
        TextView name1 = view.findViewById(R.id.getName);
        TextView name2 = view.findViewById(R.id.getName1);
        TextView name3 = view.findViewById(R.id.getName2);
        TextView price1 = view.findViewById(R.id.getPhone);
        TextView price2 = view.findViewById(R.id.getPhone1);
        TextView price3 = view.findViewById(R.id.getPhone2);
        ImageView img1 = view.findViewById(R.id.productimg1);
        ImageView img2 = view.findViewById(R.id.productimg2);
        ImageView img3 = view.findViewById(R.id.productimg3);
        int i,i1,i2,i3,i4,i5,i6,i7;
        do {
             i = random.nextInt(fireBase.getProducts().size());
             i1 = random.nextInt(fireBase.getProducts().size());
             i2 = random.nextInt(fireBase.getProducts().size());
        }while(i == i1 || i == i2 || i1 == i2 );
        name1.setText(fireBase.getProducts().get(i).getName());
        name2.setText(fireBase.getProducts().get(i1).getName());
        name3.setText(fireBase.getProducts().get(i2).getName());
        price1.setText(fireBase.getProducts().get(i).getPrice());
        price2.setText(fireBase.getProducts().get(i1).getPrice());
        price3.setText(fireBase.getProducts().get(i2).getPrice());
        Picasso.get().load(fireBase.getProducts().get(i).getPhoto()).into(img1);
        Picasso.get().load(fireBase.getProducts().get(i1).getPhoto()).into(img2);
        Picasso.get().load(fireBase.getProducts().get(i2).getPhoto()).into(img3);
        do {
            i3 = random.nextInt(fireBase.getProducts().size());
            i4 = random.nextInt(fireBase.getProducts().size());
            i5 = random.nextInt(fireBase.getProducts().size());
            i6 = random.nextInt(fireBase.getProducts().size());
        }while(i3 == i4 || i4 == i5 || i5 == i6 );

        ImageView lv = view.findViewById(R.id.imageView1);
        ImageView lv1 = view.findViewById(R.id.imageView2);
        ImageView lv2 = view.findViewById(R.id.imageView3);
        ImageView lv3 = view.findViewById(R.id.imageView4);

        Picasso.get().load(fireBase.getProducts().get(i3).getPhoto()).into(lv);
        Picasso.get().load(fireBase.getProducts().get(i4).getPhoto()).into(lv1);
        Picasso.get().load(fireBase.getProducts().get(i5).getPhoto()).into(lv2);
        Picasso.get().load(fireBase.getProducts().get(i6).getPhoto()).into(lv3);

        return view;
    }
}