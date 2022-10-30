package com.example.projectb.customerview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.projectb.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class ProfilePage extends Fragment {

    Uri filePath;
    ImageView cstimg ;

    public ProfilePage(FireBase fb) {
        // Required empty public constructor
        this.firebase = fb;
    }
    FireBase firebase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main4, container, false);
        cstimg = view.findViewById(R.id.cstimg);
        Button upload = view.findViewById(R.id.upload);
        Button editname = view.findViewById(R.id.editname);
        Button editusername = view.findViewById(R.id.editusername);
        Button editphone = view.findViewById(R.id.editphone);
        Button editpassword = view.findViewById(R.id.editpassword);
        TextView cstname = view.findViewById(R.id.cstname);
        TextView cstusername = view.findViewById(R.id.cstusername);
        TextView cstphone = view.findViewById(R.id.cstphone);
        TextView cstpassword = view.findViewById(R.id.cstpassword);
        cstname.setText(firebase.getCustomers().get(firebase.getPosition()).getName());
        cstusername.setText(firebase.getCustomers().get(firebase.getPosition()).getUsername());
        cstphone.setText(firebase.getCustomers().get(firebase.getPosition()).getPhone());
        cstpassword.setText(firebase.getCustomers().get(firebase.getPosition()).getPassword());
        Picasso.get().load(firebase.getCustomers().get(firebase.getPosition()).getPhoto()).into(cstimg);
        editname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit");
                final View view = View.inflate(getActivity(), R.layout.dialog, null);
                EditText name = view.findViewById(R.id.edit);
                builder.setView(view);
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.getCustomers().get(firebase.getPosition()).setName(name.getText().toString());
                        User user = firebase.getCustomers().get(firebase.getPosition());
                        firebase.modifyCustomer(firebase.getCstskeys().get(firebase.getPosition()),user);
                        dialog.cancel();
                    }

                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.show();
            }
        });
        editusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit");
                final View view = View.inflate(getActivity(), R.layout.dialog, null);
                EditText name = view.findViewById(R.id.edit);
                builder.setView(view);
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.getCustomers().get(firebase.getPosition()).setUsername(name.getText().toString());
                        User user = firebase.getCustomers().get(firebase.getPosition());
                        firebase.modifyCustomer(firebase.getCstskeys().get(firebase.getPosition()),user);
                        dialog.cancel();
                    }

                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.show();
            }
        });
        editphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit");
                final View view = View.inflate(getActivity(), R.layout.dialog, null);
                EditText name = view.findViewById(R.id.edit);
                builder.setView(view);
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.getCustomers().get(firebase.getPosition()).setPhone(name.getText().toString());
                        User user = firebase.getCustomers().get(firebase.getPosition());
                        firebase.modifyCustomer(firebase.getCstskeys().get(firebase.getPosition()),user);
                        dialog.cancel();
                    }

                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.show();
            }
        });
        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit");
                final View view = View.inflate(getActivity(), R.layout.dialog, null);
                EditText name = view.findViewById(R.id.edit);
                name.setText(firebase.getCustomers().get(firebase.getPosition()).getPassword());
                builder.setView(view);
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.getCustomers().get(firebase.getPosition()).setPassword(name.getText().toString());
                        User user = firebase.getCustomers().get(firebase.getPosition());
                        firebase.modifyCustomer(firebase.getCstskeys().get(firebase.getPosition()),user);
                        dialog.cancel();
                    }

                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.show();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });
        return view;
    }
    public void selectImage() {
        Intent intent = new Intent();
        Intent x =Intent.createChooser(intent, "Select Image from here...");
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        someActivityResultLauncher.launch(x);



    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        filePath = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                            cstimg.setImageBitmap(bitmap);
                            firebase.uploadImage(filePath);

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}