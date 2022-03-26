package com.example.implicitandexplicit.activity3.midtermsql;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.implicitandexplicit.activity3.midtermsql.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DB = new DBHelper(this);
        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hasEmptyFields()) {
                    Toast.makeText(MainActivity.this, "Please Fill Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String prodId = binding.etProdId.getText().toString();
                    String prodName = binding.etProdName.getText().toString();
                    String prodDesc = binding.etProdDesc.getText().toString();
                    float price = Float.parseFloat(binding.etPrice.getText().toString());
                    int quantity = Integer.parseInt(binding.etQuantity.getText().toString());

                    Boolean isDataInserted = DB.insertProductData(prodId, prodName, prodDesc, price, quantity);
                    if (isDataInserted == true) {
                        Toast.makeText(MainActivity.this, "Product Data Inserted", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(MainActivity.this, "Product Data Not Inserted, Duplicate ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (hasEmptyFields()) {
                    Toast.makeText(MainActivity.this, "Please Fill Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String prodId = binding.etProdId.getText().toString();
                    String prodName = binding.etProdName.getText().toString();
                    String prodDesc = binding.etProdDesc.getText().toString();
                    float price = Float.parseFloat(binding.etPrice.getText().toString());
                    int quantity = Integer.parseInt(binding.etQuantity.getText().toString());

                    Boolean isDataUpdated = DB.updateProductData(prodId, prodName, prodDesc, price, quantity);
                    if (isDataUpdated == true) {
                        Toast.makeText(MainActivity.this, "Product Data Updated", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(MainActivity.this, "Product Data Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String prodId = binding.etProdId.getText().toString();
                Boolean checkDeleteData = DB.deleteData(prodId);
                if (checkDeleteData == true) {
                    clearFields();
                    Toast.makeText(MainActivity.this, "Product Data Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if (binding.etProdId.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "ID Textbox is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Database Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                while (res.moveToNext()) {
                    String temp = res.getString(0);
                    String input = binding.etProdId.getText().toString();
                    if (temp.equals(input)) {
                        Toast.makeText(MainActivity.this, "Product Found", Toast.LENGTH_SHORT).show();
                        binding.etProdName.setText(res.getString(1));
                        binding.etProdDesc.setText(res.getString(2));
                        binding.etPrice.setText(res.getString(3));
                        binding.etQuantity.setText(res.getString(4));
                        return;
                    }
                }
                Toast.makeText(MainActivity.this, "Record Not Found", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnViewAllData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Database Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()) {

                    buffer.append("Product ID :" + res.getString(0) + "\n");
                    buffer.append("Product Name :" + res.getString(1) + "\n");
                    buffer.append("Product Description :" + res.getString(2) + "\n");
                    buffer.append("Price :" + res.getString(3) + "\n");
                    buffer.append("Quantity :" + res.getString(4) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Product Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        binding.btnClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    public boolean hasEmptyFields() {

        if (binding.etProdId.getText().toString().isEmpty() || binding.etProdName.getText().toString().isEmpty()
                || binding.etPrice.getText().toString().isEmpty() || binding.etQuantity.getText().toString().isEmpty()
                || binding.etProdDesc.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void clearFields() {

        binding.etProdId.setText("");
        binding.etProdName.setText("");
        binding.etPrice.setText("");
        binding.etQuantity.setText("");
        binding.etProdDesc.setText("");
    }
}

