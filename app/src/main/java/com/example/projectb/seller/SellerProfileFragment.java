package com.example.projectb.seller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projectb.FireBase;
import com.example.projectb.R;
import com.example.projectb.signin_layout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;


public class SellerProfileFragment extends Fragment {
    TextView spname, spusername, sppassword, spphone , spstate;
    static  TextView splocation ;
    ImageView spimage;
    Button openMap;
    String DefultImage   = "https://firebasestorage.googleapis.com/v0/b/nologyclick2021-350ac.appspot.com/o/images%2Fimage%3A27859?alt=media&token=d18564a7-c526-4cf1-8d61-c8d04c26d47e";
    public String OldURI = signin_layout.SELLERPHOTO ;
    public Uri filePath ;
    private FirebaseStorage storageReference = FirebaseStorage.getInstance();
    private FirebaseDatabase db = FirebaseDatabase.getInstance() ;
    private DatabaseReference x = db.getReference("contact") ;
    private StorageReference storageReference1 = storageReference.getReference();
    //private StorageReference desertRef ;

    private StorageReference ref ;
    private UploadTask ut ;
    FireBase fb = new FireBase(getActivity());

    public SellerProfileFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_profile, container, false);
        spname = view.findViewById(R.id.seller_profile_name);
        sppassword = view.findViewById(R.id.seller_profile_pass);
        spphone = view.findViewById(R.id.seller_profile_phone);
        spusername = view.findViewById(R.id.seller_profile_username);
        splocation = view.findViewById(R.id.seller_profile_location);
        spstate = view.findViewById(R.id.seller_profile_Activated);
        spimage = view.findViewById(R.id.Seller_profile_img);

        FirebaseDatabase db = FirebaseDatabase.getInstance()  ;
        DatabaseReference x = db.getReference("contact");
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spname.setText(snapshot.child("Seller").child(Seller_Content.Sellerkey).child("name").getValue().toString()) ;
                spusername.setText(snapshot.child("Seller").child(Seller_Content.Sellerkey).child("username").getValue().toString()) ;
                sppassword.setText(snapshot.child("Seller").child(Seller_Content.Sellerkey).child("password").getValue().toString()) ;
                spphone.setText(snapshot.child("Seller").child(Seller_Content.Sellerkey).child("phone").getValue().toString()) ;
                splocation.setText(snapshot.child("Seller").child(Seller_Content.Sellerkey).child("location").getValue().toString()) ;
                if (snapshot.child("Seller").child(Seller_Content.Sellerkey).child("state").getValue().toString().equals("true")){
                    spstate.setText("Active") ;
                }
                else {
                    spstate.setText("Blocked") ;
                }

                Picasso.get().load(snapshot.child("Seller").child(Seller_Content.Sellerkey).child("photo").getValue().toString()).into(spimage) ;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(context, "Can't Load", Toast.LENGTH_SHORT).show();
            }
        });

        DialogEdit DE = new DialogEdit(getActivity() , "Seller");

        spname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeild(Seller_Content.Sellerkey , "name" , "Your New Name").show(); ;
            }
        });
        spusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeild(Seller_Content.Sellerkey , "username" , "Your New UserName").show(); ;
            }
        });
        sppassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeild(Seller_Content.Sellerkey , "password" , "Your New Password").show();
            }
        });
        spphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeild(Seller_Content.Sellerkey , "phone" , "Your New Phone").show();

            }
        });
        splocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeildAndMap(Seller_Content.Sellerkey , "location" , "Your New location").show();
            }
        });
        spimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        return view;
    }

    public void selectImage() {
        Intent intent = new Intent();
        Intent x = Intent.createChooser(intent, "Select Image from here...");
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
                    Intent data = result.getData();
                    filePath = data.getData();
                    if (filePath != null) {
                        ref = storageReference1.child("images/" + filePath.getLastPathSegment());
                        ut = ref.putFile(filePath);
                        ProgressDialog progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setTitle("Uploading...");
                        //progressDialog.show();
                        ut.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Product Uploaded!!", Toast.LENGTH_SHORT).show();
                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Picasso.get().load(uri).into(spimage) ;
                                        x.child("Seller").child(Seller_Content.Sellerkey).child("photo").setValue(uri.toString());
                                        if(!DefultImage.equals(OldURI)) {
                                            StorageReference desertRef = storageReference.getReferenceFromUrl(OldURI);
                                            desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    OldURI = uri.toString();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception exception) {
                                                    // Uh-oh, an error occurred!
                                                }
                                            });
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {@Override public void onFailure(@NonNull Exception exception) {}});
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage("Uploaded " + (int) progress + "%");
                            }
                        });
                    }
                }
            }
        }
    );
}