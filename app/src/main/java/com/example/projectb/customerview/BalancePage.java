package com.example.projectb.customerview;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.projectb.R;

public class BalancePage extends Fragment {

    Uri filePath;
    ImageView cstimg ;

    public BalancePage(FireBase fb) {
        // Required empty public constructor
        this.firebase = fb;
    }
    FireBase firebase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.balance, container, false);
        return view;
    }
}