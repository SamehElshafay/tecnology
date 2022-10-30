    package com.example.projectb.customerview;

    import android.os.Handler;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentActivity;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;

    import com.example.projectb.R;
    import com.squareup.picasso.Picasso;

    import java.util.ArrayList;

    public class MyAdapter extends BaseAdapter {
        ArrayList<Product> list;
        FragmentActivity context;
        int layout;
        FireBase fireBase;
        String name;
        ArrayList<String> s;

        public MyAdapter(ArrayList<Product> list, FragmentActivity context, int layout, FireBase fireBase, ArrayList<String> s) {
            this.list = list;
            this.context = context;
            this.layout = layout;
            this.fireBase = fireBase;
            name = fireBase.getCustomers().get(fireBase.getPosition()).getName();
            this.s = s;
        }

        @Override
        public int getCount() {
        return list.size();
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
            if(convertView == null)
                convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            TextView x = convertView.findViewById(R.id.getName);
            x.setText(list.get(position).getName());
            Button fav = convertView.findViewById(R.id.favbtn);
            if (fireBase.getFavproductskeys().contains(s.get(position))) {
                fav.setBackgroundResource(R.drawable.hfav2);
            } else
                fav.setBackgroundResource(R.drawable.hfav);
            TextView y = convertView.findViewById(R.id.getPhone);
            y.setText(list.get(position).getPrice());
            ImageView e = convertView.findViewById(R.id.productimg);
            Picasso.get().load(list.get(position).getPhoto()).into(e);
            LinearLayout linearLayout = convertView.findViewById(R.id.linearLayout);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new DetailPage(fireBase,position,list,s);
                    FragmentManager fragmentManager = context.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.view_pager, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!fireBase.getFavproductskeys().contains(s.get(position))) {
                        fireBase.setfavproduct(s.get(position));
                        fav.setBackgroundResource(R.drawable.hfav2);
                    } else {
                        fav.setBackgroundResource(R.drawable.hfav);
                        fireBase.removefavProduct(s.get(position));
                        if(s == fireBase.getFavproductskeys()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Fragment fragment = new FavouritePage(fireBase);
                                    context.getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, fragment).commit();
                                }
                            }, 1);
                        }

                    }
                }

            });

            return convertView;
        }
}
