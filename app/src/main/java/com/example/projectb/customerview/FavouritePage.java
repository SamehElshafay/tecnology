package com.example.projectb.customerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.projectb.R;

import java.util.ArrayList;

public class FavouritePage extends Fragment {

    FireBase fireBase;

    public FavouritePage(FireBase fireBase) {
        // Required empty public constructor
        this.fireBase = fireBase;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ListView lv = view.findViewById(R.id.lv3);
        Button back = view.findViewById(R.id.BACKBTN);
        back.setVisibility(View.INVISIBLE);
        ArrayList<Product> products = new ArrayList<>();
        for(int i = 0; i < fireBase.getProductskeys().size(); i++) {
            if(fireBase.getFavproductskeys().contains(fireBase.getProductskeys().get(i))) {
                    products.add(fireBase.getProducts().get(i));
            }
        }
        MyAdapter ma = new MyAdapter(products,getActivity(),R.layout.designcard2, fireBase,fireBase.getFavproductskeys());
        lv.setAdapter(ma);
        return view;
    }
}