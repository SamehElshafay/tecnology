package com.example.projectb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterMessages extends BaseAdapter {
    private ArrayList <String> SellerName = new ArrayList<String>(); ;
    private ArrayList <String> SellerState = new ArrayList<String>(); ;
    private ArrayList <String> SellerImage = new ArrayList<String>(); ;
    private Context con ;

    MyAdapterMessages(ArrayList <String> SellerName , ArrayList <String> SellerState , ArrayList <String> SellerImage , Context con ){
        this.SellerName = SellerName   ;
        this.SellerState = SellerState ;
        this.SellerImage = SellerImage ;
        this.con = con ;
    }

    @Override
    public int getCount() {
        return SellerName.size() ;
    }
    
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(con).inflate(R.layout.seller_card, parent , false);
        }

        TextView x = convertView.findViewById(R.id.SellerName);
        x.setText(SellerName.get(position));
        
        TextView y = convertView.findViewById(R.id.SellerState);
        if(SellerState.get(position).equals("true")) {
            y.setText("Active");
        }else {
            y.setText("Blocked");
        }

        ImageView e = convertView.findViewById(R.id.SellerImage);
        Picasso.get().load(SellerImage.get(position)).into(e);

        return convertView ;
    }
}
