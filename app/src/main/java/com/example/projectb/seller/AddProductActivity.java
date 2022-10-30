package com.example.projectb.seller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectb.FireBase;
import com.example.projectb.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {
    String sameh ;
    EditText pname, price, sale, describtion;
    Spinner spin;
    Button savePData;
    ImageView img_v;
    public Uri filePath;
    FireBase fb;
    String ProductKeys = Seller_Content.ProductKeys ;
    String Sellerkey = Seller_Content.Sellerkey;
    private FirebaseStorage storageReference = FirebaseStorage.getInstance();
    private FirebaseDatabase db = FirebaseDatabase.getInstance() ;
    private DatabaseReference x = db.getReference("contact") ;
    private StorageReference storageReference1 = storageReference.getReference();
    private StorageReference ref ;
    private UploadTask ut ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        pname       = findViewById(R.id.AddP_name);
        price       = findViewById(R.id.AddP_Price);
        sale        = findViewById(R.id.AddP_Sale);
        describtion = findViewById(R.id.AddP_Description);
        savePData   = findViewById(R.id.Add_img);
        spin        = findViewById(R.id.Typ);



        ArrayList<String> Activty = new ArrayList<String>();
        Activty.add("mobilePhone");
        Activty.add("Computers");
        Activty.add("Accessories");

        MyTools SpinnerS = new MyTools(spin , Activty);
        SpinnerS.Spinner(this);

        savePData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pname.getText().toString().equals("") || price.getText().toString().equals("") || sale.getText().toString().equals("") || describtion.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext() , "fill all fields" , Toast.LENGTH_SHORT).show();
                }
                else {
                    selectImage();
                }
            }
        });

//        pname = findViewById(R.id.AddP_name);
//        price = findViewById(R.id.AddP_Price);
//        sale = findViewById(R.id.AddP_Sale);
//        describtion = findViewById(R.id.AddP_Description);
//        savePData = findViewById(R.id.Add_img);

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
                        ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
                        progressDialog.setTitle("Uploading...");
                        //progressDialog.show();
                        ut.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Product Uploaded!!", Toast.LENGTH_SHORT).show();
                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String keyY = x.push().getKey();
                                        x.child("Product").child(keyY).child("name").setValue(pname.getText().toString());
                                        x.child("Product").child(keyY).child("price").setValue(price.getText().toString());
                                        x.child("Product").child(keyY).child("photo").setValue(uri.toString());
                                        x.child("Product").child(keyY).child("type").setValue(spin.getSelectedItem().toString());
                                        x.child("Product").child(keyY).child("sale").setValue(sale.getText().toString());
                                        x.child("Product").child(keyY).child("state").setValue(describtion.getText().toString());
                                        String newValude = ProductKeys + " " + keyY ;
                                        Seller_Content.ProductKeys = keyY ;
                                        x.child("Seller").child(Sellerkey).child("product").setValue(newValude);
                                        ProductKeys = newValude ;
                                        startActivity(new Intent(getApplicationContext() , Seller_Content.class));
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
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext() , Seller_Content.class));
        overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
    }
}