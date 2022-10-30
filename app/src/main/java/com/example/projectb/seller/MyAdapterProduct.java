package com.example.projectb.seller;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterProduct extends BaseAdapter {
    private ArrayList <String> ProductName    = new ArrayList<String>();
    private ArrayList <String> ProductPrice   = new ArrayList<String>();
    private ArrayList <String> ProductImage   = new ArrayList<String>();
    private ArrayList <String> ProductOffer   = new ArrayList<String>();
    private ArrayList <String> ProductDetails = new ArrayList<String>();
    private Context con ;

    MyAdapterProduct(ArrayList <String> ProductName , ArrayList <String> ProductPrice , ArrayList <String> ProductImage , ArrayList <String> ProductOffer , ArrayList <String> ProductDetails , Context con ){
        this.ProductName    = ProductName  ;
        this.ProductPrice   = ProductPrice ;
        this.ProductImage   = ProductImage ;
        this.ProductOffer   = ProductOffer ;
        this.ProductDetails = ProductDetails ;
        this.con = con ;
    }

    @Override
    public int getCount() {
        return ProductName.size() ;
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
            convertView = LayoutInflater.from(con).inflate(R.layout.product_card, parent , false);
        }
        TextView x = convertView.findViewById(R.id.ProductName);
        x.setText(ProductName.get(position));

        TextView y = convertView.findViewById(R.id.ProductDetalis);
        y.setText(ProductDetails.get(position));

        TextView z = convertView.findViewById(R.id.ProductPrice);
        z.setText(ProductPrice.get(position) + " EGP");

        TextView w = convertView.findViewById(R.id.ProductOffer);
        if(ProductOffer.get(position).equals("0")){
            w.setText("No Sale");
        }
        else {
            w.setText(ProductOffer.get(position) + "%");
        }

        TextView t = convertView.findViewById(R.id.ProductPriceAfterSale);
        if(Integer.parseInt(ProductOffer.get(position)) > 0) {
            int oldPrice = Integer.parseInt(ProductPrice.get(position));
            int offer = Integer.parseInt(ProductOffer.get(position) );
            t.setText(""+  (oldPrice - ((oldPrice*offer)/100))  + " EGP" );
            z.setTextColor(Color.RED);
        }
        else{
            t.setText("No Sale");
            z.setPaintFlags(z.getPaintFlags() | Paint.END_HYPHEN_EDIT_INSERT_UCAS_HYPHEN);
            w.setVisibility(View.INVISIBLE);
        }

        ImageView e = convertView.findViewById(R.id.ProductImage);
        Picasso.get().load(ProductImage.get(position)).into(e);

        return convertView ;
    }
}
