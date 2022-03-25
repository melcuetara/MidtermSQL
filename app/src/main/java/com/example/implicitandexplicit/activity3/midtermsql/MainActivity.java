package com.example.implicitandexplicit.activity3.midtermsql;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;;
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
                String prodId = binding.etProdId.getText().toString();
                String prodName = binding.etProdName.getText().toString();
                String prodDesc = binding.etProdDesc.getText().toString();
                float price = Float.parseFloat(binding.etPrice.getText().toString());
                int quantity = Integer.parseInt(binding.etQuantity.getText().toString());

                Boolean checkInsertData = DB.insertProductData(prodId, prodName, prodDesc, price, quantity);
                if(checkInsertData==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });


    }}