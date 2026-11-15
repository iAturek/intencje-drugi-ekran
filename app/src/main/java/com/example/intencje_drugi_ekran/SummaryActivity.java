package com.example.intencje_drugi_ekran;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SummaryActivity extends AppCompatActivity {

    public static final String EXTRA_SIZE = "size";
    public static final String EXTRA_CHEESE = "cheese";
    public static final String EXTRA_HAM = "ham";
    public static final String EXTRA_MUSHROOMS = "mushrooms";
    public static final String EXTRA_ADDRESS = "address";
    public static final String EXTRA_NUMBER = "number";

    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvSummary = findViewById(R.id.tvSummary);

        tvSummary.setText(buildSummary());

        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnConfirm = findViewById(R.id.btnConfirm);

        btnEdit.setOnClickListener(v -> finish());
        btnConfirm.setOnClickListener(v -> confirm());
    }

    private String buildSummary() {
        String size = getIntent().getStringExtra(EXTRA_SIZE);
        boolean cheese = getIntent().getBooleanExtra(EXTRA_CHEESE, false);
        boolean ham = getIntent().getBooleanExtra(EXTRA_HAM, false);
        boolean mushrooms = getIntent().getBooleanExtra(EXTRA_MUSHROOMS, false);
        String address = getIntent().getStringExtra(EXTRA_ADDRESS);

        int price = 0;

        if (size.equals("Mała")) {
            price = 20;
        } else if (size.equals("Średnia")) {
            price = 27;
        } else if (size.equals("Duża")) {
            price = 34;
        }

        String toppings = "";

        if (cheese) {
            toppings += "Ser, ";
            price += 3;
        }

        if (ham) {
            toppings += "Szynka, ";
            price += 3;
        }

        if (mushrooms) {
            toppings += "Pieczarki, ";
            price += 3;
        }

        if (toppings.isEmpty()) {
            toppings = "Brak";
        } else {
            toppings = toppings.substring(0, toppings.length() - 2);
        }

        return "Rozmiar: " + size
                + "\nDodatki: " + toppings
                + "\nAdres: " + address
                + "\nCena: " + price + " zł";
    }

    private void confirm() {
        String number = "PIZ/"
                + (1000 + new Random().nextInt(9000));

        Intent result = new Intent();
        result.putExtra(EXTRA_NUMBER, number);

        setResult(RESULT_OK, result);
        finish();
    }
}