package com.example.intencje_drugi_ekran;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etEntry;
    private TextView tvList;
    private ArrayList<String> entries;

    private ActivityResultLauncher<Intent> drawLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEntry = findViewById(R.id.etEntry);
        tvList = findViewById(R.id.tvList);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnDraw = findViewById(R.id.btnDraw);

        entries = new ArrayList<>();

        drawLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();

                        if (data != null) {
                            String selectedEntry = data.getStringExtra("selectedEntry");

                            if (selectedEntry != null) {
                                Snackbar.make(
                                        findViewById(android.R.id.content),
                                        "Wybrano: " + selectedEntry,
                                        Snackbar.LENGTH_LONG
                                ).show();
                            }
                        }
                    }
                }
        );

        btnAdd.setOnClickListener(v -> addEntry());
        btnDraw.setOnClickListener(v -> openDraw());
    }

    private void addEntry() {
        String entry = etEntry.getText().toString().trim();

        if (entry.isEmpty()) {
            etEntry.setError("Wpisz coś");
            return;
        }

        entries.add(entry);
        etEntry.setText("");

        String list = "";

        for (int i = 0; i < entries.size(); i++) {
            list += (i + 1) + ". " + entries.get(i) + "\n";
        }

        tvList.setText(list);
    }

    private void openDraw() {
        if (entries.isEmpty()) {
            Snackbar.make(
                    findViewById(android.R.id.content),
                    "Najpierw dodaj przynajmniej jeden wpis",
                    Snackbar.LENGTH_LONG
            ).show();

            return;
        }

        Intent intent = new Intent(MainActivity.this, DrawActivity.class);

        String[] entriesArray = entries.toArray(new String[0]);

        intent.putExtra("entries", entriesArray);

        drawLauncher.launch(intent);
    }
}