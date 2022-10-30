package com.example.projectb.customerview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.projectb.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation meowBottomNavigation;
    public int position;
    FireBase fireBase;
    int counter = -1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainc);
        Intent intent = getIntent() ;
        String name = intent.getStringExtra("1") ;
        fireBase = new FireBase(this);
        Handler h = new  Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<User> csts = fireBase.getCustomers();
                for (User user : csts){
                    if(user.getName().equals(name))
                        position = csts.indexOf(user);
                }
                fireBase.setPosition(position);
                navigationBar(fireBase);
            }
        },5000);

}

void navigationBar(FireBase fireBase){
    meowBottomNavigation = findViewById(R.id.bottom_navigation);
    meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.wallet));
    meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.home));
    meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.fav));
    meowBottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.user));
    meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
        @Override
        public void onShowItem(MeowBottomNavigation.Model item) {
            Fragment fragment = null;
            if(item.getId() == 1    ){
                fragment = new BalancePage(fireBase);
            }
            else if(item.getId() == 2){
                fragment = new HomePage(fireBase);
            }
            else if(item.getId() == 3){
                fragment = new FavouritePage(fireBase);
            }
            else if(item.getId() == 4){
                fragment = new ProfilePage(fireBase);
            }
            counter = item.getId() ;
            getSupportFragmentManager().beginTransaction().replace(R.id.view_pager,fragment).commit();
        }
    });
    meowBottomNavigation.show(2,true);
    meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
        @Override
        public void onClickItem(MeowBottomNavigation.Model item) {
        }
    });
    meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
        @Override
        public void onReselectItem(MeowBottomNavigation.Model item) {
            Fragment fragment = null;
            if(item.getId() == 1){
                fragment = new BalancePage(fireBase);
            }
            else if(item.getId() ==2){
                fragment = new HomePage(fireBase);
            }
            else if(item.getId() == 3){
                fragment = new FavouritePage(fireBase);
            }
            else if(item.getId() == 4){
                fragment = new ProfilePage(fireBase);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.view_pager,fragment).commit();
        }
    });
}

void setFragment(Fragment fragment){
    getSupportFragmentManager().beginTransaction().replace(R.id.view_pager,fragment).commit();
}

    @Override
    public void onBackPressed() {
        if(counter == 2 && HomePage.po == 1 ){
            HomePage.po = 0 ;
            getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new HomePage(fireBase)).addToBackStack(null).commit();
            meowBottomNavigation.show(2, true);
        }
        else if(counter == 2){
            System.exit(0);
        }
        else if(counter == 1 || counter == 3 || counter == 4) {
            getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new HomePage(fireBase)).addToBackStack(null).commit();
            meowBottomNavigation.show(2, true);
        }
    }
}