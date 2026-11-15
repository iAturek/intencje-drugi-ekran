package com.example.intencje_drugi_ekran;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNext).setOnClickListener(v -> openSummary());
    }

    private void openSummary() {
        Intent intent = new Intent(this, SummaryActivity.class);

        startActivity(intent);
    }

    private void showRegistered(String number) {
        Snackbar.make(findViewById(R.id.main),
                getString(R.string.registered, number),
                Snackbar.LENGTH_LONG).show();
    }
}