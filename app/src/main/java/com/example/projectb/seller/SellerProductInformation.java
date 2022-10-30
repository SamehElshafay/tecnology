package com.example.projectb.seller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectb.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SellerProductInformation extends AppCompatActivity {
    public Uri filePath ;
    private FirebaseStorage storageReference = FirebaseStorage.getInstance();
    private FirebaseDatabase db = FirebaseDatabase.getInstance() ;
    private DatabaseReference x = db.getReference("contact") ;
    private StorageReference storageReference1 = storageReference.getReference();
    private StorageReference ref ;
    private UploadTask ut ;

    String productName  = Product.productName  ;
    String productPrice = Product.productPrice ;
    String productPhoto = Product.productPhoto ;
    String OldUri       = productPhoto ;
    String productSale  = Product.productSale  ;
    String productType  = Product.productType  ;
    String productState = Product.productState ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_information);

        TextView product_name = findViewById(R.id.ProductName);
        product_name.setText(productName);

        TextView product_Detalis = findViewById(R.id.ProductDetalis);
        product_Detalis.setText(productState);

        TextView product_Price = findViewById(R.id.ProductPrice);
        product_Price.setText(productPrice + " EGP");

        TextView PROD = findViewById(R.id.productType);
        PROD.setText(productType);

        TextView product_Offer = findViewById(R.id.ProductOffer);
        if(productSale.equals("0")){
            product_Offer.setText("No Sale");
        }
        else {
            product_Offer.setText(productSale + "%");
        }

        TextView ProductPriceAfterSale = findViewById(R.id.ProductPriceAfterSale);
        if(Integer.parseInt(productSale) > 0) {
            int oldPrice = Integer.parseInt(productPrice);
            int offer = Integer.parseInt(productSale);
            ProductPriceAfterSale.setText(""+  (oldPrice - ((oldPrice*offer)/100))  + " EGP" );
            product_Price.setTextColor(Color.RED);
            product_Price.setPaintFlags(ProductPriceAfterSale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            ProductPriceAfterSale.setText("No Sale");
            product_Offer.setVisibility(View.INVISIBLE);
        }

        ImageView product_Photo = findViewById(R.id.ProductImageS);
        Picasso.get().load(productPhoto).into(product_Photo);


        DialogEdit DE = new DialogEdit(this , "Product");

        product_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeildForProduct(Product.productKEy , "name" , "Your New Product Name").show();
            }
        });

        product_Detalis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeildForProduct(Product.productKEy , "state" , "Your New Product Description").show();
            }
        });

        product_Price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeildForProduct(Product.productKEy , "price" , "Your New Product Price").show();
            }
        });

        product_Offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DE.EditFeildForProduct(Product.productKEy , "sale" , "Your New Product Sale").show();
            }
        });

        product_Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
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
                                        x.child("Product").child(Product.productKEy).child("photo").setValue(uri.toString());
                                        Picasso.get().load(uri).into(((ImageView) findViewById(R.id.ProductImageS))) ;
                                        StorageReference desertRef = storageReference.getReferenceFromUrl(OldUri);
                                        desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                productPhoto = uri.toString() ;
                                                OldUri = uri.toString() ;
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception exception) {
                                                // Uh-oh, an error occurred!
                                            }
                                        });
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