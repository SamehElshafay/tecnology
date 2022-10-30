package com.example.projectb.customerview;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class FireBase {

    private FirebaseStorage storageReference;
    private DatabaseReference x;       private StorageReference storageReference1;
    private StorageReference ref;      private ArrayList<User> csts;
    private Context context;           private ArrayList<User> admins;
    private UploadTask ut;             private ArrayList<Product> products;
    private ArrayList<String> productskeys;
    private ArrayList<FavProduct> favproducts;
    private ArrayList<String> favproductskeys;
    private ArrayList<String> cstskeys;
    private ArrayList<String> adminskeys;
    private FirebaseDatabase db;       private ArrayList<User> users;
    public String Turi;
    public int position;

    public FireBase(Context mcontext) {
        db = FirebaseDatabase.getInstance();
        x = db.getReference("contact");
        storageReference = FirebaseStorage.getInstance();
        storageReference1 = storageReference.getReference();
        users = new ArrayList<>();
        csts = new ArrayList<>();
        admins = new ArrayList<>();
        products = new ArrayList<>();
        cstskeys = new ArrayList<>();
        adminskeys = new ArrayList<>();
        favproducts = new ArrayList<>();
        context = mcontext;
        favproductskeys = new ArrayList<>();
        productskeys = new ArrayList<>();
        getProduct();
        getCustomer();
        getfavproduct();
    }

    public void setFavproducts(ArrayList<FavProduct> favproducts) {
        this.favproducts = favproducts;
    }

    public void setFavproductskeys(ArrayList<String> favproductskeys) {
        this.favproductskeys = favproductskeys;
    }

    public ArrayList<FavProduct> getFavproducts() {
        return favproducts;
    }

    public ArrayList<String> getFavproductskeys() {
        return favproductskeys;
    }

    public ArrayList<String> getProductskeys() {
        return productskeys;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private void getCustomer(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.child("Customer").getChildren()) {
                        User cst = singleSnapshot.getValue(User.class);
                    cstskeys.add(singleSnapshot.getKey());
                    csts.add(cst);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAdmin(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                admins.clear();
                for (DataSnapshot singleSnapshot : snapshot.child("Admin").getChildren()) {
                    User admin = singleSnapshot.getValue(User.class);
                    adminskeys.add(singleSnapshot.getKey());
                    admins.add(admin);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getProduct(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                products.clear();;
                productskeys.clear();
                for (DataSnapshot singleSnapshot : snapshot.child("Product").getChildren()) {
                    Product product = singleSnapshot.getValue(Product.class);
                    productskeys.add(singleSnapshot.getKey());
                    products.add(product);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public FirebaseStorage getStorageReference() {
        return storageReference;
    }

    public DatabaseReference getX() {
        return x;
    }

    public StorageReference getStorageReference1() {
        return storageReference1;
    }

    public StorageReference getRef() {
        return ref;
    }

    public ArrayList<User> getCsts() {
        return csts;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<String> getCstskeys() {
        return cstskeys;
    }

    public ArrayList<String> getAdminskeys() {
        return adminskeys;
    }

    public FirebaseDatabase getDb() {
        return db;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getTuri() {
        return Turi;
    }

    private String generateKey() {
        return x.push().getKey();
    }

    public ArrayList<User> getSellers() {
        return users;
    }

    public ArrayList<User> getCustomers() {
        return csts;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public void setSeller(String Name,String UName, String password, String phone, String location, String state,String product) {
        String key = generateKey();
        User user = new User(Name,UName,password,phone,Turi,location,state,product,null);
        x.child("Seller").child(key).setValue(user);
    }

    public void setCustomer(String Name, String UName,String password, String phone, String state, String fav) {
        String key = generateKey();
        User cst = new User(Name,UName,password,phone,Turi,null,state,null,fav);
        x.child("Customer").child(key).setValue(cst);
    }

    public void modifyCustomer(String key, User user) {
        x.child("Customer").child(key).setValue(user);
    }

    public void setAdmin(String Name, String password) {
        String key = generateKey();
        User admn = new User(Name,null,password,null,null,null,null,null,null   );
        x.child("Admin").child(key).setValue(admn);
    }

    public void setProduct(String name, String price, String description, String storage, String ram, String rating, String fav, String soldby) {
        String key = generateKey();
        Product prdct = new Product(name,price,Turi,description,storage,ram,rating,fav,soldby);
        x.child("Product").child(key).setValue(prdct);
    }

    public void modifyProduct(String key,Product product) {
        x.child("Product").child(key).setValue(product);
    }

    public void removeCustomer(int pos){
        x.child("Customer").child(getCstskeys().get(pos)).removeValue();
    }

    public void removeAdmin(int pos){
        x.child("Admin").child(getAdminskeys().get(pos)).removeValue();
    }

    public void removeProduct(int pos){
        x.child("Product").child(getProductskeys().get(pos)).removeValue();
    }

    public void removefavProduct(String pos){
        x.child("Favourite Products").child(getCustomers().get(position).getName()).child(pos).removeValue();
    }

    public void uploadImage(Uri filePath) {
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
                    Toast.makeText(context, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Turi = uri.toString();
                            getCustomers().get(getPosition()).setPhoto(getTuri());
                            User user = getCustomers().get(getPosition());
                            modifyCustomer(getCstskeys().get(getPosition()),user);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                            Toast.makeText(context, "Image couldn't Uploaded!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }

    }

    private void getfavproduct(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favproducts.clear();;
                favproductskeys.clear();
                for (DataSnapshot singleSnapshot : snapshot.child("Favourite Products").child(getCustomers().get(position).getName()).getChildren()) {
                    FavProduct product = singleSnapshot.getValue(FavProduct.class);
                    favproductskeys.add(singleSnapshot.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setfavproduct(String Key) {
        String key = generateKey();
        FavProduct prdct = new FavProduct(Key);
        x.child("Favourite Products").child(getCustomers().get(position).getName()).child(Key).setValue(prdct);
    }

}

