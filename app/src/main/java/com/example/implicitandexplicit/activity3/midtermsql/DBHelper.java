package com.example.implicitandexplicit.activity3.midtermsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {

        super(context, "Product.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table ProductTable(prodId TEXT primary key, prodName TEXT, prodDesc TEXT, price DECIMAL(10, 2), quantity INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {

        DB.execSQL("drop Table if exists ProductTable");
    }

    public Boolean insertProductData(String prodId, String prodName, String prodDesc, float price, int quantity) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("prodId", prodId);
        contentValues.put("prodName", prodName);
        contentValues.put("prodDesc", prodDesc);
        contentValues.put("price", price);
        contentValues.put("quantity", quantity);
        long result=DB.insert("ProductTable", null, contentValues);
        if(result==-1) {
            return false;
        } else {
            return true;
        }
    }


}