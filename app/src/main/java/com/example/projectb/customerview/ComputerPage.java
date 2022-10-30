package com.example.projectb.customerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectb.R;

import java.util.ArrayList;

public class ComputerPage extends Fragment {

    FireBase fireBase;
    ArrayList<Product> products;
    ArrayList<String> productskeys;

    public ComputerPage(FireBase fireBase, ArrayList<Product> products, ArrayList<String> productskeys) {
        // Required empty public constructor
        this.fireBase = fireBase;
        this.products = products;
        this.productskeys = productskeys;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ListView listView = view.findViewById(R.id.lv3);
        MyAdapter myAdapter = new MyAdapter(products,getActivity(),R.layout.designcard2, fireBase,productskeys);
        listView.setAdapter(myAdapter);

        view.findViewById(R.id.BACKBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomePage(fireBase);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.view_pager, fragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

}