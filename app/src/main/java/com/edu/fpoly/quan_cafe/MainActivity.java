package com.edu.fpoly.quan_cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgType, imgTable, imgInvoice, imgChangePass, imgCommodity, imgLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgType = findViewById(R.id.imgType);
        imgTable = findViewById(R.id.imgTable);
        imgInvoice = findViewById(R.id.imgInvoice);
        imgChangePass = findViewById(R.id.imgChangePassword);
        imgCommodity = findViewById(R.id.imgCommodity);
        imgLogout = findViewById(R.id.imgLogout);

        imgType.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MainActivity.this, TypeActivity.class));
        });
        imgTable.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MainActivity.this, TableActivity.class));
        });
        imgInvoice.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MainActivity.this, InvoiceActivity.class));
        });
        imgChangePass.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MainActivity.this, ChangePassActivity.class));
        });
        imgCommodity.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MainActivity.this, CommodityActivity.class));
        });
        imgLogout.setOnClickListener(v -> {
            startActivity(new android.content.Intent(MainActivity.this, LoginActivity.class));
        });
    }
}