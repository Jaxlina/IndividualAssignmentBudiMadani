package com.example.individualassignmentbudimadani;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    Spinner spFuel;
    EditText etUsage;
    CheckBox cbBudi;
    Button btnCalculate;
    TextView tvResult, tvPrice;
    double [] fuelPrice = {3.72, 4.35, 4.67};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorOnPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

        spFuel = findViewById(R.id.spFuel);
        etUsage = findViewById(R.id.etUsage);
        cbBudi = findViewById(R.id.cbBudi);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        tvPrice = findViewById(R.id.tvPrice);

        String[] fuelType = {
                "RON95",
                "RON97",
                "Diesel"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        fuelType);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spFuel.setAdapter(adapter);

        spFuel.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            android.view.View view,
                            int position,
                            long id) {

                        tvPrice.setText(
                                "Current Price : RM " + fuelPrice[position]
                        );
                    }

                    @Override
                    public void onNothingSelected(
                            AdapterView<?> parent) {

                    }
                });

        btnCalculate.setOnClickListener(v -> {

            if(etUsage.getText().toString().isEmpty()){

                Toast.makeText(
                        MainActivity.this,
                        "Please enter fuel usage",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            String fuel =
                    spFuel.getSelectedItem().toString();

            int position =
                    spFuel.getSelectedItemPosition();

            double price =
                    fuelPrice[position];

            double usage;

            try{
                usage = Double.parseDouble(
                        etUsage.getText().toString().trim());
            }
            catch(NumberFormatException e){

                etUsage.setError("Please enter a valid number");
                etUsage.requestFocus();

                return;
            }

            double totalCost =
                    usage * price;

            double rebate = 0;

            if(fuel.equals("RON95")
                    && cbBudi.isChecked()){
                rebate = usage * 1.99;
            }

            double totalsaving =
                    totalCost - rebate;

            tvResult.setText(
                    "Fuel Type : " + fuel
                            + "\n\nTotal Petrol Cost : RM "
                            + String.format("%.2f", totalCost)
                            + "\nBUDI Rebate : RM "
                            + String.format("%.2f", rebate)
                            + "\nFinal Amount : RM "
                            + String.format("%.2f", totalsaving)
            );
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