
package com.example.intencje_drugi_ekran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityResultLauncher<Intent> summaryLauncher;

    private Spinner spSize;
    private CheckBox cbCheese, cbHam, cbMushrooms;
    private EditText etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        summaryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK
                            && result.getData() != null) {

                        String number = result.getData().getStringExtra(
                                SummaryActivity.EXTRA_NUMBER
                        );

                        showRegistered(number);

                    } else if (result.getResultCode() == RESULT_CANCELED) {

                        Snackbar.make(
                                findViewById(R.id.btnNext),
                                "Zamówienie anulowane lub wymaga poprawy",
                                Snackbar.LENGTH_LONG
                        ).show();
                    }
                }
        );

        spSize = findViewById(R.id.spSize);
        cbCheese = findViewById(R.id.cbCheese);
        cbHam = findViewById(R.id.cbHam);
        cbMushrooms = findViewById(R.id.cbMushrooms);
        etAddress = findViewById(R.id.etAddress);

        setUpSizeSpinner();

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> openSummary());

        Button btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }

    private void setUpSizeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.pizza_sizes,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spSize.setAdapter(adapter);
    }

    private void openSummary() {

        String address = etAddress.getText().toString().trim();

        if (address.isEmpty()) {
            etAddress.setError("Wpisz adres dostawy");
            return;
        }

        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);

        intent.putExtra(SummaryActivity.EXTRA_SIZE,
                spSize.getSelectedItem().toString());

        intent.putExtra(SummaryActivity.EXTRA_CHEESE,
                cbCheese.isChecked());

        intent.putExtra(SummaryActivity.EXTRA_HAM,
                cbHam.isChecked());

        intent.putExtra(SummaryActivity.EXTRA_MUSHROOMS,
                cbMushrooms.isChecked());

        intent.putExtra(SummaryActivity.EXTRA_ADDRESS, address);

        summaryLauncher.launch(intent);
    }

    private void showRegistered(String number) {
        Snackbar.make(
                findViewById(R.id.btnNext),
                "Zamówienie przyjęte: " + number,
                Snackbar.LENGTH_LONG
        ).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
