package com.example.individualassignmentbudimadani;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class AboutActivity extends AppCompatActivity {

    TextView tvGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorOnPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvGithub = findViewById(R.id.tvGithub);

        tvGithub.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        Intent intent =
                                new Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://github.com/Jaxlina/IndividualAssignmentBudiMadani"));

                        startActivity(intent);
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

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        if(item.getItemId() == R.id.home){
            Intent intent =
                    new Intent(
                            AboutActivity.this,
                            MainActivity.class);
            startActivity(intent);
        }

        return true;
    }
}