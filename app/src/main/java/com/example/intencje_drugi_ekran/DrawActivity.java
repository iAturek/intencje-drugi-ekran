package com.example.intencje_drugi_ekran;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DrawActivity extends AppCompatActivity {

    private TextView tvResult;
    private TextView tvCount;

    private String[] entries;
    private String selectedEntry;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        tvResult = findViewById(R.id.tvResult);
        tvCount = findViewById(R.id.tvCount);

        Button btnAgain = findViewById(R.id.btnAgain);
        Button btnChoose = findViewById(R.id.btnChoose);

        random = new Random();

        Intent intent = getIntent();
        entries = intent.getStringArrayExtra("entries");

        if (entries == null || entries.length == 0) {
            finish();
            return;
        }

        tvCount.setText("Liczba wpisów: " + entries.length);

        drawEntry();

        btnAgain.setOnClickListener(v -> drawEntry());
        btnChoose.setOnClickListener(v -> chooseEntry());
    }

    private void drawEntry() {
        int index = random.nextInt(entries.length);

        selectedEntry = entries[index];

        tvResult.setText(selectedEntry);
    }

    private void chooseEntry() {
        Intent result = new Intent();

        result.putExtra("selectedEntry", selectedEntry);

        setResult(RESULT_OK, result);

        finish();
    }
}