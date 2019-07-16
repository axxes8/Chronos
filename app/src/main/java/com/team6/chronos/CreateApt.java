package com.team6.chronos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateApt extends AppCompatActivity {

    //User Inputs
    Button btnSubmitApt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_apt);

        btnSubmitApt = findViewById(R.id.btnSubmitApt);

        btnSubmitApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),QuickView.class));
                Toast.makeText(CreateApt.this, "Appointment Creation Currently Under Development", Toast.LENGTH_LONG).show();
            }


        });
    }
}
