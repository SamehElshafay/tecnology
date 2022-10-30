package com.example.projectb;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.*;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class FireBase {
    private FirebaseStorage storageReference;
    private DatabaseReference x;       private StorageReference storageReference1;
    private StorageReference ref;      private FirebaseStorage storage;
    private Context context;           private Uri filePath;
    private UploadTask ut;             private FirebaseDatabase db ;
    private String VALUE ;
    private String Turi = "https://firebasestorage.googleapis.com/v0/b/nologyclick2021-350ac.appspot.com/o/images%2Fimage%3A27859?alt=media&token=d18564a7-c526-4cf1-8d61-c8d04c26d47e";
    private ArrayList<String> productsKEYS          = new ArrayList<String>() ;
    private ArrayList<String> sellerNames           = new ArrayList<String>() ;
    private ArrayList<String> sellerPhones          = new ArrayList<String>() ;
    private ArrayList<String> sellerPassword        = new ArrayList<String>() ;
    private ArrayList<String> sellerProductIdSeller = new ArrayList<String>() ;
    private ArrayList<String> sellerRate            = new ArrayList<String>() ;
    private ArrayList<String> sellerLocation        = new ArrayList<String>() ;
    private ArrayList<String> sellerState           = new ArrayList<String>() ;
    private ArrayList<String> sellerkeys            = new ArrayList<String>() ;
    private ArrayList<String> sellerPhotos          = new ArrayList<String>() ;
    private ArrayList<String> sellerUNames          = new ArrayList<String>() ;
    private ArrayList<String> customerNames         = new ArrayList<String>() ;
    private ArrayList<String> customerPhones        = new ArrayList<String>() ;
    private ArrayList<String> customerPhotos        = new ArrayList<String>() ;
    private ArrayList<String> customerkeys          = new ArrayList<String>() ;
    private ArrayList<String> customerUNames        = new ArrayList<String>() ;
    private ArrayList<String> customerState         = new ArrayList<String>() ;
    private ArrayList<String> customerPassword      = new ArrayList<String>() ;
    private ArrayList<String> productNames          = new ArrayList<String>() ;
    private ArrayList<String> productPhotos         = new ArrayList<String>() ;
    private ArrayList<String> productPrice          = new ArrayList<String>() ;
    private ArrayList<String> productState          = new ArrayList<String>() ;
    private ArrayList<String> productType           = new ArrayList<String>() ;
    private ArrayList<String> productSale           = new ArrayList<String>() ;
    private ArrayList<String> productkeys           = new ArrayList<String>() ;
    private ArrayList<String> adminUNames           = new ArrayList<String>() ;
    private ArrayList<String> adminPassword         = new ArrayList<String>() ;
    private ArrayList<String> adminKeys             = new ArrayList<String>() ;
    private ArrayList<String> messagesKeys          = new ArrayList<String>() ;
    private ArrayList<String> messagesDate          = new ArrayList<String>() ;


    public ArrayList<String> getSellerNames() {
        return sellerNames;
    }

    public ArrayList<String> getSellerPhones() {
        return sellerPhones;
    }

    public ArrayList<String> getSellerPhotos() {
        return sellerPhotos;
    }

    public ArrayList<String> getSellerUNames() {
        return sellerUNames;
    }

    public ArrayList<String> getSellerPassword() {
        return sellerPassword;
    }

    public ArrayList<String> getSellerkeys() {
        return sellerkeys;
    }

    public ArrayList<String> getSellerProductIdSeller() {
        return sellerProductIdSeller;
    }

    public ArrayList<String> getSellerRate() {
        return sellerRate;
    }

    public ArrayList<String> getSellerLocation() {
        return sellerLocation;
    }

    public ArrayList<String> getSellerState() {
        return sellerState;
    }

    public ArrayList<String> getSellergetSellerkeys() {
        return sellerkeys;
    }

    public ArrayList<String> getProductsKEYSFromSeller() {
        return productsKEYS ;
    }


    public ArrayList<String> getCustomerNames() {
        return customerNames;
    }

    public ArrayList<String> getCustomerPhones() {
        return customerPhones;
    }

    public ArrayList<String> getCustomerPhotos() {
        return customerPhotos;
    }

    public ArrayList<String> getCustomerUNames() {
        return customerUNames;
    }

    public ArrayList<String> getCustomerPassword() {
        return customerPassword;
    }

    public ArrayList<String> getCustomerState() {
        return customerState;
    }

    public ArrayList<String> getCustomerkeys() {
        return customerkeys;
    }

    public ArrayList<String> getProductNames() {
        return productNames;
    }

    public ArrayList<String> getProductPhotos() {
        return productPhotos;
    }

    public ArrayList<String> getProductType() {
        return productType;
    }

    public ArrayList<String> getProductPrice() {
        return productPrice;
    }

    public ArrayList<String> getProductSale() {
        return productSale;
    }

    public ArrayList<String> getProductState() {
        return productState;
    }

    public ArrayList<String> getProductkeys() {
        return productkeys;
    }

    public ArrayList<String> getAdminUNames() {
        return adminUNames;
    }

    public ArrayList<String> getAdminPassword() {
        return adminPassword;
    }

    public ArrayList<String> getAdminKeys() {
        return adminKeys ;
    }

    public ArrayList<String> getProductsKEYS(){
        return productsKEYS ;
    }

    public FireBase(Context cm){
        context = cm ;
        db = FirebaseDatabase.getInstance();
        x = db.getReference("contact");
        storageReference = FirebaseStorage.getInstance();
        storageReference1 = storageReference.getReference();
    }

    public void getSeller(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sellerNames   .clear() ;
                sellerPhones  .clear() ;
                sellerPhotos  .clear() ;
                sellerUNames  .clear() ;
                sellerPassword.clear() ;
                sellerLocation.clear() ;
                sellerState   .clear() ;
                productsKEYS  .clear() ;
                sellerkeys    .clear() ;
                messagesKeys  .clear() ;
                messagesDate  .clear() ;
                for (DataSnapshot singleSnapshot : snapshot.child("Seller").getChildren()) {
                    try {
                        sellerNames.add(singleSnapshot.child("name").getValue().toString());
                        sellerPhones.add(singleSnapshot.child("phone").getValue().toString());
                        sellerPhotos.add(singleSnapshot.child("photo").getValue().toString());
                        sellerUNames.add(singleSnapshot.child("username").getValue().toString());
                        sellerPassword.add(singleSnapshot.child("password").getValue().toString());
                        sellerLocation.add(singleSnapshot.child("location").getValue().toString());
                        sellerState.add(singleSnapshot.child("state").getValue().toString());
                        productsKEYS.add(singleSnapshot.child("product").getValue().toString()) ;
                        messagesKeys.add(singleSnapshot.child("messagesKeys").getValue().toString()) ;
                        messagesDate.add(singleSnapshot.child("messagesDate").getValue().toString());
                        sellerkeys.add(singleSnapshot.getKey());
                    }catch (Exception ex){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(context, "Can't Load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String GETVALUE(){
        return VALUE ;
    }

    public String getSellerAttribute(String KEY , String Atribute){
        final String[] Value = new String[1];
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Value[0] =  snapshot.child("Seller").child(KEY).child(Atribute).getValue().toString() ;
                VALUE = Value[0] ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(context, "Can't Load", Toast.LENGTH_SHORT).show();
            }
        });
        return Value[0] ;
    }


    public String getSellerKeysProduct(String Key){
        final String[] ke = {" "};
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.child("Seller").getChildren()) {
                    try {
                        if(Key.equals(singleSnapshot.getKey())) {
                            System.out.println("================================================");
                            ke[0] = singleSnapshot.child("product").getValue().toString();
                        }
                    }catch (Exception ex){
                        Toast.makeText(context,ex.toString() , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(context, "Can't Load", Toast.LENGTH_SHORT).show();
            }
        });
        return ke[0];
    }

    public void getCustomer(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerNames   .clear() ;
                customerPhones  .clear() ;
                customerPhotos  .clear() ;
                customerUNames  .clear() ;
                customerPassword.clear() ;
                customerState   .clear() ;
                customerkeys    .clear() ;
                messagesDate    .clear() ;
                try {
                    for (DataSnapshot singleSnapshot : snapshot.child("Customer").getChildren()) {
                        customerNames   .add(singleSnapshot.child("name")    .getValue().toString());
                        customerPhones  .add(singleSnapshot.child("phone")   .getValue().toString());
                        customerPhotos  .add(singleSnapshot.child("photo")   .getValue().toString());
                        customerUNames  .add(singleSnapshot.child("username").getValue().toString());
                        customerPassword.add(singleSnapshot.child("password").getValue().toString());
                        customerState   .add(singleSnapshot.child("state")   .getValue().toString());
                        messagesKeys    .add(singleSnapshot.child("messagesKeys").getValue().toString());
                        messagesDate    .add(singleSnapshot.child("messagesDate").getValue().toString());
                        customerkeys    .add(singleSnapshot.getKey());
                    }
                }catch (Exception ex){}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(context, "Can't Load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAdmin(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminUNames.clear() ;
                adminPassword.clear() ;
                adminKeys.clear() ;
                try {
                    for (DataSnapshot singleSnapshot : snapshot.child("Admin").getChildren()) {
                        adminUNames.add(singleSnapshot.child("username").getValue().toString());
                        adminPassword.add(singleSnapshot.child("password").getValue().toString());
                        adminKeys.add(singleSnapshot.getKey());
                    }
                }catch (Exception ex){ }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getProduct(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productNames .clear();
                productPhotos.clear();
                productPrice .clear();
                productSale  .clear();
                productState .clear();
                productType  .clear();
                for (DataSnapshot singleSnapshot : snapshot.child("Product").getChildren()) {
                    productNames.add (singleSnapshot.child("name").getValue().toString()) ;
                    productPhotos.add(singleSnapshot.child("photo").getValue().toString());
                    productPrice.add (singleSnapshot.child("price").getValue().toString());
                    productSale.add  (singleSnapshot.child("sale").getValue().toString());
                    productState.add (singleSnapshot.child("state").getValue().toString());
                    productType.add  (singleSnapshot.child("type").getValue().toString()) ;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSellerProduct(ArrayList <String> KEY){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productNames .clear();
                productPhotos.clear();
                productPrice .clear();
                productSale  .clear();
                productState .clear();
                productType  .clear();
                for (DataSnapshot singleSnapshot : snapshot.child("Product").getChildren()) {
                    if(KEY.contains(singleSnapshot.getKey())) {
                        productNames.add (singleSnapshot.child("name").getValue().toString() ) ;
                        productPhotos.add(singleSnapshot.child("photo").getValue().toString()) ;
                        productPrice.add (singleSnapshot.child("price").getValue().toString()) ;
                        productSale.add  (singleSnapshot.child("sale").getValue().toString() ) ;
                        productState.add (singleSnapshot.child("state").getValue().toString()) ;
                        productType.add  (singleSnapshot.child("type").getValue().toString() ) ;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String generateKey() {
        String key = x.push().getKey();
        return key;
    }

    public void setSeller(String Name,String UName, String password, String phone, String location, String state) {
        String key = generateKey();
        x.child("Seller").child(key).child("name").setValue(Name) ;
        x.child("Seller").child(key).child("username").setValue(UName) ;
        x.child("Seller").child(key).child("password").setValue(password) ;
        x.child("Seller").child(key).child("phone").setValue(phone) ;
        x.child("Seller").child(key).child("photo").setValue(Turi) ;
        x.child("Seller").child(key).child("location").setValue("your location&(0,0)") ;
        x.child("Seller").child(key).child("state").setValue(state) ;
        x.child("Seller").child(key).child("messagesKeys").setValue("") ;
        x.child("Seller").child(key).child("messagesDate").setValue("") ;
        x.child("Seller").child(key).child("product").setValue("") ;
    }

    public void setCustomer(String Name, String UserName ,String password, String phone , String state) {
        String key = generateKey();
        x.child("Customer").child(key).child("name").setValue(Name);
        x.child("Customer").child(key).child("username").setValue(UserName);
        x.child("Customer").child(key).child("password").setValue(password);
        x.child("Customer").child(key).child("phone").setValue(phone);
        x.child("Customer").child(key).child("state").setValue(state);
        x.child("Customer").child(key).child("photo").setValue(Turi);
        x.child("Customer").child(key).child("messagesKeys").setValue("");
        x.child("Customer").child(key).child("messagesDate").setValue("");
    }

    public void setCustomerState(String KEY, String STATE ) {
        x.child("Customer").child(KEY).child("state").setValue(STATE);
    }

    public void setSellerState(String KEY, String STATE ) {
        x.child("Seller").child(KEY).child("state").setValue(STATE);
    }

    public void setSellerProductKey(String SellerKEY , String NewProductKey , String oldProducctKey ) {
        x.child("Seller").child(SellerKEY).child("product").setValue(oldProducctKey + " " + NewProductKey);
    }


    public void setAdmin(String Name, String password) {
        String key = generateKey();
        x.child("Admin").child(key).child("username").setValue(Name);
        x.child("Admin").child(key).child("password").setValue(password);
    }


    public String setProduct(String Name, String price, String sale, String type, String state , String uri) {
        String key = generateKey();
        x.child("Product").child(key).child("name").setValue(Name);
        x.child("Product").child(key).child("price").setValue(price);
        x.child("Product").child(key).child("photo").setValue(uri);
        x.child("Product").child(key).child("type").setValue(type);
        x.child("Product").child(key).child("sale").setValue(sale);
        x.child("Product").child(key).child("state").setValue(state);
        return key ;
    }


    public void UploadProductWithImage( String SellerKey , String oldProductKey ,  Uri filePath , String name , String price , String sale , String description , String type ) {
        if (filePath != null) {
            ref = storageReference1.child("images/" + filePath.getLastPathSegment());
            ut = ref.putFile(filePath);
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            ut.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Product Uploaded!!", Toast.LENGTH_SHORT).show();
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String newProductKey = setProduct(name , price , sale , type , description , uri.toString());
                            setSellerProductKey( SellerKey , newProductKey , oldProductKey );
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                            //Toast.makeText(context, "Image couldn't Uploaded!!", Toast.LENGTH_SHORT).show();
                        }
                    });
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

