package com.example.individualassignmentbudimadani;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spFuel;
    EditText etPrice;
    EditText etUsage;
    CheckBox cbBudi;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spFuel = findViewById(R.id.spFuel);
        etPrice = findViewById(R.id.etPrice);
        etUsage = findViewById(R.id.etUsage);
        cbBudi = findViewById(R.id.cbBudi);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        String[] fuelType = {"RON95", "RON97", "Diesel"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_item,
                        fuelType);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spFuel.setAdapter(adapter);

        btnCalculate.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        if(etPrice.getText().toString().equals("") ||
                                etUsage.getText().toString().equals("")){

                            Toast.makeText(
                                    MainActivity.this,
                                    "Please enter all values",
                                    Toast.LENGTH_SHORT).show();

                            return;
                        }

                        double price =
                                Double.parseDouble(
                                        etPrice.getText().toString());

                        double usage =
                                Double.parseDouble(
                                        etUsage.getText().toString());

                        if(price <= 0){

                            Toast.makeText(
                                    MainActivity.this,
                                    "Price must be greater than 0",
                                    Toast.LENGTH_SHORT).show();

                            return;
                        }

                        if(usage <= 0){

                            Toast.makeText(
                                    MainActivity.this,
                                    "Usage must be greater than 0",
                                    Toast.LENGTH_SHORT).show();

                            return;
                        }

                        String fuel =
                                spFuel.getSelectedItem().toString();

                        double totalCost =
                                price * usage;

                        double rebate = 0;

                        if(fuel.equals("RON95")
                                && cbBudi.isChecked()){

                            rebate = usage * 1.99;
                        }

                        double finalAmount =
                                totalCost - rebate;

                        tvResult.setText(
                                "Total Cost : RM " +
                                        String.format("%.2f", totalCost)
                                        + "\nBUDI Rebate : RM " +
                                        String.format("%.2f", rebate)
                                        + "\nFinal Amount : RM " +
                                        String.format("%.2f", finalAmount)
                        );
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(
                R.menu.main_menu,
                menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.about){

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AboutActivity.class);

            startActivity(intent);
        }

        return true;
    }
}