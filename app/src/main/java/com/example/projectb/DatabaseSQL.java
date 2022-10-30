/**
 * DDl : date defination language
 * ------
 * create database  => X
 * create table
 * drop db    => X
 * drop table
 * truncate table
 * ==========================================
 * Dml: data manipulation language
 * -----
 * add -> insert
 * delete -> delete
 * edit -> update
 * view -> select
 * ==========================================
 * DCl : Data control language
 * -----
 * security
 **/

package com.example.projectb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.Key;

public class DatabaseSQL extends SQLiteOpenHelper {
    Context con ;

    public DatabaseSQL(Context con){
        super( con , "SetSignIn" , null  , 1  );
        this.con = con ;
    }

    Cursor getAll(){
        SQLiteDatabase x = this.getReadableDatabase();
        return x.rawQuery("SELECT * FROM SignIn" ,null) ; /* return type : Cursor */
    }

    public boolean Insert (String name ,  String password , String key , String type , String signedIn){
        try {
            ContentValues data = new ContentValues();
            SQLiteDatabase db = this.getWritableDatabase();
            data.put("UserKey" , key      );
            data.put("UserName", name     );
            data.put("Password", password );
            data.put("Type"    , type     );
            data.put("SignedIn", signedIn );
            db.insert("SignIn", null, data);
            db.close();
            return true ;
        }catch (Exception ex){
            return false ;
        }
    }

    public boolean delete (String key){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(
                    "delete from SignIn where UserKey='" + key + "';"
            );
            return true;
        }catch (Exception ex){
            return false ;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(

                "CREATE TABLE SignIn("            +
                    "UserKey  VARCHAR(2000),"     +
                    "UserName VARCHAR(200) ,"     +
                    "Password VARCHAR(2000),"     +
                    "SignedIN VARCHAR(200) ,"     +
                    "Type     VARCHAR(200)  "     +
                ");"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("");
    }

    public Cursor selectWhere(String key){
        SQLiteDatabase x = this.getReadableDatabase();
        return x.rawQuery("SELECT * FROM SignIn where UserKey= '"+key+"'" ,null) ;
    }

    public void UpdateSinedIn(String key , String flag){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE SignIn SET signedIn = '"+flag+"' "+ "WHERE UserKey= '"+key+"';");
    }

    public void UpdateUserName(String key , String UserName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE SignIn SET UserName = '"+UserName+"' "+ "WHERE UserKey= '"+key+"';");
    }

    public void UpdatePassword(String key , String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE SignIn SET Password = '"+ Password +"' "+ "WHERE UserKey= '"+key+"';");
    }
}
